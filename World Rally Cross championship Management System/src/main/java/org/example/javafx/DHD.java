package org.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;

import static org.example.javafx.AHD.horses;


public class DHD {

    public Button btnbackDHD;
    public Button btndeleted_horse;
    public TextField fieldhorseIDfromDHD;
    public Button btnbackVWH;
    public AnchorPane anchorpaneDHD;
    private int selectedHorseId;


    public void backformDHD(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnbackDHD.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void deleted_horse(ActionEvent actionEvent) {
        String selectedHorseId = fieldhorseIDfromDHD.getText(); // Get the selected horse ID from the text field

        // Validate ID input
        if (!isValidInteger(selectedHorseId)) {
            // Display an error message
            showAlert("ID must be a valid integer.");
            fieldhorseIDfromDHD.clear();
            return;
        }

        boolean found = false;
        for (Iterator<AHD.Horse> iterator = horses.iterator(); iterator.hasNext();) {
            AHD.Horse horse = iterator.next();
            if (horse.getId().equals(selectedHorseId)) {
                iterator.remove(); // Remove the horse from the list
                showMessage("Horse Details Successfully deleted");
                found = true;
                break;
            }
        }

        if (!found) {
            showAlert("Horse with ID " + selectedHorseId + " not found.");
        }

        fieldhorseIDfromDHD.clear();
        // Assuming horses is an ObservableList<Horse>
//        horses.removeIf(horse -> horse.getId().equals(selectedHorseId));
//        fieldhorseIDfromDHD.clear(); // Clear the text field
    }

    private boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String message) {
        // Display an alert with the given message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void horseIDfromDHD(ActionEvent actionEvent) {
    }
}
