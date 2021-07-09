package ucf.assignments;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TodoAppTest {

    // A user shall be able to add a new item to the list
    @Test
    void testAddItemToList() {
        // create a new list
        List<TodoItem> TodoItems = new LinkedList<>();

        // add an item to the list
        assertDoesNotThrow(() -> TodoItems.add(new TodoItem("Test task", new Date())));

        // attempt to add an item with an illegal description
        String longDescription = "This is a test of how many characters this piece of text has. This is a test of " +
                "how many characters this piece of text has. This is a test of how many characters this piece of text" +
                " has. This is a test of how many characters this piece of text has. This is a test of how many" +
                " characters this piece of text has.";
        assertThrows(IllegalArgumentException.class,
                () -> TodoItems.add(new TodoItem(longDescription, new Date())));

        // attempt to add an item with an illegal date
        assertThrows(IllegalArgumentException.class,
                () -> TodoItems.add(new TodoItem("Test test", null)));

        // expect the item to be present
        assertDoesNotThrow(() -> TodoItems.get(0));
    }

    // A user shall be able to remove an item from the list
    @Test
    void testRemoveItemFromList() {
        // create a new list
        List<TodoItem> TodoItems = new LinkedList<>();

        // add an item to the list
        TodoItems.add(new TodoItem("Test task", new Date()));

        // assert the item to be present
        assertDoesNotThrow(() -> TodoItems.get(0));

        // remove the item from the list
        TodoItems.remove(0);

        // expect list to be empty
        assertThrows(IndexOutOfBoundsException.class, () -> TodoItems.get(0));
    }

    // A user shall be able to clear the list of all items
    @Test
    void testRemovingAllItems() {
        // add multiple items to the list
        // call removeAll
        // assert list should be empty
    }

    // A user shall be able to edit the description of an item within the list
    @Test
    void testEditItemDescription () {
        // create a list
        // add an item
        // edit the items description
        // expect: the description to be the new one
    }

    // A user shall be able to edit the due date of an item within the list
    @Test
    void testChangingDueDateOfItem () {
        // Create a todolist
        // add an item to it
        // change the date of the item
        // expect the date to be the new date
    }

    // A user shall be able to mark an item in the list as either complete or incomplete
    @Test
    void testCompleteItem () {
        // create a todolist
        // add an item
        // mark as complete
        // expect: incomplete items should return empty
        // mark as incomplete
        // assert is incomplete
    }

    // A user shall be able to display all of the existing items in the list
    @Test
    void testDisplayAllTasks () {
        // create a todolist
        // add two items to the list
        // complete one
        // expect: should be able to show all items
    }

    // A user shall be able to display only the incomplete items in the list
    @Test
    void testDisplayIncompleteTasks () {
        // create a todolist
        // add two items to the list
        // complete the second one
        // expect: should show the first item
    }

    // A user shall be able to display only the completed items in the list
    @Test
    void testDisplayCompleteTasks () {
        // create a todolist
        // add two items to the list
        // complete the second one
        // expect: should show the second item
    }


    // A user shall be able to save the list (and all of its items) to external storage
    @Test
    void testSaveSingleList () {
        // create a todoapp controller
        // add two todolists
        // add a different task to each todolist
        // export the second todolist
        // expect: the file to present
        // expect: the contents to match the second todolists info
    }

    // A user shall be able to load a list (and all of its items) from external storage
    @Test
    void testLoadSingleList () {
        // create a todoapp
        // load a file from the test recources folder
        // call the load function
        // expect the todolist and items to match the file data
    }
}
