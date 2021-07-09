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

    public TodoItem(String description, Date dueDate) {
        // validate the item's description
        // validate the items due date
        // set the item's description
        // set the item's due date
    }

    public String getDescription() {
        // return the description
        return "";
    }

    public void setDescription(String description) throws IllegalArgumentException {
        // ensure the string is between 1 and 256 characters
        // set the description
    }

    public Date getDate() {
        // return the task date
        return null;
    }

    public void setDate(Date date) throws IllegalArgumentException {
        // make sure the date is a valid Gregorian Calendar date
        // set the date
    }

    public Boolean getIsComplete() {
        // return isCompleted
        return true;
    }

    public void toggleIsComplete() {
        // toggle isComplete value
    }
}
