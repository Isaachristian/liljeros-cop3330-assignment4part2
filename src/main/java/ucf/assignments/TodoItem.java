/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Isaac Liljeros
 */

package ucf.assignments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String description;
    private Date dueDate;
    private Boolean isComplete;
    private Boolean isEditingDescription = false;
    private Boolean isEditingDate = false;

    public TodoItem(String description, Date dueDate) throws IllegalArgumentException {
        // validate the item's description and due date
        if (description.length() > 0 && description.length() <= 256 && dueDate != null) {
            // set the item's description
            this.description = description;
            // set the item's due date
            this.dueDate = dueDate;
        } else {
            throw new IllegalArgumentException("Date or description is invalid");
        }
    }

    public String getDescription() {
        // return the description
        return this.description;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        // ensure the string is between 1 and 256 characters
        if (description != null && isEditingDescription && description.length() > 0 && description.length() <= 256) {
            // set the description
            this.description = description;
        } else {
            throw new IllegalArgumentException("The description must be between 1 and 256 characters.");
        }
    }

    public Date getDate() {
        // return the task date
        return this.dueDate;
    }

    public String getDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.dueDate);
    }

    public void setDate(Date date) throws IllegalArgumentException {
        // make sure the date is a valid Gregorian Calendar date
        if (date != null && isEditingDate) {
            // set the date
            dueDate = date;
        } else {
            throw new IllegalArgumentException("The date was null.");
        }
    }

    public Boolean getIsComplete() {
        // return isCompleted
        return true;
    }

    public void toggleIsComplete() {
        // toggle isComplete value
    }

    public Boolean getEditingDescription() {
        return isEditingDescription;
    }

    public void toggleEditingDescription() {
        isEditingDescription = !isEditingDescription;
    }

    public Boolean getEditingDate() {
        return isEditingDate;
    }

    public void toggleEditingDate() {
        isEditingDate = !isEditingDate;
    }
}
