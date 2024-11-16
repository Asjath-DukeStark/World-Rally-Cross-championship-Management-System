package org.example.javafx;
import org.junit.Test;

import static org.junit.Assert.*;



import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AHD {


    public static List<Horse> horses = new ArrayList<>();

    public TextField fieldHorseID;
    public TextField fieldHorsename;
    public TextField FiledJockeyname;
    public TextField fieldbreed;
    public TextField filedAge;
    public TextField filedracerecord;
    public Button btnadd;
    public TextField fieldgroup;
    public Button btnbackadd;

    static boolean isValidGroup(String input) {
        return input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D");
    }


    public void Addingdetails(ActionEvent actionEvent) {

        if (horses.size() >= 20) {
            showAlert("Cannot add more than 20 horses.");
            return;
        }

    if (areAllFieldsFilled()) {
        String ageInput = filedAge.getText();
        if (!isValidInteger(ageInput)) {
            // Display an error message
            showAlert("Age must be a valid integer.");
            filedAge.clear();
            return;
        }

        int age = Integer.parseInt(ageInput);
        if (age < 3 || age > 18) {
            showAlert("Age must be between 3 and 18.");
            filedAge.clear();
            return;
        }

        // Validate ID input
        String idInput = fieldHorseID.getText();
        if (!isValidInteger(idInput)) {
            // Display an error message
            showAlert("ID must be a valid integer.");
            fieldHorseID.clear();
            return;
        }

        for (Horse horse : horses) {
            if (horse.getId().equals(idInput)) {
                showAlert("ID already exists. Please use a different ID.");

                fieldHorseID.clear();

                return;
            }
        }

        String groupInput = fieldgroup.getText().toUpperCase();
        if (!isValidGroup(groupInput)) {
            showAlert("Group must be A, B, C, or D.");
            fieldgroup.clear();
            return;
        }

        long groupCount = horses.stream().filter(h -> h.getGroup().equals(groupInput)).count();
        if (groupCount >= 5) {
            showAlert("Group " + groupInput + " already has 5 horses.");
            fieldgroup.clear();
            return;
        }

        // If all validations pass, add the horse details
        Horse horse = new Horse(
                fieldHorseID.getText(),
                fieldHorsename.getText(),
                FiledJockeyname.getText(),
                fieldbreed.getText(),
                filedAge.getText(),
                filedracerecord.getText(),
                fieldgroup.getText().toUpperCase()
        );
        horses.add(horse);
        showMessage("Horse Successfully Added");

        fieldHorseID.clear();
        fieldHorsename.clear();
        FiledJockeyname.clear();
        fieldbreed.clear();
        filedAge.clear();
        filedracerecord.clear();
        fieldgroup.clear();
    } else {
        showAlert("Please fill in all fields.");
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

    private boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean areAllFieldsFilled() {
    return !fieldHorseID.getText().isEmpty() &&
            !fieldHorsename.getText().isEmpty() &&
            !FiledJockeyname.getText().isEmpty() &&
            !fieldbreed.getText().isEmpty() &&
            !filedAge.getText().isEmpty() &&
            !filedracerecord.getText().isEmpty() &&
            !fieldgroup.getText().isEmpty();
}

    public void backfromadd(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnbackadd.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public List<Horse> getHorses() {
    return horses;
}

    public void addinga_group(ActionEvent actionEvent) {
    }

    public void addingracerecord(ActionEvent actionEvent) {
    }

    public void AddingAge(ActionEvent actionEvent) {
    }

    public void addingbreed(ActionEvent actionEvent) {
    }

    public void Addingjockeyname(ActionEvent actionEvent) {
    }

    public void AddingHorseID(ActionEvent actionEvent) {
    }

    public void AddingHorsename(ActionEvent actionEvent) {
    }




    public static class Horse {
    private String id;
    private String name;
    private String jockeyName;
    private String breed;
    private String age;
    private String raceRecord;
    private String group;

    private int raceTime;

    private int place;

    public Horse(String id, String name, String jockeyName, String breed, String age, String raceRecord, String group) {
        this.id = id;
        this.name = name;
        this.jockeyName = jockeyName;
        this.breed = breed;
        this.age = age;
        this.raceRecord = raceRecord;
        this.group = group;
        this.raceTime = raceTime;
        this.place = place;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJockeyName() {
        return jockeyName;
    }

    public String getBreed() {
        return breed;
    }

    public String getAge() {
        return age;
    }

    public String getRaceRecord() {
        return raceRecord;
    }

    public String getGroup() {
        return group;
    }

        public int getRaceTime() {
            return raceTime;
        }


        public int getPlace() {
            return place;
        }

        public void setId(String id) {
            this.id = id;
    }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public void setBreed(String breed) {
            this.breed = breed;
        }

        public void setRaceRecord(String raceRecord) {
            this.raceRecord = raceRecord;
        }

        public void setJockeyName(String jockeyName) {
            this.jockeyName = jockeyName;
        }

        public void setRaceTime(int raceTime) {
            this.raceTime = raceTime;
        }

        public void setPlace(int place) {
            this.place = place;
        }
    }
}
