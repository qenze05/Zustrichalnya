package com.smakab.datingapp.zustrichalnya.Controllers.Chat;

import com.smakab.datingapp.zustrichalnya.Controllers.BaseController;
import com.smakab.datingapp.zustrichalnya.Database.DatabaseConnection;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo;
import com.smakab.datingapp.zustrichalnya.Models.Search.Request;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Models.Settings.SettingsModel;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.smakab.datingapp.zustrichalnya.Controllers.Search.Content.TemplatesListController.directoryExists;

public class ChatController extends BaseController {

    public AnchorPane messagesContainer;
    public VBox peopleContainer;

    public record PersonInfo(GeneralInfo generalInfo, SettingsModel contacts) {}

    public ArrayList<PersonInfo> people = new ArrayList<>();

    @Override
    public void setProfileUUID(UUID uuid) {
        this.uuid = uuid;
        loadRequests();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public HBox createContainer(PersonInfo data) {

        HBox container = new HBox();
        container.prefHeightProperty().set(100);
        container.prefWidthProperty().set(575);
        container.maxHeightProperty().set(100);
        container.spacingProperty().set(10);
        container.alignmentProperty().set(Pos.CENTER_LEFT);

        container.onMouseClickedProperty().set(mouseEvent -> {
            try {
                openMenu(data.contacts);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Image img;
        ArrayList<HashMap<String, Boolean>> photos = data.generalInfo.
                getPhotos();
        if(photos == null || photos.isEmpty()) img = new Image(getClass().getResourceAsStream("/icons/avatar.png"));
        else img = new Image(photos.getFirst().keySet().toArray(new String[1])[0]);

        ImageView imageView = new ImageView(img);
        imageView.fitHeightProperty().set(100);
        imageView.fitWidthProperty().set(100);

        Label name = new Label(data.generalInfo.fullName());
        name.fontProperty().set(new Font(25));

        container.getChildren().addAll(imageView, name);

        return container;
    }

    public void openMenu(SettingsModel info) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Chat/ContactsView.fxml"));
        Parent view = loader.load();

        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setRightAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);

        DialogController controller = loader.getController();
        controller.setLabels(info.getTelegram(), info.getInstagram(), info.getZustrichat());


        messagesContainer.getChildren().setAll(view);
    }

    public void loadRequests() {
        String folderPath = "src\\main\\resources\\local-database\\"+uuid+"\\";
        if(directoryExists(folderPath)) {
            try {

               ArrayList<String> requests = new ArrayList<>();

                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM mutuals WHERE personFrom = ? OR personTo = ?");
                statement.setString(1, uuid.toString());
                statement.setString(2, uuid.toString());

                TreeSet<UUID> uuids = new TreeSet<>();

                ResultSet set = statement.executeQuery();
                if(set != null) {
                    while(set.next()) {
                        UUID from = UUID.fromString(set.getString("personFrom"));
                        UUID to = UUID.fromString(set.getString("personTo"));
                        uuids.add(from);
                        uuids.add(to);
                    }
                }

                uuids.remove(uuid);

                if(uuids.isEmpty()) return;

                people.clear();
                uuids.forEach(x -> {
                    try {
                        File generalInfoFile = new File("src\\main\\resources\\local-database\\"+x+"\\profile-data\\main.json");
                        com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo generalInfo = JsonUtil.fromJsonFile(generalInfoFile, com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo.class);

                        File contactsFile = new File("src\\main\\resources\\local-database\\"+x+"\\settings\\contacts.json");
                        com.smakab.datingapp.zustrichalnya.Models.Settings.SettingsModel contacts = JsonUtil.fromJsonFile(contactsFile, com.smakab.datingapp.zustrichalnya.Models.Settings.SettingsModel.class);

                        people.add(new PersonInfo(generalInfo, contacts));
                        peopleContainer.getChildren().add(createContainer(people.getLast()));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
