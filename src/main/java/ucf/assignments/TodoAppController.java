/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Isaac Liljeros
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class TodoAppController {
    final List<TodoItem> todoItems = new LinkedList<>();
    private Set<TodoItem> todoItemsInView;
    int currentFilter = 0;

    @FXML private VBox taskBox;
    @FXML private Menu toggleFilterOptions;
    @FXML private TextField addItemDescription;
    @FXML private DatePicker addItemDate;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // set view setting to "Show All"
    }

//    @FXML
//    private void onCloseClick(ActionEvent action) {
//        // get the stage from the button
//        Button closeButton = (Button) action.getSource();
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//
//        // Close the window from the stage
//        stage.close();
//    }

    @FXML
    public void toggleFilter(ActionEvent action) {
        // check which filter called the method
        // change global filter value
        // unselect current filter
        // select global filter value
        // update items in view
        // redraw todolist
    }

    @FXML
    public void addItem() {
        try {
            // get item info
            if (addItemDescription.getText() != null && addItemDate.getValue() != null) {
                String description = addItemDescription.getText();
                Date date = convertToDate(addItemDate.getValue());
                // create a new item (this will handle the validation)
                TodoItem todoItem = new TodoItem(description, date);
                // add item to items in view list
                todoItems.add(todoItem);
                // redraw todolist
                redrawApplication();
            } else {
                System.out.println("Something went wrong!");
            }
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "The description should have 1 - 256 characters and the date should be filled in");
        }
    }

    @FXML
    private void removeItem(Event event) {
        // get the index of the task that is being removed
        Button deleteButton = (Button) event.getSource();
        int index = (Integer) deleteButton.getUserData();

        // remove the item from the items in view
        todoItems.remove(index);

        // redraw todolist
        redrawApplication();
    }

    @FXML
    public void removeAllItems() {
        // clear the items in view
        todoItems.clear();

        // redraw the scene
        redrawApplication();
    }

    private void changeItemDescription(Event event) {
        // determine the index of the item that called this
        // get the item
        // switch its label to an input
        // disable the items completion button
        // onEnter try
            // change the entries description
            // redraw
        // catch
            // prompt the user the description must be 1-256 chars
    }

    private void changeItemDueDate() {
        // determine the index of the item that called this
        // get the item
        // switch its label to an input
        // disable the items completion button
        // onEnter try
            // change the entries description
            // redraw
        // catch
            // prompt the user the description must be 1-256 chars
    }

    private void toggleItemCompletion() {
        // determine the index of the item that called this
        // toggle that items completion
        // redraw
    }

    private void openHelpDialogue() { // NOTE: this may get replaced with a simple readme.md
        // create a prompt with the help info in it
        // remove when user closes dialog

        // TODO: figure out how to load in another "view?" with help info
    }

    private void importItems() {
        // open a file selector
        // try
            // use path from file to find file
            // open file
            // try
                // put contents into the todoItems
            // catch
                // prompt: file not in proper format
        // catch
            // prompt: the desired file does not exist
    }

    private void exportItems() {
        // get the selection from the user (whether current list or all)
        // create an output file to write to (with unique name)
            // if name exists, add digit and increment by 1 till unique
        // if current list
            // take the list in view
            // write the number of tasks and the name of the list on line 1
            // write each task on its own line:
            // [ID] [isComplete] [description] [YYY-MM-DD]
        // else
            // do the same thing but loop through every list in memory
    }

    private void redrawApplication() {
        // erase screen
        taskBox.getChildren().clear();

        // temporary: draws everything in the todolist
        int index = 0;
        for (TodoItem todoItem : todoItems)
            drawTodoItem(todoItem, index++);
    }

    private void drawTodoItem(TodoItem todoItem, int index) {
        // Create a container for the todoItem and set its properties
        HBox todoItemContainer = new HBox();
        todoItemContainer.getStyleClass().add("exampleTask");
        todoItemContainer.setPrefHeight(40.0);
        todoItemContainer.setPrefWidth(555.0);

        // Create a checkbox for the task
        CheckBox checkBox = new CheckBox();
        checkBox.getStyleClass().add("completeTask");
        checkBox.setTranslateX(5.0);
        checkBox.setTranslateY(5.0);
        checkBox.setPrefHeight(20.0);
        checkBox.setPrefWidth(20.0);

        // Create a label for the description
        Label descriptionLabel = new Label();
        descriptionLabel.setText(todoItem.getDescription());
        descriptionLabel.getStyleClass().add("taskDescription");
        descriptionLabel.setPrefHeight(30.0);
        descriptionLabel.setPrefWidth(350.0);
        descriptionLabel.setTranslateX(10.0);

        // Create a label for the date
        Label dateLabel = new Label();
        dateLabel.setText(todoItem.getDateAsString());
        dateLabel.getStyleClass().add("taskDate");
        dateLabel.setPrefHeight(30.0);
        dateLabel.setPrefWidth(100);
        dateLabel.setTranslateX(20);

        // Create a button for deleting the task
        Button deleteButton = new Button();
        deleteButton.setText("X");
        deleteButton.getStyleClass().add("deleteTask");
        deleteButton.setPrefHeight(30.0);
        deleteButton.setPrefWidth(30);
        deleteButton.setTranslateX(44.0);
        deleteButton.setUserData(index);
        deleteButton.setOnMouseClicked(this::removeItem);

        // Create a spacer task
        HBox spacer = new HBox();
        spacer.setPrefWidth(555.0);
        spacer.setPrefHeight(5.0);

        // Add the elements to the container
        todoItemContainer.getChildren().addAll(checkBox, descriptionLabel, dateLabel, deleteButton);

        // Add the container and spacer to the canvas
        taskBox.getChildren().addAll(todoItemContainer, spacer);
    }

    private Date convertToDate(LocalDate localDate) {
        // convert local date to date
        return Date.from(Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())));
    }

    private LocalDate convertToLocalDate(Date date) {
        // convert date to local date
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
