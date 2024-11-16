package org.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.example.javafx.AHD.horses;

public class SDD {
    public Button btnbackSDD;
    public TableColumn SDDcolJockeyName;
    public TableColumn SDDcolBreed;
    public TableColumn SDDcolAge;
    public TableColumn SDDcolRaceRecord;
    public TableColumn SDDcolGroup;

    public TableView SDDhorseTable;
    public TableColumn SDDcolHorseName;
    public TableColumn SDDcolHorseID;

    public static List<AHD.Horse> selectedHorses = new ArrayList<>();




    public void SDD(ActionEvent actionEvent) {
        selectedHorses.clear(); // Clear the list before selecting horses for the final round

        if (horses.size() < 20) {
            showMessage("There are not enough horses for the final round. ");
            return;
        }

        // Separate horses into groups
        Map<String, List<AHD.Horse>> groupedHorses = horses.stream()
                .collect(Collectors.groupingBy(AHD.Horse::getGroup));

        // Check if there is at least one horse in each group
        if (groupedHorses.size() < 4) {
            showMessage("There are not enough groups for the final round. Minimum 4 groups are required.");
            return;
        }

        // Select a random horse from each group
        Random random = new Random();
        for (List<AHD.Horse> group : groupedHorses.values()) {
            if (!group.isEmpty()) {
                int index = random.nextInt(group.size());
                selectedHorses.add(group.get(index)); // Add a random horse from each group
            }
        }

        StringBuilder message = new StringBuilder("Selected horses for the final round:\n");
        for (AHD.Horse horse : selectedHorses) {
            message.append("ID: ").append(horse.getId()).append("\n");
            message.append("Name: ").append(horse.getName()).append("\n");
            message.append("Jockey Name: ").append(horse.getJockeyName()).append("\n");
            message.append("Breed: ").append(horse.getBreed()).append("\n");
            message.append("Age: ").append(horse.getAge()).append("\n");
            message.append("Race Record: ").append(horse.getRaceRecord()).append("\n");
            message.append("Group: ").append(horse.getGroup()).append("\n\n");
        }

        showMessage(message.toString());
        initData(selectedHorses);
    }




    public void initData(List<AHD.Horse> selectedHorses) {
        ObservableList<AHD.Horse> horseList = FXCollections.observableArrayList(selectedHorses);
        SDDhorseTable.setItems(horseList);
    }




    public void initialize() {
        SDDcolHorseID.setCellValueFactory(new PropertyValueFactory<>("id"));
        SDDcolHorseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        SDDcolJockeyName.setCellValueFactory(new PropertyValueFactory<>("jockeyName"));
        SDDcolBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        SDDcolAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        SDDcolRaceRecord.setCellValueFactory(new PropertyValueFactory<>("raceRecord"));
        SDDcolGroup.setCellValueFactory(new PropertyValueFactory<>("group"));

    }


    public void backfromSDD(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnbackSDD.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



private void showMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


}
