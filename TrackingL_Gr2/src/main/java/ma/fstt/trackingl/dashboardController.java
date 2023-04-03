package ma.fstt.trackingl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class dashboardController {


        @FXML
        private Button livreurBtn;

        @FXML
        private Button commandesBtn;

        @FXML
        private Button produitsBtn;

        @FXML
        private Button deconnecterBtn;

        @FXML
        public void onLivreurClicked() {
                try {
                        // Load the hello-view.fxml file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                        Parent root = loader.load();

                        // Set the new scene
                        Scene scene = livreurBtn.getScene();
                        scene.setRoot(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @FXML
        public void onCommandesClicked() {
                // Handle Produits button click
                try {
                        // Load the hello-view.fxml file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("commande.fxml"));
                        Parent root = loader.load();

                        // Set the new scene
                        Scene scene = produitsBtn.getScene();
                        scene.setRoot(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @FXML
        public void onProduitsClicked() {
            // Handle Produits button click
                try {
                        // Load the hello-view.fxml file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("produit.fxml"));
                        Parent root = loader.load();

                        // Set the new scene
                        Scene scene = produitsBtn.getScene();
                        scene.setRoot(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @FXML
        public void onDeconnecterClicked() {
            // Handle Deconnecter button click
                try {
                        // Load the login.fxml file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                        Parent root = loader.load();

                        // Set the new scene
                        Scene scene = deconnecterBtn.getScene();
                        scene.setRoot(root);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

    public void onDashboardClicked() {
            try {
                    // Load the login.fxml file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("board.fxml"));
                    Parent root = loader.load();

                    // Set the new scene
                    Scene scene = deconnecterBtn.getScene();
                    scene.setRoot(root);
            } catch (IOException e) {
                    e.printStackTrace();
            }
    }
}

