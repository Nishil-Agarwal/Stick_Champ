module com.example.ap_proj {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;
    requires junit;
    requires javafx.media;


    opens com.example.ap_proj to javafx.fxml;
    exports com.example.ap_proj;
}