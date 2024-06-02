package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.PersonDataUser;
import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ProfileContentClass implements PersonDataUser, Initializable {
    public Person person = null;
    public ProfileDataDelegate delegate = null;

    public abstract void initialize(URL url, ResourceBundle resourceBundle);
    public abstract void setDelegate(ProfileDataDelegate delegate);
    public abstract void setPerson(Person person);
}
