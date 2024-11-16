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
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.input.MouseEvent;
    import javafx.stage.Stage;
    import java.io.IOException;
    import java.util.*;

    import static org.example.javafx.SDD.selectedHorses;

    public class WHD {
        public TableView WHDhorseTable;
        public TableColumn WHDcolHorseID;
        public TableColumn WHDcolHorseName;


        public TableColumn WHDcolGroup;
        public Button btnbackWHD;
        public TableColumn Time;
        public TableColumn Place;




        public Button btnvisualizehorse;
        public Button btnbackVWH;
        public BarChart barchat;
        public CategoryAxis Xasis;
        public NumberAxis Yaxis;




        public void WHD(ActionEvent actionEvent) {

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

            // Display winners
            StringBuilder message = new StringBuilder("Race Results:\n");
            for (int i = 0; i < winners.size(); i++) {
                AHD.Horse horse = winners.get(i);
                message.append(i + 1).append("st Place - ID: ").append(horse.getId()).append(", Name: ")
                        .append(horse.getName()).append(", Race Time: ").append(horse.getRaceTime()).append("s\n");
            }
            showMessage(message.toString());
            System.out.println(selectedHorses);
            System.out.println(winners);
            initData(winners);

        }


        public void initData(List<AHD.Horse> winners) {
            ObservableList<AHD.Horse> horseList = FXCollections.observableArrayList(winners);
            WHDhorseTable.setItems(horseList);

        }

        public void initialize() {
            WHDcolHorseID.setCellValueFactory(new PropertyValueFactory<>("id"));
            WHDcolHorseName.setCellValueFactory(new PropertyValueFactory<>("name"));
            Time.setCellValueFactory(new PropertyValueFactory<>("raceTime"));
            Place.setCellValueFactory(new PropertyValueFactory<>("place"));
            WHDcolGroup.setCellValueFactory(new PropertyValueFactory<>("group"));

        }


        public void backfromWHD(MouseEvent mouseEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnbackWHD.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void showMessage(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Race Results");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        public void visualizinghorse(ActionEvent actionEvent) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VWH.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnvisualizehorse.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void backformVWH(ActionEvent actionEvent) {
        }

        public void Visulaize(ActionEvent actionEvent) {
        }
    }



