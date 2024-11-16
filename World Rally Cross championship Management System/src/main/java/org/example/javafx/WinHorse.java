//package org.example.javafx;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.chart.BarChart;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import java.io.IOException;
//import java.util.*;
//
//import javafx.scene.control.Alert;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.Random;
//
//import static org.example.javafx.SDD.selectedHorses;
//
//public class WinHorse {
//
//    public static List<AHD.Horse> winners = new ArrayList<>();
//
//
//    public void WinHorse() {
//
//        if (selectedHorses.size() < 4) {
//            showMessage("First you have to select the horses for final round");
//            return;
//        }
//
//        // Assign a random time for each horse
//        Random random = new Random();
//        for (AHD.Horse horse : selectedHorses) {
//            horse.setRaceTime(random.nextInt(91)); // Random time between 0 to 90 seconds
//        }
//
//        // Select the top 3 horses based on race time
//        selectedHorses.sort(Comparator.comparingInt(AHD.Horse::getRaceTime));
//        List<AHD.Horse> winners = selectedHorses.subList(0, Math.min(selectedHorses.size(), 3));
//
//        int place = 1;
//        for (AHD.Horse horse : winners) {
//            horse.setRaceTime(random.nextInt(91)); // Assign a random time between 0 to 90 seconds
//            horse.setPlace(place); // Set the place for the horse
//            place++;
//        }
//    }
//    private void showMessage(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Race Results");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//}
