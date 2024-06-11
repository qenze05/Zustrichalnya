package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Views.Template.MainView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class TemplateController extends TemplateContentClass {
    public Button saveB;
    public Button removeB;
    public VBox infoContainer;
    public FlowPane greenFlagsContainer;
    public FlowPane redFlagsContainer;
    MainView view;
    Template model;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void setDelegate(TemplateDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Template template) {
        this.model = template;
        this.view = new MainView(model.getGeneralInfo(), saveB, removeB);
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void loadModelData() {
        infoContainer.getChildren().add(0, view.getGeneralInfoFormRenderer());
        model.getGeneralInfo().getGreenFlags().forEach(name -> greenFlagsContainer.getChildren().add(view.createFlag(name, model.getGeneralInfo().getGreenFlags(), greenFlagsContainer, "green")));
        model.getGeneralInfo().getRedFlags().forEach(name -> redFlagsContainer.getChildren().add(view.createFlag(name, model.getGeneralInfo().getRedFlags(), redFlagsContainer, "red")));

    }

    public void addFlag(FlowPane container, TreeSet<String> set, String style) {
        container.getChildren().add(view.createFlag("###", set, container, style));
    }

    public void addGreenFlag() {
        addFlag(greenFlagsContainer, model.getGeneralInfo().getGreenFlags(), "green");
    }

    public void addRegFlag() {
        addFlag(redFlagsContainer, model.getGeneralInfo().getRedFlags(), "red");
    }
}
