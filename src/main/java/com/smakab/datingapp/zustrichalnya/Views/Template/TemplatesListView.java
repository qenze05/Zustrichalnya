package com.smakab.datingapp.zustrichalnya.Views.Template;

import com.smakab.datingapp.zustrichalnya.Controllers.Search.Content.TemplatesListController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.UUID;

public class TemplatesListView {
    public TemplatesListController controller;

    public TemplatesListView(TemplatesListController controller) {
        this.controller = controller;
    }


    public VBox createTemplate(String name, String desc, UUID uuid) {
        VBox container = new VBox();
        VBox subContainer = new VBox();
        Label header = new Label(name);
        Separator separator = new Separator(Orientation.HORIZONTAL);
        Label description = new Label(desc);
        Button propositions = new Button("0");

        container.getStylesheets().add("styles.css");
        container.getStyleClass().add("search-template");

        container.alignmentProperty().set(Pos.TOP_CENTER);
        container.prefWidthProperty().set(350);
        container.prefHeightProperty().set(400);
        container.spacingProperty().set(10);
        container.paddingProperty().set(new Insets(20, 20, 0, 20));
        container.idProperty().set(uuid.toString());

        subContainer.alignmentProperty().set(Pos.CENTER);
        subContainer.paddingProperty().set(new Insets(0, 0, 0, 5));
        subContainer.spacingProperty().set(5);
        subContainer.getStyleClass().add("search-template-inner");

        header.prefWidthProperty().set(300);
        header.fontProperty().set(new Font(25));

        separator.prefWidthProperty().set(200);

        description.alignmentProperty().set(Pos.TOP_LEFT);
        description.prefHeightProperty().set(300);
        description.prefWidthProperty().set(300);
        description.fontProperty().set(new Font(20));
        description.wrapTextProperty().set(true);

        propositions.fontProperty().set(new Font(20));
        propositions.prefWidthProperty().set(60);
        propositions.prefHeightProperty().set(60);
        propositions.getStyleClass().add("requests-button");

        subContainer.getChildren().addAll(header, separator, description);
        container.getChildren().addAll(subContainer, propositions);

        return container;
    }
}
