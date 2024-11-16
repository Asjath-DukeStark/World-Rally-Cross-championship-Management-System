package org.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static org.example.javafx.AHD.horses;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class VHD {



    @FXML
    public TableView<AHD.Horse> horseTable;
    @FXML
    public TableColumn<AHD.Horse, String> colHorseID;
    @FXML
    public TableColumn<AHD.Horse, String> colHorseName;
    @FXML
    public TableColumn<AHD.Horse, String> colJockeyName;
    @FXML
    public TableColumn<AHD.Horse, String> colBreed;
    @FXML
    public TableColumn<AHD.Horse, String> colAge;
    @FXML
    public TableColumn<AHD.Horse, String> colRaceRecord;
    @FXML
    public TableColumn<AHD.Horse, String> colGroup;
    @FXML
    public Button btnbackVHD;

    public Scene previousScene; // Store the previous scene


    public void initialize() {
        colHorseID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colHorseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colJockeyName.setCellValueFactory(new PropertyValueFactory<>("jockeyName"));
        colBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colRaceRecord.setCellValueFactory(new PropertyValueFactory<>("raceRecord"));
        colGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        initData(horses);
    }

    public void initData(List<AHD.Horse> horses) {
        ObservableList<AHD.Horse> horseList = FXCollections.observableArrayList(horses);
        bubbleSort(horseList);
        horseTable.setItems(horseList);
    }

    private void bubbleSort(ObservableList<AHD.Horse> horseList) {
        for (int i = 0; i < horseList.size() - 1; i++) {
            for (int j = 0; j < horseList.size() - i - 1; j++) {
                if (horseList.get(j).getId().compareTo(horseList.get(j + 1).getId()) > 0) {
                    // Swap horses
                    AHD.Horse temp = horseList.get(j);
                    horseList.set(j, horseList.get(j + 1));
                    horseList.set(j + 1, temp);
                }
            }
        }
    }




    public void backfromVHD(MouseEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            previousScene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

// Go back to the previous scene
        btnbackVHD.setOnAction(event -> {
            Stage stage = (Stage) btnbackVHD.getScene().getWindow();
            stage.setScene(previousScene);
        });
    }
}
