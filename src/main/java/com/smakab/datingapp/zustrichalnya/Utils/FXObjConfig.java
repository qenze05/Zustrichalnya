//package com.smakab.datingapp.zustrichalnya.Utils;
//
//import javafx.beans.value.ChangeListener;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//public class FXObjConfig {
//
//    public static void configImageView(ImageView view, Image image) {
//        double oldImageWidth = image.getWidth(), oldImageHeight = image.getHeight();            //saving the original image size and ratio
//        double imageRatio = oldImageWidth / oldImageHeight;
//
//        Rectangle2D imagePane = view.getViewport();
//
//        view.setImage(image);
//        ChangeListener<Number> listener = (obs, ov, nv) -> {
//            double paneWidth = imagePane.getWidth();
//            double paneHeight = imagePane.getHeight();
//
//            double paneRatio = paneWidth / paneHeight;                                          //calculating the new pane's ratio
//            //after width or height changed
//            double newImageWidth = oldImageWidth, newImageHeight = oldImageHeight;
//
//            if (paneRatio > imageRatio) {
//                newImageHeight = oldImageWidth / paneRatio;
//            } else if (paneRatio < imageRatio) {
//                newImageWidth = oldImageHeight * paneRatio;
//            }
//
//            view.setViewport(new Rectangle2D(                                     // The rectangle used to crop
//                    (oldImageWidth - newImageWidth) / 2, (oldImageHeight - newImageHeight) / 2, //MinX and MinY to crop from the center
//                    newImageWidth, newImageHeight)                                              // new width and height
//            );
//
//            view.setFitWidth(paneWidth);
//        };
//
//        imagePane.widthProperty().addListener(listener);
//        imagePane.heightProperty().addListener(listener);
//
//    }
//}
