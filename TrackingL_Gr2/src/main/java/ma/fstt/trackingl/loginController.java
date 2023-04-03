package ma.fstt.trackingl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class loginController {

     @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private void onLoginButtonClick() {
        String user = username.getText();
        String pass = password.getText();

        // Connect to database and check credentials
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/glovo", "root", "");
            stmt = conn.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
            stmt.setString(1, user);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            if (rs.next()) {
                // Credentials are correct, open main interface
                Stage stage = (Stage) username.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                // Credentials are incorrect, show error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Error connecting to database");
            alert.showAndWait();
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
        }


