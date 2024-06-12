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
    requires com.google.gson;

    opens com.smakab.datingapp.zustrichalnya to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya;

    opens com.smakab.datingapp.zustrichalnya.Controllers to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers;

    opens com.smakab.datingapp.zustrichalnya.Controllers.Profile to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers.Profile;

    opens com.smakab.datingapp.zustrichalnya.Controllers.Chat to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers.Chat;

    opens com.smakab.datingapp.zustrichalnya.Controllers.SignUp to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers.SignUp;

    opens com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

    opens com.smakab.datingapp.zustrichalnya.Controllers.Search to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers.Search;

    opens com.smakab.datingapp.zustrichalnya.Controllers.Search.Content to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

    opens com.smakab.datingapp.zustrichalnya.Interfaces to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Interfaces;

    opens com.smakab.datingapp.zustrichalnya.Utils to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Utils;

    opens com.smakab.datingapp.zustrichalnya.Models to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Models;

    opens com.smakab.datingapp.zustrichalnya.Models.Profile to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Models.Profile;

    opens com.smakab.datingapp.zustrichalnya.Models.Search to javafx.fxml;
    exports com.smakab.datingapp.zustrichalnya.Models.Search;


    exports com.smakab.datingapp.zustrichalnya.Views.Profile;
    opens com.smakab.datingapp.zustrichalnya.Views.Profile to javafx.fxml;

    exports com.smakab.datingapp.zustrichalnya.Views.Template;
    opens com.smakab.datingapp.zustrichalnya.Views.Template to javafx.fxml;

}