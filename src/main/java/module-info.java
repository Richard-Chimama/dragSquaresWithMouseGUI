module com.example.dragsquareswithmouse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dragsquareswithmouse to javafx.fxml;
    exports com.example.dragsquareswithmouse;
}