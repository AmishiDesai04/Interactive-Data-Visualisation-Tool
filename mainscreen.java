package com.example.javaproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainscreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OOPJ MINI PROJECT");

        Label headerLabel1 = new Label("Interactive Data Visualization Tool");
        headerLabel1.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: Georgia"); // Align header label to center
        Label headerLabel2 = new Label("C034, C044, C049");
        headerLabel2.setStyle("-fx-font-size: 20px;");

        Button pieButton = new Button("Pie Chart");
        Button barButton = new Button("Bar Chart");
        Button lineButton = new Button("Line Chart");

        pieButton.setStyle("-fx-font-size: 18px; -fx-min-width: 300px; -fx-min-height: 60px; -fx-background-color: #2DD881; -fx-text-fill: white;-fx-font-weight: bold;-fx-font-family: Cambria; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
        barButton.setStyle("-fx-font-size: 18px; -fx-min-width: 300px; -fx-min-height: 60px; -fx-background-color: #4EA699; -fx-text-fill: white;-fx-font-weight: bold;-fx-font-family: Cambria; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
        lineButton.setStyle("-fx-font-size: 18px; -fx-min-width: 300px; -fx-min-height: 60px; -fx-background-color: #140D4F; -fx-text-fill: white;-fx-font-weight: bold;-fx-font-family: Cambria; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");

        pieButton.setOnAction(event -> {
            pie pieChartExample = new pie();
            pieChartExample.start(new Stage());
        });

        barButton.setOnAction(event -> {
            bar barChartExample = new bar();
            barChartExample.start(new Stage());
        });

        lineButton.setOnAction(event -> {
            line lineChartExample = new line();
            lineChartExample.start(new Stage());
        });

        VBox buttonsVBox = new VBox(20, pieButton, barButton, lineButton);
        buttonsVBox.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        VBox root = new VBox();
        root.getChildren().addAll(headerLabel1, headerLabel2, buttonsVBox);
        root.setStyle("-fx-alignment: center;-fx-background-color: white; -fx-font-family: Cambria");

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
