package com.smakab.datingapp.zustrichalnya.Views.Settings;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.controls.SimpleCheckBoxControl;
import com.dlsc.formsfx.view.controls.SimpleRadioButtonControl;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.dlsc.formsfx.view.util.ViewMixin;
import com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo;
import com.smakab.datingapp.zustrichalnya.Models.Settings.SettingsModel;
import javafx.scene.control.Button;

import java.util.List;

public class MainView implements ViewMixin {

    private static String reqField = "Це поле є обовʼязковим.";
    private SettingsModel model;
    private Form generalInfoForm;
    private FormRenderer generalInfoFormRenderer;
    private Button save, reset;
    public MainView(SettingsModel model, Button save, Button reset) {
        this.model = model;
        this.save = save;
        this.reset = reset;

        initGeneralInfoForm();
        init();
    }

    public void initGeneralInfoForm() {
        generalInfoForm = Form.of(
                Group.of(
                        Field.ofStringType(model.telegramProperty())
                                .label("Telegram"),
                        Field.ofStringType(model.instagramProperty())
                                .label("Instagram"),
                        Field.ofStringType(model.zustrichatProperty())
                                .label("Зустрічат")
                                .required(reqField)

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
    public void initializeParts() {
        generalInfoFormRenderer = new FormRenderer(generalInfoForm);
    }

    @Override
    public void setupBindings() {
        generalInfoFormRenderer.setFillWidth(true);
        save.disableProperty().bind(getGeneralInfoForm().persistableProperty().not());
        reset.disableProperty().bind(getGeneralInfoForm().changedProperty().not());
    }

    @Override
    public void layoutParts() {

    }
}
