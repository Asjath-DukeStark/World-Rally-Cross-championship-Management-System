package org.example.javafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.javafx.AHD.horses;



public class HelloController {

    @FXML
    public Button btnvisualizehorse;
    public Button btnviewhorse;
    public Button btnselecthorse;
    public Button btnsavehorse;
    public Button btnwinninghorse;
    public Button btnexit;
    public Button btnupdatehorse;
    public Button btnaddhorse;
    public Button btndeletehorse;
    public Button btnstartrace;

    public Stage stage;
    public Parent root;

    private boolean raceStarted = false;

    public void addinghorse(ActionEvent actionEvent) {

        if (raceStarted) {
            showMessage("Cannot add horse details after the race has started.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AHD.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnaddhorse.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        }

        public void updatinghorse(ActionEvent actionEvent) {

            if (raceStarted) {
                showMessage("Cannot update horse details after the race has started.");
                return;
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UHD.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnupdatehorse.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



        public void deletinghorse(ActionEvent actionEvent) {

            if (raceStarted) {
                showMessage("Cannot delete horse details after the race has started.");
                return;
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DHD.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btndeletehorse.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void starttherace(ActionEvent actionEvent) {

        if (horses.size() < 20) {
            showMessage("There are not enough horses to start race.");
            return;
        }
        raceStarted = true;
        showMessage("Race Started");
        btnstartrace.setDisable(true); // Assuming startRaceButton is the button for starting the race
    }

    public void stoprace(ActionEvent actionEvent) {
        if (!raceStarted) {
            showMessage("Race not started yet.");
            return;
        }

        raceStarted = false;
        showMessage("Race Stopped");
        btnstartrace.setDisable(false);

    }

        public void viewinghorse(ActionEvent actionEvent) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VHD.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnviewhorse.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    public void selectinghorse(ActionEvent actionEvent) {
        if (raceStarted) {
            showMessage("Cannot select horses for the final round. after the race has started.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SDD.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnselecthorse.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void savinghorse(ActionEvent actionEvent) {
            if (horses.isEmpty()) {
                System.out.println("No horse details to save.");
                showError("No horse details to save.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("horse_details.txt"))) {
                for (AHD.Horse horse : horses) {
                    writer.write(horse.getId() + "," +
                            horse.getName() + "," +
                            horse.getJockeyName() + "," +
                            horse.getBreed() + "," +
                            horse.getAge() + "," +
                            horse.getRaceRecord() + "," +
                            horse.getGroup());
                    writer.newLine();
                }
                showMessage("Horse details saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





        public void displayingwinninghorse(ActionEvent actionEvent) {
            if (!raceStarted) {
                showMessage("Cannot display winning horse details before the race has started.");
                return;
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("WHD.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnwinninghorse.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void visualizinghorse(ActionEvent actionEvent) {

            if (!raceStarted) {
                showMessage("Cannot visualize horse details before the race has started.");
                return;
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VWH.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnvisualizehorse.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void exiting(ActionEvent actionEvent) {
            Platform.exit();
        }
    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    public void setStage(Stage stage) {
        this.stage = stage;
    }


}


