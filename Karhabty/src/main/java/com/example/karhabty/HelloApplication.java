package com.example.karhabty;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Lavage",
                "Réparation",
                "Réclamation"
        );

        Button serviceClientButton = new Button("Service Client");
        VBox root = new VBox(serviceClientButton);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 200);

        ListView<String> listView = new ListView<>(list);

        serviceClientButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().add(listView);
            }
        });

        listView.setOnMouseClicked(event -> {
            String selectedService = listView.getSelectionModel().getSelectedItem();
            if ("Lavage".equals(selectedService)) {
                showDateTimePickerPopup(primaryStage);
            }
        });

        listView.setOnMouseClicked(event -> {
            String selectedService = listView.getSelectionModel().getSelectedItem();
            if ("Lavage".equals(selectedService)) {
                showDateTimePickerPopup(primaryStage);
            } else if ("Réparation".equals(selectedService)) {
                showVehicleTypeList(primaryStage);
            } else if ("Réclamation".equals(selectedService)) {
                showReclamationBox(primaryStage);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showVehicleTypeList(Stage primaryStage) {
        Stage vehicleTypeStage = new Stage();
        vehicleTypeStage.setTitle("Réparation");

        ObservableList<String> vehicleTypes = FXCollections.observableArrayList(
                "Renault",
                "Audi",
                "Ford",
                "Kia",
                "Fiat"
                // Add more vehicle types as needed
        );

        ChoiceBox<String> vehicleTypeChoiceBox = new ChoiceBox<>(vehicleTypes);
        TextField matriculeTextField = new TextField(); // Text field for matricule de voiture
        matriculeTextField.setPrefWidth(100);
        matriculeTextField.setPrefHeight(10);
        matriculeTextField.setAlignment(Pos.CENTER);

        ObservableList<String> additionalItemList = FXCollections.observableArrayList(
                "Plaquettes de freins", "Parallélisme", "Amortisseurs", "Vidange", "Embrayage", "Climatisation", "Décalaminage", "Vidange", "Roue"
        );

        ListView<String> additionalListView = new ListView<>(additionalItemList);

        VBox vehicleTypeBox = new VBox();



        // Add a title label
        Label titleLabel = new Label("Type de voiture");
        titleLabel.setStyle("-fx-font-weight: bold;");

        // Add a title label for matricule de voiture
        Label matriculeLabel = new Label("Matricule de voiture");
        matriculeLabel.setStyle("-fx-font-weight: bold;");


        vehicleTypeBox.getChildren().addAll(titleLabel, vehicleTypeChoiceBox, matriculeLabel, matriculeTextField,additionalListView);

        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(e -> {
            String selectedVehicleType = vehicleTypeChoiceBox.getValue();
            String matricule = matriculeTextField.getText();

            // Get the selected item from additionalListView
            String typeReparation = additionalListView.getSelectionModel().getSelectedItem();

            System.out.println("Selected Vehicle Type: " + selectedVehicleType);
            System.out.println("Matricule de voiture: " + matricule);
            System.out.println("Type de réparation: " + typeReparation);

            // You can perform actions with the selected vehicle type here

            vehicleTypeStage.close();
        });

        vehicleTypeBox.getChildren().addAll(confirmButton);
        vehicleTypeBox.setAlignment(Pos.CENTER);
        vehicleTypeBox.setSpacing(10);

        Scene vehicleTypeScene = new Scene(vehicleTypeBox, 300, 200);

        vehicleTypeStage.setScene(vehicleTypeScene);
        vehicleTypeStage.show();
    }


    private void showDateTimePickerPopup(Stage primaryStage) {
        Stage dateTimeStage = new Stage();
        dateTimeStage.setTitle("Lavage date and timer picker");

        // Create a DatePicker for selecting date
        DatePicker datePicker = new DatePicker();

        // Create a Spinner for selecting time
        Spinner<Integer> hourSpinner = new Spinner<>(0, 23, 12);
        Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, 0);

        VBox dateTimeBox = new VBox(datePicker, hourSpinner, minuteSpinner);
        dateTimeBox.setAlignment(Pos.CENTER);
        dateTimeBox.setSpacing(10);

        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            LocalTime selectedTime = LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue());
            System.out.println("Selected Date and Time: " + selectedDate + " " + selectedTime);

            // You can perform actions with the selected date and time here

            dateTimeStage.close();
        });

        dateTimeBox.getChildren().add(confirmButton);

        Scene dateTimeScene = new Scene(dateTimeBox, 300, 200);

        dateTimeStage.setScene(dateTimeScene);
        dateTimeStage.show();
    }
    private void showReclamationBox(Stage primaryStage) {
        Stage reclamationStage = new Stage();
        reclamationStage.setTitle("Réclamation");

        // Add a title label for réclamation
        Label reclamationLabel = new Label("Écrire votre réclamation:");
        reclamationLabel.setStyle("-fx-font-weight: bold;");

        // Text field for entering the réclamation
        TextField reclamationTextField = new TextField();
        reclamationTextField.setPrefWidth(200);

        // Button to send the réclamation
        Button envoyerButton = new Button("Envoyer");
        envoyerButton.setOnAction(e -> {
            String reclamationText = reclamationTextField.getText();
            System.out.println("Réclamation: " + reclamationText);

            // You can perform actions with the réclamation here

            reclamationStage.close();
        });

        VBox reclamationBox = new VBox(reclamationLabel, reclamationTextField, envoyerButton);
        reclamationBox.setAlignment(Pos.CENTER);
        reclamationBox.setSpacing(10);

        Scene reclamationScene = new Scene(reclamationBox, 300, 150);

        reclamationStage.setScene(reclamationScene);
        reclamationStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
