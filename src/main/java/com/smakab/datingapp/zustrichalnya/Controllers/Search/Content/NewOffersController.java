package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Search.PersonComparable;
import com.smakab.datingapp.zustrichalnya.Models.Search.Request;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

import static com.smakab.datingapp.zustrichalnya.Controllers.Search.Content.TemplatesListController.directoryExists;
import static com.smakab.datingapp.zustrichalnya.Controllers.Search.Content.TemplatesListController.listDirectories;

public class NewOffersController extends TemplateContentClass {

    public Template model;

    public FlowPane greenFlagsContainer;
    public FlowPane redFlagsContainer;

    public Label personDescription, personName, personAge;
    public ImageView personImage;

    public Label tempName, tempGoal, tempDescription, tempType;

    private int currentPerson;
    private ArrayList<Request> people = new ArrayList<>();
    private ArrayList<String> peopleStrings = new ArrayList<>();

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
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setUserUUID(UUID uuid) {
        this.userUUID = uuid;

    }

    @Override
    public void loadModelData() {
        loadFromDB();
    }

    private void loadFromDB() {
        people.clear();
        String folderPath = "src\\main\\resources\\local-database\\"+userUUID+"\\";
        if(directoryExists(folderPath)) {
            try {
                peopleStrings.clear();

                HashSet<String> blockedPeople = new HashSet<>();

                File file = new File(folderPath, "blacklist");
                if (file.exists() && file.isFile()) {
                    try (Scanner scanner = new Scanner(file)) {

                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            blockedPeople.add(line);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                file = new File(folderPath, "requests");
                if (file.exists() && file.isFile()) {
                    try (Scanner scanner = new Scanner(file)) {

                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            peopleStrings.add(line);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                for (String personStr : peopleStrings) {
                    String[] personSplit = personStr.split("\\|");
                    String personUuid = personSplit[0];
                    String tempUuid = personSplit[1];

                    if(blockedPeople.contains(personUuid)) continue;

                    //person
                    File generalInfoFile = new File("src\\main\\resources\\local-database\\"+personUuid+"\\profile-data\\main.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo generalInfo = JsonUtil.fromJsonFile(generalInfoFile, com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo.class);

                    File personalityFile = new File("src\\main\\resources\\local-database\\"+personUuid+"\\profile-data\\personality.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.Personality personality = JsonUtil.fromJsonFile(personalityFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Personality.class);

                    File hobbiesFile = new File("src\\main\\resources\\local-database\\"+personUuid+"\\profile-data\\hobbies.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies hobbies = JsonUtil.fromJsonFile(hobbiesFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies.class);

                    File preferencesFile = new File("src\\main\\resources\\local-database\\"+personUuid+"\\profile-data\\preferences.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.Preferences preferences = JsonUtil.fromJsonFile(preferencesFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Preferences.class);

                    File viewsFile = new File("src\\main\\resources\\local-database\\"+personUuid+"\\profile-data\\views.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.Views views = JsonUtil.fromJsonFile(viewsFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Views.class);


                    Person person = new Person(UUID.fromString(personUuid), generalInfo, hobbies, personality, preferences, views);
                    //template
                    File generalInfoFileT = new File("src\\main\\resources\\local-database\\"+personUuid+"\\templates\\"+tempUuid+"\\main.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.GeneralInfo generalInfoT = JsonUtil.fromJsonFile(generalInfoFileT, com.smakab.datingapp.zustrichalnya.Models.Search.GeneralInfo.class);

                    File detailsFileT = new File("src\\main\\resources\\local-database\\"+personUuid+"\\templates\\"+tempUuid+"\\details.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Details detailsT = JsonUtil.fromJsonFile(detailsFileT, com.smakab.datingapp.zustrichalnya.Models.Search.Details.class);

                    File personalityFileT = new File("src\\main\\resources\\local-database\\"+personUuid+"\\templates\\"+tempUuid+"\\personality.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Personality personalityT = JsonUtil.fromJsonFile(personalityFileT, com.smakab.datingapp.zustrichalnya.Models.Search.Personality.class);

                    File hobbiesFileT = new File("src\\main\\resources\\local-database\\"+personUuid+"\\templates\\"+tempUuid+"\\hobbies.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Hobbies hobbiesT = JsonUtil.fromJsonFile(hobbiesFileT, com.smakab.datingapp.zustrichalnya.Models.Search.Hobbies.class);

                    File preferencesFileT = new File("src\\main\\resources\\local-database\\"+personUuid+"\\templates\\"+tempUuid+"\\preferences.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Preferences preferencesT = JsonUtil.fromJsonFile(preferencesFileT, com.smakab.datingapp.zustrichalnya.Models.Search.Preferences.class);

                    File viewsFileT = new File("src\\main\\resources\\local-database\\"+personUuid+"\\templates\\"+tempUuid+"\\views.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Views viewsT = JsonUtil.fromJsonFile(viewsFileT, com.smakab.datingapp.zustrichalnya.Models.Search.Views.class);

                    Template template = new Template(UUID.fromString(tempUuid), generalInfoT, detailsT, personalityT, hobbiesT, preferencesT, viewsT);

                    people.add(new Request(person, template));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!people.isEmpty()) {
            currentPerson = 0;
            setPersonInfoLabels();
            setTemplateInfoLabels();
        }
    }

    public HBox createFlag(String name, TreeSet<String> set, FlowPane flagContainer, String style) {

        HBox container = new HBox();
        container.setPadding(new Insets(5, 5, 5, 5));
        container.spacingProperty().set(10);
        container.getStylesheets().add("styles.css");
        container.getStyleClass().add("label-"+style+"flag");
        container.alignmentProperty().set(Pos.CENTER);

        EditableLabel label = new EditableLabel(name);
        label.setFont(new Font(20));

        container.getChildren().add(label);

        return container;
    }

    public void addFlag(FlowPane container, TreeSet<String> set, String style, String text) {
        container.getChildren().add(createFlag(text, set, container, style));
    }

    public void addGreenFlag(String text) {
        addFlag(greenFlagsContainer, people.get(currentPerson).template().getGeneralInfo().getGreenFlags(), "green", text);
    }

    public void addRegFlag(String text) {
        addFlag(redFlagsContainer, people.get(currentPerson).template().getGeneralInfo().getRedFlags(), "red", text);
    }

    private void setPersonInfoLabels() {
        if(people.isEmpty()) {
            personName.textProperty().set("");
            personDescription.textProperty().set("");
            personAge.textProperty().set("");

            personImage.imageProperty().set(new Image(getClass().getResourceAsStream("/icons/avatar.png")));
           return;
        }

        String name = people.get(currentPerson).person().getGeneralInfo().fullName();
        String desc = people.get(currentPerson).person().getGeneralInfo().getDescription();
        String age = String.valueOf(people.get(currentPerson).person().getGeneralInfo().getAge());

        if(name == null) name = "";
        if(desc == null) desc = "";

        personName.textProperty().set(name);
        personDescription.textProperty().set(desc);
        personAge.textProperty().set(age);

        ArrayList<HashMap<String, Boolean>> photos = people.get(currentPerson).person().getGeneralInfo().
                getPhotos();
        if(photos == null || photos.isEmpty()) personImage.imageProperty().set(new Image(getClass().getResourceAsStream("/icons/avatar.png")));
        else personImage.imageProperty().set(new Image(photos.getFirst().keySet().toArray(new String[1])[0]));
    }

    private void setTemplateInfoLabels() {
        if(people.isEmpty()) {
            tempName.textProperty().set("");
            tempGoal.textProperty().set("");
            tempDescription.textProperty().set("");
            tempType.textProperty().set("");

            greenFlagsContainer.getChildren().clear();
            redFlagsContainer.getChildren().clear();
            return;
        }
        tempName.textProperty().set(people.get(currentPerson).template().getGeneralInfo().getName());
        tempGoal.textProperty().set(people.get(currentPerson).template().getGeneralInfo().getPurpose());
        tempDescription.textProperty().set(people.get(currentPerson).template().getGeneralInfo().getDescription());
        tempType.textProperty().set(people.get(currentPerson).template().getGeneralInfo().getRelationshipType());

        greenFlagsContainer.getChildren().clear();
        redFlagsContainer.getChildren().clear();

        people.get(currentPerson).template().getGeneralInfo().getGreenFlags().forEach(this::addGreenFlag);
        people.get(currentPerson).template().getGeneralInfo().getRedFlags().forEach(this::addRegFlag);
    }


    public void openProfileShowcase(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Profile/ProfileShowcase.fxml"));
        Parent view = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Огляд профілю");
        stage.setScene(new Scene(view, 1280, 900));
        stage.show();
    }

    public void nextPerson() {
        if(people.isEmpty()) {
            setTemplateInfoLabels();
            setPersonInfoLabels();
            return;
        }
        currentPerson++;
        if(currentPerson >= people.size()) currentPerson = 0;
        setPersonInfoLabels();
        setTemplateInfoLabels();
    }

    public void removePerson() {
        if(people.isEmpty()) {
            setTemplateInfoLabels();
            setPersonInfoLabels();
            return;
        }
        try (FileWriter fw = new FileWriter("src\\main\\resources\\local-database\\"+userUUID+"\\blacklist", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(people.get(currentPerson).person().uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        people.remove(currentPerson);
        nextPerson();
    }

    public void likePerson() {
    }

    public void prevPerson() {
        if(people.isEmpty()) return;
        currentPerson--;
        if(currentPerson < 0) currentPerson = people.size()-1;
        setPersonInfoLabels();
        setTemplateInfoLabels();
    }
}
