package org.example.javafx;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static org.example.javafx.AHD.horses;


public class UHD {
    public TextField updatefieldbreed;
    public TextField updatefiledAge;
    public Button btnupdatehorsedeatils;
    public TextField updatefieldHorseID;
    public TextField updatefieldHorsename;


    public Button btnbackUHD;
    public TextField filedAge;
    public TextField updatefiledrace_record;
    public TextField updatefiledgroup;
    public TextField updatefieldJockey;
    public TableView horseTableinUHD;
    public TableColumn colHorseIDinUHD;
    public TextField selectfieldHorseID;

    public void updateingHorseID(ActionEvent actionEvent) {
    }

    public void updatingHorsename(ActionEvent actionEvent) {
    }

    public void updatingbreed(ActionEvent actionEvent) {
    }


    public void updatingjockey(ActionEvent actionEvent) {
    }


    public void updateingrace_record(ActionEvent actionEvent) {
    }

    public void updateinggroup(ActionEvent actionEvent) {
    }

    public void UpdatingAge(ActionEvent actionEvent) {
    }

    public void initialize() {


        colHorseIDinUHD.setCellValueFactory(new PropertyValueFactory<>("id"));


        initData(horses);
    }

    public void initData(List<AHD.Horse> horses) {
        ObservableList<AHD.Horse> horseList = FXCollections.observableArrayList(horses);
        horseTableinUHD.setItems(horseList);
    }


    public void backfromUHD(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnbackUHD.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidGroup(String input) {
        return input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D");
    }
    public void updatinghorsedetails(ActionEvent actionEvent) {
        String selectedHorseId = selectfieldHorseID.getText(); // Get the selected horse ID from the text field
        for (AHD.Horse horse : horses) {
            if (horse.getId().equals(selectedHorseId)) {

                // Update horse details if the corresponding field is not empty
                if (!updatefieldHorseID.getText().isEmpty()) {
                    // Validate ID input
                    if (!isValidInteger(updatefieldHorseID.getText())) {
                        // Display an error message
                        showAlert("ID must be a valid integer.");
                        updatefieldHorseID.clear();
                        return;
                    }

                    // Check if the new ID already exists
                    for (AHD.Horse existingHorse : horses) {
                        if (existingHorse.getId().equals(updatefieldHorseID.getText())) {
                            showAlert("ID already exists. Please use a different ID to update.");
                            updatefieldHorseID.clear();
                            return;
                        }
                    }
                    horse.setId(updatefieldHorseID.getText());
                }

                // Update other horse details if the corresponding field is not empty
                if (!updatefieldbreed.getText().isEmpty()) {
                    horse.setBreed(updatefieldbreed.getText());
                }

                if (!updatefieldHorsename.getText().isEmpty()) {
                    horse.setName(updatefieldHorsename.getText());
                }
                if (!updatefieldJockey.getText().isEmpty()) {
                    horse.setJockeyName(updatefieldJockey.getText());
                }
                if (!updatefiledAge.getText().isEmpty()) {
                    // Validate age input
                    if (!isValidInteger(updatefiledAge.getText())) {
                        // Display an error message
                        showAlert("Age must be a valid integer.");
                        updatefiledAge.clear();
                        return;
                    }
                    int age = Integer.parseInt(updatefiledAge.getText());
                    if (age < 5 || age > 18) {
                        showAlert("Age must be between 5 and 18.");
                        updatefiledAge.clear();
                        return;
                    }
                    horse.setAge(Integer.toString(age));
                }

                if (!updatefiledrace_record.getText().isEmpty()) {
                    horse.setRaceRecord(updatefiledrace_record.getText());
                }
                if (!updatefiledgroup.getText().isEmpty()) {
                    // Validate group input
                    String groupInput = updatefiledgroup.getText().toUpperCase();
                    if (!isValidGroup(groupInput)) {
                        showAlert("Group must be A, B, C, or D.");
                        updatefiledgroup.clear();
                        return;
                    }

                    // Check horse limit in the group
                    long groupCount = horses.stream().filter(h -> h.getGroup().equals(groupInput)).count();
                    if (groupCount >= 5) {
                        showAlert("Group " + groupInput + " already has 5 horses.");
                        return;
                    }

                    horse.setGroup(groupInput);
                }
                if (updatefieldHorseID.getText().isEmpty() &&
                        updatefieldbreed.getText().isEmpty() &&
                        updatefiledrace_record.getText().isEmpty() &&
                        updatefieldHorsename.getText().isEmpty() &&
                        updatefieldJockey.getText().isEmpty() &&
                        updatefiledgroup.getText().isEmpty() &&
                        updatefiledAge.getText().isEmpty()) {
                    showAlert("At least one field  must be filled to update.");
                    return;
                }

                // Clear all fields
                selectfieldHorseID.clear();
                updatefieldHorseID.clear();
                updatefieldHorsename.clear();
                updatefieldJockey.clear();
                updatefieldbreed.clear();
                updatefiledAge.clear();
                updatefiledrace_record.clear();
                updatefiledgroup.clear();

                showMessage("Horse Details successfully updated");
                initData(horses);
                horseTableinUHD.refresh();

                // Exit the loop once the horse is found and updated
                return;
            }
        }

        showAlert("Horse not found with ID: " + selectedHorseId);
        selectfieldHorseID.clear();
        horseTableinUHD.refresh();
    }


    public void selecteingHorseID(ActionEvent actionEvent) {
    }
}

    // Refresh the table view to reflect the changes
        ; // Assuming tableView is the TableView showing the horse list





