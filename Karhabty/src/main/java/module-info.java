module com.example.karhabty {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.karhabty to javafx.fxml;
    exports com.example.karhabty;
}