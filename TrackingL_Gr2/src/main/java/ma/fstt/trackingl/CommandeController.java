package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.fstt.model.BaseDAO;
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;
import ma.fstt.model.produit;
import ma.fstt.trackingl.loginController;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;







    public class CommandeController implements Initializable {

        @FXML
        private TableView<Livreur> mytable;

        @FXML
        private TableColumn<Livreur, Long> col_id;

        @FXML
        private TableColumn<Livreur, String> col_tele;

        @FXML
        private TableColumn<Livreur, String> col_pre;

        @FXML
        private TableColumn<Livreur, String> col_nom;

        @FXML
        private TableView<produit> mytableP;

        @FXML
        private TableColumn<produit, Long> col_idP;

        @FXML
        private TableColumn<produit, String> col_nomP;

        @FXML
        private TableColumn<produit, Double> col_prixP;

        @FXML
        private TableColumn<produit, String> col_descp;
 @FXML
 private  TextField date;
 @FXML
 private TextField km;
        @FXML
        private Button saveCommandeBtn;
        @FXML
        private Button btnBck;

        public CommandeController() {
        }


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            // Initialize Livreur table columns
            //mytableP.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            col_id.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            col_tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            col_pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

            // Initialize Produit table columns
            col_idP.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            col_nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_descp.setCellValueFactory(new PropertyValueFactory<>("descreption"));

            // Populate Livreur table

            try {
                mytable.setItems(getAll());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            // Populate Produit table
            try {
                mytableP.setItems(getProduitList());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        Statement state ;
        PreparedStatement preparedStatement ;
        public ObservableList<Livreur> getAll() throws SQLException
        {
            ObservableList livreur = FXCollections.observableArrayList();
            state =  BaseDAO.getConnection().createStatement() ;
            ResultSet result = state.executeQuery("select * from livreur");
            while(result.next())
            {
                Livreur liv = new Livreur();
                liv.setNom(result.getString(2));
                liv.setId_livreur(result.getLong(1));
                liv.setTelephone(result.getString(3));
                liv.setPrenom(result.getString(4));
                livreur.add(liv);
            }
            BaseDAO.closeConnectionAndStatement();

            return livreur ;
        }

        public ObservableList<produit> getProduitList() throws SQLException {
            ObservableList Produit = FXCollections.observableArrayList();
            state =  BaseDAO.getConnection().createStatement() ;
            ResultSet result = state.executeQuery("select * from produit");
            while(result.next())
            {
                produit prd = new produit();
                prd.setNom(result.getString(2));
                prd.setId_produit(result.getLong(1));
                prd.setPrix(result.getString(3));
                prd.setDescreption(result.getString(4));
                Produit.add(prd);
            }
            BaseDAO.closeConnectionAndStatement();

            return Produit ;
        }

        @FXML
        public void handleSaveCommandeBtn(ActionEvent event) {
            Livreur selectedLivreur = mytable.getSelectionModel().getSelectedItem();
            produit selectedProduit = mytableP.getSelectionModel().getSelectedItem();
            String dateString = date.getText();
            double kmValue = Double.parseDouble(km.getText());

            // Check if either selectedLivreur or selectedProduit is null
            if (selectedLivreur == null || selectedProduit == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a Livreur and a produit.");
                alert.showAndWait();
                return;
            }

            try {
                // Prepare the INSERT statement
                PreparedStatement statement = BaseDAO.getConnection().prepareStatement("INSERT INTO commande (id_livreur, id_produit, date, km) VALUES (?, ?, ?, ?)");
                statement.setLong(1, selectedLivreur.getId_livreur());
                statement.setLong(2, selectedProduit.getId_produit());
                statement.setString(3, dateString);
                statement.setDouble(4, kmValue);

                // Execute the statement
                statement.executeUpdate();

                // Close the statement and the database connection
                statement.close();
                BaseDAO.closeConnectionAndStatement();

                // Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("The Commande has been created successfully.");
                alert.showAndWait();

                // Clear the selected items and text fields
                mytable.getSelectionModel().clearSelection();
                mytableP.getSelectionModel().clearSelection();
                date.clear();
                km.clear();

            } catch (SQLException e) {
                // Display an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while creating the Commande.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }



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
