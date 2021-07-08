/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Isaac Liljeros
 */

package ucf.assignments;

import java.util.Date;

public class TodoList {

    private String listName;
    private TodoItem[] todoItems;

    public String getListName() {
        // get the list name and return it
        return "";
    }

    public void setListName(String listName) {
        // make sure the listName is not empty
        // set list name
    }

    public TodoItem[] getAll() {
        // return the todoItems list
        return null;
    }

    public TodoItem[] getComplete() {
        // turn the todoItems into a stream
        // filter out the incomplete
        // return result
        return null;
    }

    public TodoItem[] getIncomplete() {
        // turn the todoItems into a stream
        // filter out the complete
        // return result
        return null;
    }

    public void createItem(String itemName, Date itemDate) {
        // create unique ID
            // increment by one
            // loop through items
            // try again if not unique, otherwise assign
        // create a new item with the name, date, and ID, as incomplete
    }

    public void completeItem(int ID) {
        // find item by id
        // call setIsCompelete on item
    }

    public void renameItem(int ID, String newItemName) {
        // find item by id
        // call rename method on item
    }

    public void rescheduleItem(int ID, Date newItemDate) {
        // find item by ID
        // call the change date method on the item
    }

    public void deleteItem(int ID) {
        // find element by ID
        // remove the item from the list
    }
}
