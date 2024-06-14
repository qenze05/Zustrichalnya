package com.smakab.datingapp.zustrichalnya.Controllers.Chat;

import com.smakab.datingapp.zustrichalnya.Controllers.BaseController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class ChatController extends BaseController {
    @Override
    public void setProfileUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
