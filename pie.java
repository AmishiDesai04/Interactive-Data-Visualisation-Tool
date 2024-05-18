package com.example.javaproject;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;//for whatever text fields input that are taken
import javafx.beans.property.SimpleStringProperty;//for whatever text fields input that are taken
import javafx.collections.FXCollections;//needed for observable list option
import javafx.collections.ObservableList;//dynamic data structure that can be observed for changes -> list view and data view; helpful when we add data and want it to get reflected to the table
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;//for customized pir charts, piechart.Data is used to add items to the visualisation of data
import javafx.scene.control.*;//allows you to use various JavaFX UI controls in your application
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class pie extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Enter data for the pie chart:");
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        TextField nameInput = new TextField();
        nameInput.setStyle("-fx-font-size: 14px;");

        TextField dataInput = new TextField();//controls
        dataInput.setStyle("-fx-font-size: 14px;");

        Button addButton = new Button("Add Data");
        addButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2DD881; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
        PieChart pieChart = new PieChart();//creating instance of piechart class

        TableView<ItemData> tableView = new TableView<>();//creating an instance of table view, it is under controls

        TableColumn<ItemData, String> itemNameCol = new TableColumn<>("Item Name");
        itemNameCol.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());//setCellFactory is an in built function that is used to determine cell values
        //getValue() is used to retrieve the values in the cell

        TableColumn<ItemData, Number> itemValueCol = new TableColumn<>("Value");
        itemValueCol.setCellValueFactory(cellData -> cellData.getValue().itemValueProperty());

        tableView.getColumns().addAll(itemNameCol, itemValueCol);

        ObservableList<ItemData> itemDataList = FXCollections.observableArrayList();//stores object of ItemData

        addButton.setOnAction(event -> {
            try {
                String itemName = nameInput.getText();
                double dataValue = Double.parseDouble(dataInput.getText());
                ItemData itemData = new ItemData(itemName, dataValue);
                itemDataList.add(itemData);//adding to table

                tableView.setItems(itemDataList);//to integrate the object list and the table visible to us

                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                double totalValue = 0.0;
                for (ItemData item : itemDataList) {
                    totalValue += item.getItemValue();
                }
                for (ItemData item : itemDataList)//to display percentage
                {
                    double percentage = (item.getItemValue() / totalValue) * 100;
                    pieChartData.add(new PieChart.Data(item.getItemName() + " (" + String.format("%.2f", percentage) + "%)", item.getItemValue()));
                }
                pieChart.setData(pieChartData);

                nameInput.clear();
                dataInput.clear();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        });

        VBox root = new VBox(10, label, new HBox(10, new Label("Item Name:"), nameInput),
                new HBox(10, new Label("Value:"), dataInput), addButton, tableView, pieChart);
        root.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f4;-fx-font-family: Cambria");

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("PIE CHART GENERATOR");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static class ItemData
    {
        private final SimpleStringProperty itemName;
        private final SimpleDoubleProperty itemValue;

        public ItemData(String itemName, double itemValue) //constructor or setter
        {
            this.itemName = new SimpleStringProperty(itemName);
            this.itemValue = new SimpleDoubleProperty(itemValue);
        }

        public String getItemName() {
            return itemName.get();
        }

        public SimpleStringProperty itemNameProperty() {
            return itemName;
        }

        public double getItemValue() {
            return itemValue.get();
        }

        public SimpleDoubleProperty itemValueProperty() {
            return itemValue;
        }
    }
}
