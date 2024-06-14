package com.smakab.datingapp.zustrichalnya.Views.Profile;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.controls.SimpleCheckBoxControl;
import com.dlsc.formsfx.view.controls.SimpleRadioButtonControl;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.dlsc.formsfx.view.util.ViewMixin;
import com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo;
import javafx.scene.control.Button;

import java.util.List;

public class MainView implements ViewMixin {

    private static String reqField = "Це поле є обовʼязковим.";
    private GeneralInfo generalInfo;
    private Form generalInfoForm;
    private FormRenderer generalInfoFormRenderer;
    private Button save, reset;
    public MainView(GeneralInfo generalInfo, Button save, Button reset) {
        this.generalInfo = generalInfo;
        this.save = save;
        this.reset = reset;

        initGeneralInfoForm();
        init();
    }

    public void initGeneralInfoForm() {
        generalInfoForm = Form.of(
                Group.of(
                        Field.ofStringType(generalInfo.surnameProperty())
                                .label("Прізвище")
                                .required(reqField),
                        Field.ofStringType(generalInfo.nameProperty())
                                .label("Імʼя")
                                .required(reqField),
                        Field.ofStringType(generalInfo.patronymicProperty())
                                .label("По-батькові"),
                        Field.ofStringType(generalInfo.nicknameProperty())
                                .label("Нікнейм"),
                        Field.ofMultiSelectionType(generalInfo.nameVisibilityAllProperty(), generalInfo.nameVisibilityProperty())
                                .label("Що відображатиметься в анкетах?")
                                .render(new SimpleCheckBoxControl<>() {
                                    @Override
                                    protected void setWidth(double value) {
                                        super.setWidth(1500);
                                    }
                                })
                ),
                Group.of(

                        Field.ofBooleanType(generalInfo.isBirthdateVisibility())
                                .label("Показувати дату?")
                                .required(reqField)
                ),
                Group.of(
                        Field.ofSingleSelectionType(generalInfo.regionAllProperty(), generalInfo.regionProperty())
                                .label("Область")
                                .required(reqField),
                        Field.ofStringType(generalInfo.cityProperty())
                                .label("Місто")
                ),
                Group.of(
                        Field.ofSingleSelectionType(generalInfo.sexAllProperty(), generalInfo.sexProperty())
                                .label("Стать")
                                .required(reqField)
                                .render(new SimpleRadioButtonControl<>()),
                        Field.ofStringType(generalInfo.genderProperty())
                                .label("Гендер"),
                        Field.ofStringType(generalInfo.orientationProperty())
                                .label("Орієнтація")
                ),
                Group.of(
                        Field.ofBooleanType(generalInfo.isWorkingProperty())
                                .label("Зайнятість"),
                        Field.ofStringType(generalInfo.universityProperty())
                                .label("Місце навчання"),
                        Field.ofStringType(generalInfo.professionProperty())
                                .label("Професія")
                ),
                Group.of(
                        Field.ofMultiSelectionType(generalInfo.relationshipTypeAllProperty(), generalInfo.relationshipTypeProperty())
                                .label("Відкритість до заявок")
                                .render(new SimpleCheckBoxControl<>() {
                                    @Override
                                    protected void setWidth(double value) {
                                        super.setWidth(1500);
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
