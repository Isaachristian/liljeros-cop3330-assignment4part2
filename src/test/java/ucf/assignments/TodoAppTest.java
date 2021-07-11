package ucf.assignments;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        // create a new list
        List<TodoItem> TodoItems = new LinkedList<>();

        // add multiple items to the list
        for (int i = 0; i < 5; i++)
            TodoItems.add(new TodoItem("Test task", new Date()));

        // clear the list of items
        TodoItems.clear();

        // expect list to be empty
        assertEquals(0, TodoItems.size());
    }

    // A user shall be able to edit the description of an item within the list
    @Test
    void testEditItemDescription () {
        // create a list
        List<TodoItem> todoItems = new LinkedList<>();

        // add an item
        todoItems.add(new TodoItem("Test item", new Date()));

        // edit the items description
        todoItems.get(0).toggleEditingDescription();
        assertDoesNotThrow(() -> todoItems.get(0).setDescription("This is the new description"));
        todoItems.get(0).toggleEditingDescription();

        // assert: the description to be the new one
        assertEquals("This is the new description", todoItems.get(0).getDescription());
        assertFalse(todoItems.get(0).getEditingDescription());
    }

    // A user shall be able to edit the due date of an item within the list
    @Test
    void testChangingDueDateOfItem () {
        // Create a todolist
        List<TodoItem> todoItems = new LinkedList<>();

        // add an item to it
        todoItems.add(new TodoItem("Test item", new Date()));

        // change the date of the item
        todoItems.get(0).toggleEditingDate();
        Date date = new Date();
        assertDoesNotThrow(() -> todoItems.get(0).setDate(date));
        todoItems.get(0).toggleEditingDate();

        // expect the date to be the new date
        assertEquals(date, todoItems.get(0).getDate());
        assertFalse(todoItems.get(0).getEditingDate());
    }

    // A user shall be able to mark an item in the list as either complete or incomplete
    @Test
    void testCompleteItem () {
        // create a todolist
        List<TodoItem> todoItems = new LinkedList<>();

        // add an item
        todoItems.add(new TodoItem("Test item", new Date()));

        // mark as complete
        todoItems.get(0).toggleIsComplete();

        // assert: should be complete
        assertEquals(true, todoItems.get(0).getIsComplete());

        // mark as incomplete
        todoItems.get(0).toggleIsComplete();

        // assert is incomplete
        assertEquals(false, todoItems.get(0).getIsComplete());
    }

    // A user shall be able to display all of the existing items in the list
    @Test
    void testDisplayAllTasks () {
        // create a todolist
        List<TodoItem> todoItems = new LinkedList<>();

        // create a todoApp controller
        TodoAppController tac = new TodoAppController();

        // add two items to the list
        todoItems.add(new TodoItem("Item 1", new Date()));
        todoItems.add(new TodoItem("Item 2", new Date()));

        // complete one
        todoItems.get(1).toggleIsComplete();

        // assert: should be able to show all items
        assertEquals(2, tac.getVisibleTodoItems(todoItems, 0).size());
    }

    // A user shall be able to display only the incomplete items in the list
    @Test
    void testDisplayCompleteTasks () {
        // create a todolist
        List<TodoItem> todoItems = new LinkedList<>();

        // create a new controller
        TodoAppController tac = new TodoAppController();

        // add two items to the list
        todoItems.add(new TodoItem("Item 1", new Date()));
        todoItems.add(new TodoItem("Item 2", new Date()));

        // complete the second one
        todoItems.get(1).toggleIsComplete();

        // assert: should show the second item
        assertEquals(1, tac.getVisibleTodoItems(todoItems, 1).size());
        assertEquals("Item 2", tac.getVisibleTodoItems(todoItems, 1).get(0).getDescription());
    }

    // A user shall be able to display only the completed items in the list
    @Test
    void testDisplayIncompleteTasks () {
        // create a todolist
        List<TodoItem> todoItems = new LinkedList<>();

        // create a new controller
        TodoAppController tac = new TodoAppController();

        // add two items to the list
        todoItems.add(new TodoItem("Item 1", new Date()));
        todoItems.add(new TodoItem("Item 2", new Date()));

        // complete the second one
        todoItems.get(1).toggleIsComplete();

        // assert: should show the first item
        assertEquals(1, tac.getVisibleTodoItems(todoItems, 2).size());
        assertEquals("Item 1", tac.getVisibleTodoItems(todoItems, 2).get(0).getDescription());
    }


    // A user shall be able to save the list (and all of its items) to external storage
    @Test
    void testSaveSingleList () {
        // create a todolist
        List<TodoItem> todoItems = new LinkedList<>();

        // create a date option
        Date date = new Date();

        // create a todoapp controller
        TodoAppController todoAppController = new TodoAppController();

        // add task todolist
        todoItems.add(new TodoItem("Task 1", date));

        // export the todolist
        String fileContents = todoAppController.generateFileContents(todoItems);

        // assert: the file contents to be accurate
        assertEquals("0!Task 1!" + date.toString() + "\n", fileContents);
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
