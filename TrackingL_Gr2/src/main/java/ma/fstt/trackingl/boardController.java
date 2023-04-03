package ma.fstt.trackingl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.fstt.model.commande;
import ma.fstt.model.commandeDAO;

public class boardController implements Initializable {

    @FXML
    private TableColumn<commande, Long> idProduitColumn;
    @FXML
    private TableColumn<commande, Long> idLivreurColumn;
    @FXML
    private TableColumn<commande, String> dateColumn;
    @FXML
    private TableColumn<commande, Double> kmColumn;
    @FXML
    private TableView<commande> commandeTable;
    @FXML
    private Button btnBck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Initialize the cell value factories
            idProduitColumn.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            idLivreurColumn.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            kmColumn.setCellValueFactory(new PropertyValueFactory<>("km"));

            // Get the data from the database
            commandeDAO dao = new commandeDAO();
            List<commande> commandes = dao.getAll();

            // Populate the table with the data
            commandeTable.getItems().setAll(commandes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void onbtnBck(ActionEvent event) throws IOException {
        // Get the current stage

        Stage stage = (Stage) btnBck.getScene().getWindow();

        // Load the previous scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Parent root = loader.load();

        // Show the previous scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

