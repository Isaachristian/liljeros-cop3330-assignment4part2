/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Isaac Liljeros
 */

package ucf.assignments;

import java.util.Date;

public class TodoItem {
    private String description;
    private Date dueDate;
    private Boolean isComplete;
    private int ID;

    public TodoItem(int ID) {
        // set the unique ID of the task in the constructor
    }

    public String getDescription() {
        // return the description
        return "";
    }

    public void setDescription(String description) {
        // make sure the string is not empty
        // set the description
    }

    public Date getDate() {
        // return the task date
        return null;
    }

    public void setDate(Date date) {
        // make sure the date is valid (not past)
        // set the date
    }

    public Boolean getIsComplete() {
        // return isCompleted
        return true;
    }

    public void setIsComplete(Boolean isComplete) {
        // set isComplete
    }

    public int getID() {
        // return the ID
        return 1;
    }

    public void setID(int ID) {
        // list must do validation so assume this is the right value and set it
    }

}
