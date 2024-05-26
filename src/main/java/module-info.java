module com.smakab.datingapp.zustrichalnya {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.smakab.datingapp.zustrichalnya to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya;

    opens com.smakab.datingapp.zustrichalnya.Controllers to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers;

//    opens com.smakab.datingapp.zustrichalnya.Views to javafx.fxml;
//    exports com.smakab.datingapp.zustrichalnya.Views;

//    opens com.smakab.datingapp.zustrichalnya.Models to javafx.fxml;
//    exports com.smakab.datingapp.zustrichalnya.Models;
}