package com.smakab.datingapp.zustrichalnya.Controllers.Search;

import com.smakab.datingapp.zustrichalnya.Controllers.BaseController;
import com.smakab.datingapp.zustrichalnya.Controllers.Search.Content.TemplateContentClass;
import com.smakab.datingapp.zustrichalnya.Controllers.Search.Content.TemplatesListController;
import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;

public class SearchController extends BaseController implements Initializable, TemplateDataDelegate {

    @FXML
    public Button mainB, selectedTemplateB, generalInfoB, personalityB, hobbiesB, prefB, viewsB, newOffersB;
    public AnchorPane container;
    public TemplatesListController mainVC;
    public HashMap<UUID, Template> templates;
    public UUID personUUID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void bindButtons() {

        selectedTemplateB.disableProperty().bind(mainVC.isTemplateSelectedProperty().not());
        generalInfoB.disableProperty().bind(mainVC.isTemplateSelectedProperty().not());
        personalityB.disableProperty().bind(mainVC.isTemplateSelectedProperty().not());
        hobbiesB.disableProperty().bind(mainVC.isTemplateSelectedProperty().not());
        prefB.disableProperty().bind(mainVC.isTemplateSelectedProperty().not());
        viewsB.disableProperty().bind(mainVC.isTemplateSelectedProperty().not());
    }

    public void switchMenu(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(source.equals(mainB)) {
            loadMainView();
        } else if (source.equals(selectedTemplateB)) {
            loadView("Template/MainView");
        } else if (source.equals(generalInfoB)) {
            loadView("Template/DetailsView");
        } else if (source.equals(personalityB)) {
            loadView("Template/PersonalityView");
        } else if (source.equals(hobbiesB)) {
            loadView("Template/HobbiesView");
        } else if (source.equals(prefB)) {
            loadView("Template/PreferencesView");
        } else if (source.equals(viewsB)) {
            loadView("Template/ViewsView");
        } else if (source.equals(newOffersB)) {
            loadView("NewOffersView");
        }
    }

    public void loadMainView() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Search/Content/SearchMainView.fxml"));
            Parent view = loader.load();

            //TODO: implement loading from db instead of saving templates locally
            if(mainVC == null) {
                mainVC = loader.getController();
            } else {
                templates = mainVC.getTemplates();
                mainVC = loader.getController();
                mainVC.setTemplates(templates);
            }

            mainVC.personUUID = uuid;
            System.out.println("main uuid" + uuid);


            mainVC.loadTemplates();


            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);

            container.getChildren().setAll(view);


            bindButtons();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadView(String menu) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Search/Content/"+menu+".fxml"));
            Parent view = loader.load();

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);

            configureContentView(loader.getController());


            container.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureContentView(TemplateContentClass controller) {
        controller.setModel(this.mainVC.getSelectedTemplate());
        controller.loadModelData();

        controller.setDelegate(this);
        controller.setUserUUID(this.uuid);

        if(this.mainVC.getSelectedTemplate() != null) {
            controller.setName(this.mainVC.getSelectedTemplate().getGeneralInfo().getName());
        }
    }

    @Override
    public void didUpdateData(Template data) {

    }

    @Override
    public void setProfileUUID(UUID uuid){
        this.uuid = uuid;
        System.out.println(uuid);
    }
}
