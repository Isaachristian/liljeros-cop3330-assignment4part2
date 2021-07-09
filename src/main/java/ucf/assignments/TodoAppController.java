/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Isaac Liljeros
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TodoAppController {
    private TodoItem[] todoItems;
    private TodoItem[] todoItemsInView;
    int currentFilter = 0;

    @FXML private VBox TaskBox;
    @FXML private Menu toggleFilterOptions;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // set view setting to "Show All"
    }

    @FXML
    private void onCloseClick(ActionEvent action) {
        // get the stage from the button
        Button closeButton = (Button) action.getSource();
        Stage stage = (Stage) closeButton.getScene().getWindow();

        // Close the window from the stage
        stage.close();
    }

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
    public void addItem(ActionEvent action) {
        // try
            // create a new item (this will handle the validation)
            // add item to items in view list
            // redraw todolist
        // catch
            // warn the user that the description or date is invalid
    }

    @FXML
    private void removeItem(ActionEvent action) {
        // get the index of the task that is being removed
        // remove the item from the items in view
        // redraw todolist
    }

    @FXML
    public void removeAllItems() {
        // clear the items in view
        // redraw the scene
    }

    private void changeItemDescription(ActionEvent action) {
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
}
