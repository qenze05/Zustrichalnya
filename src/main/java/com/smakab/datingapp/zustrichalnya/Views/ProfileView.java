package com.smakab.datingapp.zustrichalnya.Views;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.model.validators.CustomValidator;
import com.dlsc.formsfx.view.controls.SimpleCheckBoxControl;
import com.dlsc.formsfx.view.controls.SimpleRadioButtonControl;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.dlsc.formsfx.view.util.ViewMixin;
import com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.ScrollPane;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ProfileView implements ViewMixin {

    private static String reqField = "Це поле є обовʼязковим.";
    private GeneralInfo generalInfo;
    private Form generalInfoForm;
    private FormRenderer generalInfoFormRenderer;
    public ProfileView(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
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
                        Field.ofMultiSelectionType(generalInfo.nameVisibilityProperty())
                                .label("Що відображатиметься в анкетах?")
                                .render(new SimpleCheckBoxControl<>() {
                                    @Override
                                    protected void setWidth(double value) {
                                        super.setWidth(1200);
                                    }
                                })
                ),
                Group.of(
                        Field.ofDate(generalInfo.getBirthdate())
                                .label("Дата народження")
                                .required(reqField),
                        Field.ofBooleanType(generalInfo.isBirthdateVisibility())
                                .label("Показувати дату?")
                                .required(reqField)
                ),
                Group.of(
                        Field.ofStringType(generalInfo.regionProperty())
                                .label("Область")
                                .required(reqField),
                        Field.ofStringType(generalInfo.cityProperty())
                                .label("Місто")
                                .required(reqField)
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
                )
        );
    }

    public FormRenderer getGeneralInfoFormRenderer() {
        return generalInfoFormRenderer;
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
        System.out.println(getGeneralInfoFormRenderer().widthProperty().get());

    }

    @Override
    public void layoutParts() {

    }
}
