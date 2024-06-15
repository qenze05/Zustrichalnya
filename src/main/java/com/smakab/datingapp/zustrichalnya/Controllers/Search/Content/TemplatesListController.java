package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo;
import com.smakab.datingapp.zustrichalnya.Models.Profile.Personality;
import com.smakab.datingapp.zustrichalnya.Models.Search.Searcher;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Views.Template.TemplatesListView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class TemplatesListController implements Initializable {
    private TemplateDataDelegate delegate;

    public FlowPane container;
    TemplatesListView view;
    HashMap<UUID, Template> templates;
    public UUID personUUID;

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
        if(templates != null && !templates.isEmpty()) saveTemplates();
        loadTemplatesFromFile();

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
        saveTemplates();
        UUID id = UUID.randomUUID();
        VBox template = view.createTemplate("Заголовок", "", id);

        templates.put(id, new Template(id));

        template.onMouseClickedProperty().set(mouseEvent -> {
            isTemplateSelected.set(true);
            selectedTemplate = templates.get(id);

        });

        container.getChildren().add(container.getChildren().size()-1, template);
    }

    public void saveTemplates() {
        String folderPath = "src\\main\\resources\\local-database\\"+personUUID+"\\templates";
        if(directoryExists(folderPath)) {
            try {
                clearDirectory(folderPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Hi!");
        if (templates == null || templates.isEmpty()) return;
        for(Map.Entry<UUID, Template> template : templates.entrySet())
        try {
            UUID uuid = template.getKey();
            template.getValue().getDetails().writeInfoToFile("src\\main\\resources\\local-database\\"+this.personUUID+"\\templates\\"+uuid+"\\details.json");
            template.getValue().getGeneralInfo().writeInfoToFile("src\\main\\resources\\local-database\\"+this.personUUID+"\\templates\\"+uuid+"\\main.json");
            template.getValue().getPersonality().writeInfoToFile("src\\main\\resources\\local-database\\"+this.personUUID+"\\templates\\"+uuid+"\\personality.json");
            template.getValue().getHobbies().writeInfoToFile("src\\main\\resources\\local-database\\"+this.personUUID+"\\templates\\"+uuid+"\\hobbies.json");
            template.getValue().getPreferences().writeInfoToFile("src\\main\\resources\\local-database\\"+this.personUUID+"\\templates\\"+uuid+"\\preferences.json");
            template.getValue().getViews().writeInfoToFile("src\\main\\resources\\local-database\\"+this.personUUID+"\\templates\\"+uuid+"\\views.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello there");
        Searcher searcher = new Searcher(selectedTemplate, personUUID);
        if(selectedTemplate == null){
            System.out.println("whoopsie, null");
            return;
        }
        searcher.tempSout();
    }

    public void loadTemplatesFromFile(){
        if(templates != null ) templates.clear();
        else templates = new HashMap<>();
        String folderPath = "src\\main\\resources\\local-database\\"+personUUID+"\\templates";
        if(directoryExists(folderPath)) {
            try {
                List<String> folderNames = listDirectories(folderPath);
                for(String folderName : folderNames){

                    File generalInfoFile = new File("src\\main\\resources\\local-database\\"+personUUID+"\\templates\\"+folderName+"\\main.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.GeneralInfo generalInfo = JsonUtil.fromJsonFile(generalInfoFile, com.smakab.datingapp.zustrichalnya.Models.Search.GeneralInfo.class);

                    File detailsFile = new File("src\\main\\resources\\local-database\\"+personUUID+"\\templates\\"+folderName+"\\details.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Details details = JsonUtil.fromJsonFile(detailsFile, com.smakab.datingapp.zustrichalnya.Models.Search.Details.class);

                    File personalityFile = new File("src\\main\\resources\\local-database\\"+personUUID+"\\templates\\"+folderName+"\\personality.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Personality personality = JsonUtil.fromJsonFile(personalityFile, com.smakab.datingapp.zustrichalnya.Models.Search.Personality.class);

                    File hobbiesFile = new File("src\\main\\resources\\local-database\\"+personUUID+"\\templates\\"+folderName+"\\hobbies.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Hobbies hobbies = JsonUtil.fromJsonFile(hobbiesFile, com.smakab.datingapp.zustrichalnya.Models.Search.Hobbies.class);

                    File preferencesFile = new File("src\\main\\resources\\local-database\\"+personUUID+"\\templates\\"+folderName+"\\preferences.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Preferences preferences = JsonUtil.fromJsonFile(preferencesFile, com.smakab.datingapp.zustrichalnya.Models.Search.Preferences.class);

                    File viewsFile = new File("src\\main\\resources\\local-database\\"+personUUID+"\\templates\\"+folderName+"\\views.json");
                    com.smakab.datingapp.zustrichalnya.Models.Search.Views views = JsonUtil.fromJsonFile(viewsFile, com.smakab.datingapp.zustrichalnya.Models.Search.Views.class);

                    templates.put(UUID.fromString(folderName), new Template(UUID.fromString(folderName), generalInfo, details, personality, hobbies, preferences, views));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static List<String> listDirectories(String path) throws IOException {
        List<String> folderNames = new ArrayList<>();
        Path directory = Paths.get(path);

        if (Files.exists(directory) && Files.isDirectory(directory)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, Files::isDirectory)) {
                for (Path entry : stream) {
                    folderNames.add(entry.getFileName().toString());
                }
            }
        } else {
            throw new IOException("Directory does not exist or is not a directory: " + path);
        }

        return folderNames;
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

    // Custom serializer for UUID
    public static class UUIDSerializer extends com.fasterxml.jackson.databind.JsonSerializer<UUID> {
        @Override
        public void serialize(UUID value, com.fasterxml.jackson.core.JsonGenerator gen, com.fasterxml.jackson.databind.SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

}
