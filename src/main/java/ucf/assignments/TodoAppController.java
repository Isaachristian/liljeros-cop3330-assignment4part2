/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Isaac Liljeros
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.converter.LocalDateStringConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class TodoAppController implements Initializable {
    List<TodoItem> todoItems = new LinkedList<>();
    private Set<TodoItem> todoItemsInView;
    Integer currentFilter = 0;
    Boolean isEditingTodoItem = false;

    @FXML private Pane mainPane;
    @FXML private VBox taskBox;
    @FXML private Menu toggleFilterOptions;
    @FXML private TextField addItemDescription;
    @FXML private DatePicker addItemDate;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // set view setting to "Show All"
        CheckMenuItem defaultItem = (CheckMenuItem) toggleFilterOptions.getItems().get(0);
        defaultItem.setSelected(true);

        // set date to today by default
        addItemDate.getEditor().setDisable(true);
        addItemDate.getEditor().setOpacity(1);
        addItemDate.setValue(convertToLocalDate(new Date()));

        // redraw application
        redrawApplication();
    }

    @FXML
    public void toggleFilter(ActionEvent action) {
        // check which filter called the method
        var userData = ((CheckMenuItem) action.getSource()).getUserData();

        // change global filter value
        currentFilter = Integer.parseInt((String) userData);

        // unselect current filter
        toggleFilterOptions.getItems().stream()
                .filter(item -> !item.getUserData().equals(currentFilter.toString()))
                .forEach(item -> ((CheckMenuItem) item).setSelected(false));

        // redraw todolist
        redrawApplication();
    }

    @FXML
    private void tryAddItem(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            addItem();
        }
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
                // clear input
                addItemDescription.clear();
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

    private void changeItemDescriptionWithEnter(KeyEvent event) {
        // if the key is enter
        if (event.getCode().equals(KeyCode.ENTER)) {
            // determine the index of the item that called this
            TextField textField = (TextField) event.getSource();
            int index = (Integer) textField.getUserData();

            // call the changeItem
            changeItemDescription(index, textField.getText());
        }
    }

    private void changeItemDescriptionWithConfirm(MouseEvent event) {
        // get the index of the confirm button
        Button button = (Button) event.getSource();
        int index = (Integer) button.getUserData();

        // handle spacers
        int itemIndex = index == 0 ? 0 : 2 * index;

        // get the description in the item being edited
        TextField input = (TextField) ((HBox) taskBox.getChildren().get(itemIndex)).getChildren().get(1);
        String description = input.getText();

        // call change description
        changeItemDescription(index, description);
    }

    private void changeItemDescription(int index, String description) {
        // get the item data
        TodoItem todoItem = todoItems.get(index);

        // try
        try {
            // change the entries description
            todoItem.setDescription(description);
            // switch its input to a label
            todoItem.toggleEditingDescription();
            // toggle is editing item
            isEditingTodoItem = false;
            // redraw
            redrawApplication();
        } catch (IllegalArgumentException e) {
            // prompt the user the description must be 1-256 chars
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    private void changeItemDueDateWithEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            // determine the index of the item that called this
            DatePicker datePicker = (DatePicker) event.getSource();
            int index = (Integer) datePicker.getUserData();

            // get date and call method to change date
            changeItemDueDate(index, convertToDate(datePicker.getValue()));
        }
    }

    private void changeItemDueDateWithConfirm(MouseEvent event) {
        // get the index of the confirm button
        Button button = (Button) event.getSource();
        int index = (Integer) button.getUserData();

        // handle spacers
        int itemIndex = index == 0 ? 0 : 2 * index;

        // get the description in the item being edited
        DatePicker input = (DatePicker) ((HBox) taskBox.getChildren().get(itemIndex)).getChildren().get(2);
        Date date = convertToDate(input.getValue());

        changeItemDueDate(index, date);
    }

    private void changeItemDueDate(int index, Date date) {
        // get the item data
        TodoItem todoItem = todoItems.get(index);

        // try
        try {
            // change the entries date
            todoItem.setDate(date);
            // switch its input to a label
            todoItem.toggleEditingDate();
            // toggle is editing item
            isEditingTodoItem = false;
            // redraw
            redrawApplication();
        } catch (IllegalArgumentException e) {
            // prompt the user the description must be 1-256 chars
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    private void toggleItemCompletion(Event event) {
        // determine the index of the item that called this
        CheckBox checkBox = (CheckBox) event.getSource();
        int index = (Integer) checkBox.getUserData();

        // toggle that items completion
        todoItems.get(index).toggleIsComplete();

        // redraw
        redrawApplication();
    }

    private void openHelpDialogue() { // NOTE: this may get replaced with a simple readme.md
        // create a prompt with the help info in it
        // remove when user closes dialog

        // TODO: figure out how to load in another "view?" with help info
    }

    @FXML
    private void importItems(Event event) {
        // open a file selector
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

        if (file != null) {
            System.out.println(file.getAbsolutePath());
            todoItems.clear();
            todoItems = readFromFile(file);
            redrawApplication();
        }
    }

    @FXML
    private void exportItems() {
        // create an output file to write to (with unique name)
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("TestFiles", "*.txt"));
        fileChooser.setInitialFileName("todolist.txt");
        File file = fileChooser.showSaveDialog(mainPane.getScene().getWindow());

        if (file != null) {
            writeToFile(file);
        }

    }

    private void writeToFile(File file) {
        try {
            if (file.createNewFile()) {
                // write the number of tasks and the name of the list on line 1
                FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
                fileWriter.write(generateFileContents(todoItems));
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String generateFileContents(List<TodoItem> todoItems) {
        // Create string to hold return value
        StringBuilder sb = new StringBuilder();

        // write each task on its own line:
        // [ID] [isComplete] [description] [YYY-MM-DD]
        for (TodoItem todoItem : todoItems) {
            sb.append(String.format("%d!%s!%s\n",
                    todoItem.getIsComplete() ? 1 : 0,
                    todoItem.getDescription(),
                    todoItem.getDate().toString()));
        }
        return sb.toString();
    }

    public List<TodoItem> readFromFile(File file) {
        List<TodoItem> todoItems = new LinkedList<>();
        try {
            // open the file in a scanner
            Scanner scanner = new Scanner(file);

            // while there are still lines
            while (scanner.hasNextLine()) {
                // scan the next line
                String task = scanner.nextLine();
                // split the results
                String[] todoItemPieces = task.split("!");
                // parse the date back into a todoItem
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy");
                TodoItem todoItem = new TodoItem(todoItemPieces[1], sdf.parse(todoItemPieces[2]));
                if (Integer.parseInt(todoItemPieces[0]) == 1) {
                    todoItem.toggleIsComplete();
                }
                todoItems.add(todoItem);
            }
            scanner.close();
        } catch (IOException | ParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
        return todoItems;
    }

    private void redrawApplication() {
        // erase screen
        taskBox.getChildren().clear();

        // temporary: draws everything in the todolist
        int index = 0;
        for (TodoItem todoItem : getVisibleTodoItems(todoItems, currentFilter)) {
            drawTodoItem(todoItem, index++);
        }
    }

    List<TodoItem> getVisibleTodoItems(List<TodoItem> todoItems, int filter) throws IllegalArgumentException {
        // check value of filter
        // if 0
        if (filter == 0) {
            // return everything
            return todoItems;
        }

        // if 1
        if (filter == 1) {
            // return complete
            return todoItems.stream().filter(TodoItem::getIsComplete).toList();
        }

        // if 2
        if (filter == 2) {
            // return incomplete
            return todoItems.stream().filter(TodoItem::getIsNotComplete).toList();
        }

        // throw illegal argument exception error?
        throw new IllegalArgumentException("Filter must be 0, 1, or 2");
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
        checkBox.setSelected(todoItem.getIsComplete());
        checkBox.setTranslateX(5.0);
        checkBox.setTranslateY(5.0);
        checkBox.setPrefHeight(20.0);
        checkBox.setPrefWidth(20.0);
        checkBox.setUserData(index);
        if (todoItem.getEditingDescription() || todoItem.getEditingDate()) {
            checkBox.setDisable(true);
        }
        checkBox.setOnMouseClicked(this::toggleItemCompletion);
        todoItemContainer.getChildren().add(checkBox);

        if (!todoItem.getEditingDescription()) {
            // Create a label for the description
            Label descriptionLabel = new Label();
            descriptionLabel.setText(todoItem.getDescription());
            descriptionLabel.getStyleClass().add("taskDescription");
            descriptionLabel.setPrefHeight(30.0);
            descriptionLabel.setPrefWidth(350.0);
            descriptionLabel.setTranslateX(10.0);
            descriptionLabel.setOnMouseClicked(event -> {
                if (!isEditingTodoItem) {
                    isEditingTodoItem = true;
                    todoItem.toggleEditingDescription();
                    redrawApplication();
                }
            });
            todoItemContainer.getChildren().add(descriptionLabel);
        } else {
            // Create a text field to edit the description
            TextField descriptionTextField = new TextField();
            descriptionTextField.setText(todoItem.getDescription());
            descriptionTextField.getStyleClass().add("editTaskDescription");
            descriptionTextField.setPrefHeight(30.0);
            descriptionTextField.setPrefWidth(350.0);
            descriptionTextField.setTranslateX(10.0);
            descriptionTextField.setUserData(index);
            descriptionTextField.setOnKeyPressed(this::changeItemDescriptionWithEnter);
            todoItemContainer.getChildren().add(descriptionTextField);
            Platform.runLater(() -> {
                TextField textField = (TextField) todoItemContainer.getChildren().get(1);
                textField.requestFocus();
                textField.positionCaret(textField.getText().length());
            });
        }

        if (!todoItem.getEditingDate()) {
            // Create a label for the date
            Label dateLabel = new Label();
            dateLabel.setText(todoItem.getDateAsString());
            dateLabel.getStyleClass().add("taskDate");
            dateLabel.setAlignment(Pos.CENTER);
            dateLabel.setPrefHeight(30.0);
            dateLabel.setPrefWidth(120.0);
            dateLabel.setTranslateX(20.0);
            dateLabel.setOnMouseClicked(event -> {
                if (!isEditingTodoItem) {
                    todoItem.toggleEditingDate();
                    isEditingTodoItem = true;
                    redrawApplication();
                }
            });
            todoItemContainer.getChildren().add(dateLabel);
        } else {
            // Create a date picker for editing the date
            DatePicker datePicker = new DatePicker();
            datePicker.setValue(convertToLocalDate(todoItem.getDate()));
            datePicker.getStyleClass().add("editTaskDate");
            datePicker.setPrefHeight(30.0);
            datePicker.setPrefWidth(120.0);
            datePicker.setTranslateX(20.0);
            datePicker.setUserData(index);
            datePicker.getEditor().setDisable(true);
            datePicker.getEditor().setOpacity(1);
            datePicker.setOnKeyPressed(this::changeItemDueDateWithEnter);
            todoItemContainer.getChildren().add(datePicker);
        }

        // Create a button for deleting the task

        if (todoItem.getEditingDescription() || todoItem.getEditingDate()) {
            Button confirmButton = new Button();
            confirmButton.setText("\u2713");
            confirmButton.getStyleClass().add("confirmTask");
            confirmButton.setPrefHeight(30.0);
            confirmButton.setPrefWidth(30);
            confirmButton.setTranslateX(24.0);
            confirmButton.setUserData(index);
            confirmButton.setOnMouseClicked(event -> {
                // get the index of the confirm button
                int clickedIndex = (Integer) ((Button) event.getSource()).getUserData();

                // check if the associated user item is being edited
                if (todoItems.get(clickedIndex).getEditingDescription()) {
                    // if editing description, save
                    changeItemDescriptionWithConfirm(event);
                } else if (todoItems.get(clickedIndex).getEditingDate()) {
                    // if editing date, save
                    changeItemDueDateWithConfirm(event);
                }
            });
            todoItemContainer.getChildren().add(confirmButton);
        } else {
            Button deleteButton = new Button();
            deleteButton.setText("X");
            deleteButton.getStyleClass().add("deleteTask");
            deleteButton.setPrefHeight(30.0);
            deleteButton.setPrefWidth(30);
            deleteButton.setTranslateX(24.0);
            deleteButton.setUserData(index);
            deleteButton.setOnMouseClicked(this::removeItem);
            todoItemContainer.getChildren().add(deleteButton);
        }

        // Create a spacer task
        HBox spacer = new HBox();
        spacer.setPrefWidth(555.0);
        spacer.setPrefHeight(5.0);

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
