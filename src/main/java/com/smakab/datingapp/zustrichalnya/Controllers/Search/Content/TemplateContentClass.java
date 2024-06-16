package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataUser;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public abstract class TemplateContentClass implements Initializable, TemplateDataUser {

    public Template template = null;
    public TemplateDataDelegate delegate = null;
    public UUID userUUID = null;

    public abstract void initialize(URL url, ResourceBundle resourceBundle);
    public abstract void setDelegate(TemplateDataDelegate delegate);
    public abstract void setModel(Template template);
    public abstract void setName(String name);
    public abstract void setUserUUID(UUID uuid);
    public abstract void loadModelData();
}
