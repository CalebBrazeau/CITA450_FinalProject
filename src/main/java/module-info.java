module com.example.cita450_finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.cita450_finalproject to javafx.fxml;
    exports com.example.cita450_finalproject;
}