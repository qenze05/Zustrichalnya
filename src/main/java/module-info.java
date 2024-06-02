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

    requires java.desktop;
    requires javatuples;

    opens com.smakab.datingapp.zustrichalnya to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya;

    opens com.smakab.datingapp.zustrichalnya.Controllers to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers;

    opens com.smakab.datingapp.zustrichalnya.Controllers.Profile to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers.Profile;

    opens com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

    opens com.smakab.datingapp.zustrichalnya.Interfaces to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Interfaces;

    opens com.smakab.datingapp.zustrichalnya.Utils to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Utils;

    opens com.smakab.datingapp.zustrichalnya.Models to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Models;

    opens com.smakab.datingapp.zustrichalnya.Models.Profile to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Models.Profile;
    exports com.smakab.datingapp.zustrichalnya.Controllers.Search;
    opens com.smakab.datingapp.zustrichalnya.Controllers.Search to javafx.fxml;

//    opens com.smakab.datingapp.zustrichalnya.Views to javafx.fxml;
//    exports com.smakab.datingapp.zustrichalnya.Views;

//    opens com.smakab.datingapp.zustrichalnya.Models to javafx.fxml;
//    exports com.smakab.datingapp.zustrichalnya.Models;
}