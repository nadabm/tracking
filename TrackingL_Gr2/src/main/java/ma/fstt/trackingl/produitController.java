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

import ma.fstt.model.produit;
import ma.fstt.model.produitDAO;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;



public class produitController implements Initializable {


    @FXML
    private TextField nom ;


    @FXML
    private TextField prix ;
    @FXML
    private TextField descreption ;
    @FXML
    private Button btnBck;

    @FXML
    private TableView<produit> mytableP ;


    @FXML
    private TableColumn<produit, Long> col_idP ;

    @FXML
    private TableColumn <produit ,String> col_nomP ;

    @FXML
    private TableColumn <produit ,String> col_prixP ;
    @FXML
    private TableColumn <produit ,String> col_descp ;


    @FXML
    protected void onSaveButtonClickP() {

        // accees a la bdd

        try {
            produitDAO ProduitDAO = new produitDAO();

            produit prd = new produit(0L , nom.getText() , prix.getText() , descreption.getText());

            ProduitDAO.save(prd);


            UpdateTable();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    protected void onDeleteButtonClickP() {
        // Get the selected item in the table
        produit selectedproduit = mytableP.getSelectionModel().getSelectedItem();

        if (selectedproduit != null) {
            try {
                // Delete the selected item from the database
                produitDAO ProduitDAO = new produitDAO();
                ProduitDAO.delete(selectedproduit );

                // Remove the selected item from the observable list
                mytableP.getItems().remove(selectedproduit );
                getDataProduit().remove(selectedproduit );

                // Refresh the table to remove deleted rows
                mytableP.refresh();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

    public void UpdateTable(){
       col_idP.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        col_nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));

        col_prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_descp.setCellValueFactory(new PropertyValueFactory<>("descreption"));


        mytableP.setItems(this.getDataProduit());
    }

    public static ObservableList<produit> getDataProduit(){

        produitDAO ProduitDAO = null;

        ObservableList<produit> listfx = FXCollections.observableArrayList();

        try {
            ProduitDAO = new produitDAO();
            for (produit ettemp : ProduitDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }
    @FXML
    protected void onUpdateClick() {
        produit selectedproduit = mytableP.getSelectionModel().getSelectedItem();

        if (selectedproduit != null) {
            try {
                produitDAO ProduitDAO = new produitDAO();

                // update the selected product
                selectedproduit.setNom(nom.getText());
                selectedproduit.setPrix(prix.getText());
                selectedproduit.setDescreption(descreption.getText());
                System.out.println("on update clcik is working");
                // Show an alert to confirm the update
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update this product?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    ProduitDAO.update(selectedproduit);

                    // refresh the table view
                    UpdateTable();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }}

