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
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;
import ma.fstt.trackingl.loginController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;



public class HelloController implements Initializable {


    @FXML
    private TextField nom ;


    @FXML
    private TextField tele ;
    @FXML
    private TextField pre ;
    @FXML
    private Button btnBck;

    @FXML
    private TableView<Livreur> mytable ;


    @FXML
    private TableColumn<Livreur ,Long> col_id ;

    @FXML
    private TableColumn <Livreur ,String> col_nom ;

    @FXML
    private TableColumn <Livreur ,String> col_tele ;
    @FXML
    private TableColumn <Livreur ,String> col_pre ;


    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            Livreur liv = new Livreur(0l , nom.getText() , tele.getText() , pre.getText());

            livreurDAO.save(liv);


            UpdateTable();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    protected void onDeleteButtonClick() {
        // Get the selected item in the table
        Livreur selectedLivreur = mytable.getSelectionModel().getSelectedItem();

        if (selectedLivreur != null) {
            try {
                // Delete the selected item from the database
                LivreurDAO livreurDAO = new LivreurDAO();
                livreurDAO.delete(selectedLivreur);

                // Remove the selected item from the observable list
                mytable.getItems().remove(selectedLivreur);
                getDataLivreurs().remove(selectedLivreur);

                // Refresh the table to remove deleted rows
                mytable.refresh();

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
        col_id.setCellValueFactory(new PropertyValueFactory<Livreur,Long>("id_livreur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Livreur,String>("nom"));

        col_tele.setCellValueFactory(new PropertyValueFactory<Livreur,String>("telephone"));
        col_pre.setCellValueFactory(new PropertyValueFactory<Livreur,String>("prenom"));


        mytable.setItems(this.getDataLivreurs());
    }

    public static ObservableList<Livreur> getDataLivreurs(){

        LivreurDAO livreurDAO = null;

        ObservableList<Livreur> listfx = FXCollections.observableArrayList();

        try {
            livreurDAO = new LivreurDAO();
            for (Livreur ettemp : livreurDAO.getAll())
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
}