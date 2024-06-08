package com.smakab.datingapp.zustrichalnya.Views.Template;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.controls.SimpleRadioButtonControl;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.dlsc.formsfx.view.util.ViewMixin;
import com.smakab.datingapp.zustrichalnya.Models.Search.GeneralInfo;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
public class MainView implements ViewMixin {

    private GeneralInfo model;
    private Form generalInfoForm;
    private FormRenderer generalInfoFormRenderer;
    private Button save, reset;
    public MainView(GeneralInfo generalInfo, Button save, Button reset) {
        this.model = generalInfo;
        this.save = save;
        this.reset = reset;

        initGeneralInfoForm();
        init();
    }

    public void initGeneralInfoForm() {
        String reqField = "Це поле є обовʼязковим.";
        generalInfoForm = Form.of(
                Group.of(
                        Field.ofStringType(model.nameProperty())
                                .label("Назва")
                                .required(reqField),
                        Field.ofStringType(model.purposeProperty())
                                .label("Мета"),
                        Field.ofStringType(model.descriptionProperty())
                                .label("Опис")
                                .span(10),
                        Field.ofSingleSelectionType(model.relationshipTypeAllProperty(), model.relationshipTypeProperty())
                                .label("Тип")
                                .required(reqField)
                                .render(new SimpleRadioButtonControl<>() {
                                    @Override
                                    protected void setWidth(double value) {
                                        super.setWidth(1200);
                                    }
                                })
                )
        );
    }

    public FormRenderer getGeneralInfoFormRenderer() {
        return generalInfoFormRenderer;
    }

    public Form getGeneralInfoForm() {
        return generalInfoForm;
    }

    @Override
    public List<String> getStylesheets() {
        return null;
    }

    @Override
    public void initializeSelf() {
//        getStylesheets().add(getClass().getResource("style.css").toExternalForm());
//        generalInfoFormRenderer.getStyleClass().add("form-style");
//        generalInfoFormRenderer.styleProperty().set("-fx-background-color: transparent;");
    }

    @Override
    public void initializeParts() {
        generalInfoFormRenderer = new FormRenderer(generalInfoForm);
    }

    @Override
    public void setupBindings() {
        generalInfoFormRenderer.setFillWidth(true);
        save.disableProperty().bind(getGeneralInfoForm().persistableProperty().not());
        reset.disableProperty().bind(getGeneralInfoForm().changedProperty().not());

        save.onActionProperty().set(actionEvent -> getGeneralInfoForm().persist());
        reset.onActionProperty().set(actionEvent -> getGeneralInfoForm().reset());
    }

    @Override
    public void layoutParts() {

    }

    public HBox createFlag(String name, TreeSet<String> set, FlowPane flagContainer) {

        HBox container = new HBox();
        container.setPadding(new Insets(5, 5, 5, 5));
        container.spacingProperty().set(10);
        container.styleProperty().set("-fx-background-color: white;");
        container.alignmentProperty().set(Pos.CENTER);

        EditableLabel label = new EditableLabel(name);
        label.setFont(new Font(20));
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renameFlag(label.backup, t1, set);
            } else {
                label.textProperty().set(label.backup);
            }
        });

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeFlag(label.getText(), set);
            flagContainer.getChildren().remove(container);
        });

        container.getChildren().add(label);
        container.getChildren().add(delete);

        return container;
    }
}
