package org.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.example.javafx.SDD.selectedHorses;

public class VWH {
    public Button btnbackVWH;
    public CategoryAxis Xasis;
    public NumberAxis Yaxis;
    public BarChart barchat;

    public boolean Whd_done = false;
    public static List<AHD.Horse> winners = new ArrayList<>();
    public static List<AHD.Horse> visualize = new ArrayList<>();
    public Button btnvisualize;

    public void backformVWH(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnbackVWH.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void Visulaize(ActionEvent actionEvent) {

        if (selectedHorses.size() < 4) {
            showMessage("First you have to select the horses for final round");
            return;
        }

        // Assign a random time for each horse
        Random random = new Random();
        for (AHD.Horse horse : selectedHorses) {
            horse.setRaceTime(random.nextInt(91)); // Random time between 0 to 90 seconds
        }

        // Select the top 3 horses based on race time
        selectedHorses.sort(Comparator.comparingInt(AHD.Horse::getRaceTime));
        List<AHD.Horse> winners = selectedHorses.subList(0, Math.min(selectedHorses.size(), 3));

        int place = 1;
        for (AHD.Horse horse : winners) {
            horse.setRaceTime(random.nextInt(91)); // Assign a random time between 0 to 90 seconds
            horse.setPlace(place); // Set the place for the horse
            place++;
        }

        if (winners.size() < 3) {
            showMessage("First you have to do whd");
            return;
        }


        List<AHD.Horse> visualize = winners;



        // Debug output to check the contents of the winners list
        System.out.println("Winners List:");
        for (AHD.Horse horse : visualize) {
            System.out.println("Horse " + horse.getId() + ", Race Time: " + horse.getRaceTime());
        }


        barchat.getData().clear(); // Clear existing data

        // Create series for each bar
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>("", visualize.get(0).getRaceTime()));
        series1.setName("Horse " + visualize.get(0).getId());

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.getData().add(new XYChart.Data<>("", visualize.get(1).getRaceTime()));
        series2.setName("Horse " + visualize.get(1).getId());

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.getData().add(new XYChart.Data<>("", visualize.get(2).getRaceTime()));
        series3.setName("Horse " + visualize.get(2).getId());

        // Add series to the bar chart
        barchat.getData().addAll(series1, series2, series3);


        btnvisualize.setDisable(false);


        // Set the name of the series




    }



    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}

