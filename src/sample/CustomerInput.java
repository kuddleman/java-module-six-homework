package sample;

import java.io.*;
import java.util.*;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class CustomerInput extends Application {

    private Stage primaryStage;
    private Text statusText, resultText;
    private Button uploadButton;

    private final static Font RESULT_FONT = Font.font("Helvetica", 24);
    private final static Font INPUT_FONT = Font.font("Helvetica", 20);

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        VBox primaryBox = new VBox();
        primaryBox.setAlignment(Pos.CENTER);
        primaryBox.setSpacing(20);
        primaryBox.setStyle("-fx-background-color: white");

        VBox uploadBox = new VBox();
        uploadBox.setAlignment(Pos.CENTER);
        uploadBox.setSpacing(20);
        Text uploadLabel = new Text("Upload a comma-separated file with customer data.");
        uploadLabel.setFont(INPUT_FONT);
        uploadButton = new Button("Upload data");
        uploadButton.setOnAction(this::processDataUpload);

        uploadBox.getChildren().add(uploadLabel);
        uploadBox.getChildren().add(uploadButton);
        primaryBox.getChildren().add(uploadBox);

        VBox resultsBox = new VBox();
        resultsBox.setAlignment(Pos.CENTER);
        resultsBox.setSpacing(20);
        statusText = new Text("");
        statusText.setVisible(false);
        statusText.setFont(RESULT_FONT);
        statusText.setFill(Color.RED);
        resultText = new Text("");
        resultText.setVisible(false);
        resultText.setFont(RESULT_FONT);
        resultsBox.getChildren().add(statusText);
        resultsBox.getChildren().add(resultText);
        primaryBox.getChildren().add(resultsBox);

        Scene scene = new Scene(primaryBox, 475, 200, Color.TRANSPARENT);
        primaryStage.setTitle("Customer Data Upload");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void processDataUpload(ActionEvent event) {
        statusText.setVisible(false);
        resultText.setVisible(false);
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        parseFile(file);
        statusText.setText("Success");
        String countAllOrdersString = String.valueOf(Customer.getCountAllOrders());
        resultText.setText(countAllOrdersString);
        statusText.setVisible(true);
        resultText.setVisible(true);
        uploadButton.setDisable(true);

    }

    private void parseFile(File file) {
        // ??? YOUR CODE HERE
        try {
            ArrayList customerList = new ArrayList<Customer>();

            Scanner fileScan = new Scanner(new FileInputStream(new File("GoodCustomerInputData.csv")));

            while (fileScan.hasNext()) {

                String oneLine = fileScan.nextLine();

                Scanner lineScan = new Scanner(oneLine);
                lineScan.useDelimiter(",");


                    String id = lineScan.next();
                    String hopeItsAnInteger = lineScan.next();
                try {
                    int numberOfOrders = Integer.parseInt(hopeItsAnInteger);

                    if (id.contains("@")) {
                        throw new ContainsBadCharacterException();
                    }

                    Customer customer = new Customer(id, numberOfOrders);
                    customerList.add(customer);

                } catch(InputMismatchException ex) {
                    statusText.setVisible(true);
                    String errorText = hopeItsAnInteger + " is not an integer.";
                    statusText.setText(errorText);
                } catch(ContainsBadCharacterException ex) {
                    statusText.setVisible(true);
                    String errorText = id + " contains a '@' character which is not allowed.";
                    statusText.setText(errorText);
                }

            }
            fileScan.close();
        } catch(FileNotFoundException ex) {
            System.out.println("Sorry, the file you requested was not found.");

        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}