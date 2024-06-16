package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content.ProfileShowcase;
import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Search.PersonComparable;
import com.smakab.datingapp.zustrichalnya.Models.Search.Searcher;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Views.Template.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static com.smakab.datingapp.zustrichalnya.Controllers.Search.Content.TemplatesListController.listDirectories;

public class TemplateController extends TemplateContentClass {
    public Button saveB, removeB;
    public VBox infoContainer;
    public FlowPane greenFlagsContainer, redFlagsContainer;
    public Label personDescription, personName, personAge;
    public ImageView personImage;
    private int currentPerson;
    private ArrayList<PersonComparable> people = new ArrayList<>();
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
    public void setUserUUID(UUID uuid) {
        this.userUUID = uuid;

    }

    public static boolean directoryExists(String path) {
        Path directory = Paths.get(path);
        return Files.exists(directory) && Files.isDirectory(directory);
    }
    public static void clearDirectory(String path) throws IOException {
        Path directory = Paths.get(path);

        if (Files.exists(directory) && Files.isDirectory(directory)) {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc == null) {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    } else {
                        throw exc;
                    }
                }
            });
        } else {
            throw new IOException("Directory does not exist or is not a directory: " + path);
        }
    }
    public void saveToFile() {
        String folderPath = "src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\";

        if(directoryExists(folderPath)) {
            try {
                clearDirectory(folderPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (PersonComparable personComp : people) {
            Person person = personComp.getPerson();
            double comp = personComp.getCompatability();

            try{
                person.getGeneralInfo().writeInfoToFile("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+person.uuid+"\\main.json");
                person.getHobbies().writeInfoToFile("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+person.uuid+"\\hobbies.json");
                person.getPersonality().writeInfoToFile("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+person.uuid+"\\personality.json");
                person.getPreferences().writeInfoToFile("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+person.uuid+"\\preferences.json");
                person.getViews().writeInfoToFile("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+person.uuid+"\\views.json");

                try (FileWriter writer = new FileWriter("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+person.uuid+"\\comp", false)) {
                    writer.write(String.valueOf(comp));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }catch (IOException e) {
                throw new RuntimeException();
            }

        }
    }

    public void loadFromDB() {
        people.clear();
        String folderPath = "src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\";
        if(directoryExists(folderPath)) {
            try {
                List<String> folderNames = listDirectories(folderPath);
                for(String folderName : folderNames){

                    String shownPerson = "src\\main\\resources\\local-database\\"+folderName+"\\";

                    boolean found = false;
                    File file = new File(shownPerson, "blacklist");
                    if (file.exists() && file.isFile()) {
                        try (Scanner scanner = new Scanner(file)) {

                            while (scanner.hasNextLine()) {
                                String line = scanner.nextLine();
                                if (line.contains(userUUID.toString())) {
                                    found = true;
                                    break;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if(found) continue;

                    File generalInfoFile = new File("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+folderName+"\\main.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo generalInfo = JsonUtil.fromJsonFile(generalInfoFile, com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo.class);

                    File personalityFile = new File("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+folderName+"\\personality.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.Personality personality = JsonUtil.fromJsonFile(personalityFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Personality.class);

                    File hobbiesFile = new File("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+folderName+"\\hobbies.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies hobbies = JsonUtil.fromJsonFile(hobbiesFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies.class);

                    File preferencesFile = new File("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+folderName+"\\preferences.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.Preferences preferences = JsonUtil.fromJsonFile(preferencesFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Preferences.class);

                    File viewsFile = new File("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+folderName+"\\views.json");
                    com.smakab.datingapp.zustrichalnya.Models.Profile.Views views = JsonUtil.fromJsonFile(viewsFile, com.smakab.datingapp.zustrichalnya.Models.Profile.Views.class);

                    double comp = 0;
                    try (Scanner scanner = new Scanner(new File("src\\main\\resources\\local-database\\"+userUUID+"\\foundPeople\\"+model.getUuid()+"\\"+folderName+"\\comp"))) {
                        while (scanner.hasNextDouble()) {
                            comp = scanner.nextDouble();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    people.add(new PersonComparable(new Person(UUID.fromString(folderName),
                            generalInfo, hobbies, personality, preferences, views), comp));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!people.isEmpty()) {
            currentPerson = 0;
            setPersonInfoLabels();
            saveToFile();
        }
    }

    @Override
    public void loadModelData() {
        infoContainer.getChildren().add(0, view.getGeneralInfoFormRenderer());
        model.getGeneralInfo().getGreenFlags().forEach(name -> greenFlagsContainer.getChildren().add(view.createFlag(name, model.getGeneralInfo().getGreenFlags(), greenFlagsContainer, "green")));
        model.getGeneralInfo().getRedFlags().forEach(name -> redFlagsContainer.getChildren().add(view.createFlag(name, model.getGeneralInfo().getRedFlags(), redFlagsContainer, "red")));
        loadFromDB();

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

    public void openProfileShowcase() throws IOException {
        if(people == null || people.isEmpty() || currentPerson >= people.size() || currentPerson < 0) return;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Profile/ProfileShowcase.fxml"));
        Parent view = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Огляд профілю");
        stage.setScene(new Scene(view, 1280, 900));
        stage.show();

        ProfileShowcase controller = loader.getController();
        controller.setModel(people.get(currentPerson).getPerson());
        controller.loadModelData();

    }

    public void findPeople(ActionEvent actionEvent) {
        System.out.println("user uuid "+this.userUUID);
        Searcher searcher = new Searcher(this.model, this.userUUID);
        people = searcher.search();
        searcher.tempSout();

        if(!people.isEmpty()) {
            currentPerson = 0;
            setPersonInfoLabels();
            saveToFile();
            loadFromDB();
        }
    }

    public void nextPerson() {
        if(people.isEmpty()) return;
        currentPerson++;
        if(currentPerson >= people.size()) currentPerson = 0;
        setPersonInfoLabels();
    }

    public void removePerson() {
        if(people.isEmpty()) return;
        try (FileWriter fw = new FileWriter("src\\main\\resources\\local-database\\"+userUUID+"\\blacklist", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(people.get(currentPerson).getPerson().uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        removeFromAL();
    }

    private void removeFromAL() {
        if(people.isEmpty()) return;
        people.remove(currentPerson);
        nextPerson();
    }



    public void likePerson() {
        if(people.isEmpty()) return;

        Person selectedPerson = people.get(currentPerson).getPerson();

        String folderPath = "src\\main\\resources\\local-database\\"+selectedPerson.uuid+"\\";

        File file = new File(folderPath, "requests");
        if (file.exists() && file.isFile()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.equals(userUUID+"|"+model.getUuid())) {
                        removeFromAL();
                        return;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter fw = new FileWriter(folderPath+"requests", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(userUUID+"|"+model.getUuid());
        } catch (IOException e) {
            e.printStackTrace();
        }
        removeFromAL();

    }

    public void prevPerson() {
        if(people.isEmpty()) return;
        currentPerson--;
        if(currentPerson < 0) currentPerson = people.size()-1;
        setPersonInfoLabels();
    }

    private void setPersonInfoLabels() {
        String name = people.get(currentPerson).getPerson().getGeneralInfo().fullName();
        String desc = people.get(currentPerson).getPerson().getGeneralInfo().getDescription();
        String age = String.valueOf(people.get(currentPerson).getPerson().getGeneralInfo().getAge());

        if(name == null) name = "";
        if(desc == null) desc = "";

        personName.textProperty().set(name);
        personDescription.textProperty().set(desc);
        personAge.textProperty().set(age);

        ArrayList<HashMap<String, Boolean>> photos = people.get(currentPerson).getPerson().getGeneralInfo().
                getPhotos();
        if(photos == null || photos.isEmpty()) personImage.imageProperty().set(new Image(getClass().getResourceAsStream("/icons/avatar.png")));
        else personImage.imageProperty().set(new Image(photos.getFirst().keySet().toArray(new String[1])[0]));
    }
}
