package com.smakab.datingapp.zustrichalnya.Controllers;

import javafx.fxml.Initializable;

import java.util.UUID;

public abstract class BaseController implements Initializable {
    public UUID uuid;
    public abstract void setProfileUUID(UUID uuid);
}
