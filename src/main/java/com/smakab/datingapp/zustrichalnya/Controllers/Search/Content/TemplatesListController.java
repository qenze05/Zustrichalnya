package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Views.Template.TemplatesListView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;

public class TemplatesListController implements Initializable {
    private TemplateDataDelegate delegate;

    public FlowPane container;
    TemplatesListView view;
    HashMap<UUID, Template> templates;
    private BooleanProperty isTemplateSelected = new SimpleBooleanProperty(false);
    private Template selectedTemplate;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.view = new TemplatesListView(this);

        this.container.onMouseClickedProperty().set(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2){
                selectedTemplate = null;
                isTemplateSelected.set(false);
                System.out.println("unselected");
            }

        });
    }

    public Template getSelectedTemplate() {
        return selectedTemplate;
    }

    public void setDelegate(TemplateDataDelegate delegate) {
        this.delegate = delegate;
    }

    public HashMap<UUID, Template> getTemplates() {
        return templates;
    }

    public void setTemplates(HashMap<UUID, Template> templates) {
        this.templates = templates;
    }

    public void loadTemplates() {
        if(templates == null) {
            //TODO: load templates from database
            this.templates = new HashMap<>();
        }

        templates.forEach((id, temp) -> {
            VBox template = view.createTemplate(temp.getGeneralInfo().getName(), temp.getGeneralInfo().getDescription(), id);

            template.onMouseClickedProperty().set(mouseEvent -> {
                isTemplateSelected.set(true);
                selectedTemplate = templates.get(id);
            });
            container.getChildren().add(container.getChildren().size()-1, template);
        });

    }

    public BooleanProperty isTemplateSelectedProperty() {
        return isTemplateSelected;
    }

    public void createTemplate() {
        UUID id = UUID.randomUUID();
        VBox template = view.createTemplate("Заголовок", "", id);

        templates.put(id, new Template(id));

        template.onMouseClickedProperty().set(mouseEvent -> {
            isTemplateSelected.set(true);
            selectedTemplate = templates.get(id);

        });

        container.getChildren().add(container.getChildren().size()-1, template);
    }

}
