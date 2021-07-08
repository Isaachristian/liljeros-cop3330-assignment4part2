/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Isaac Liljeros
 */

package ucf.assignments;

public class TodoAppController {
    private TodoList listInView;

    private void changeListInView() {
        // change listInView to the current list that has been requested by the user
    }

    private void addList() {
        // Call proper list
        // switch to this list
    }

    private void removeList() {
        // remove the list that called this
        // switch to the top list
    }

    private void renameList() {
        // call the rename method on the listInView
    }

    private void addItem() {
        // call the add item method on listInView
    }

    private void removeItem() {
        // determine the ID of the item to remove
        // call the remove item method on the listInView using the ID
    }

    private void renameItem() {
        // determine the ID of the item to rename
        // call the rename item method on the listInView using the ID
    }

    private void changeItemDueDate() {
        // determine the ID of the item to change the due date on
        // call the change due date methode on the listInView using the ID
    }

    private void completeItem() {
        // determine the ID of the item to complete
        // call the complete item method on the ListInView with the ID
    }

    private void filterList() {
        // get the selection of the filter dropdown
        // call the filter method with the inputs selection
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

    private void importItems() {
        //
    }

}
