import org.junit.jupiter.api.Test;

public class TodoAppTest {

    @Test
    void testAddNewTodoList () {
        // create a new controller
        // add a todolist
        // expect: the list can be found
    }

    @Test
    void testRemoveTodoList () {
        // create a new controller
        // add a todolist
        // remove the todolist
        // expect: no list to be present
    }

    @Test
    void testEditExistingList () {
        // create a new list
        // change the name of the list
        // expect the list name to be the new name
    }

    @Test
    void testAddItemToList () {
        // create a new list
        // add an item to the list
        // expect the item to be present
    }

    @Test
    void testRemoveItemFromList () {
        // Create a new list
        // add an item to the list
        // expect the item to be present
        // remove an item from the list
        // expect the list to be empty
    }

    @Test
    void testRenamingItem () {
        // create a list
        // add an item
        // edit the items description
        // expect: the description to be the new one
    }

    @Test
    void testChangingDueDateOfItem () {
        // Create a todolist
        // add an item to it
        // change the date of the item
        // expect the date to be the new date
    }

    @Test
    void testCompleteItem () {
        // create a todolist
        // add an item
        // mark as complete
        // expect: incomplete items should return empty
    }

    @Test
    void testDisplayAllTasks () {
        // create a todolist
        // add two items to the list
        // complete one
        // expect: should be able to show all items
    }

    @Test
    void testDisplayCompleteTasks () {
        // create a todolist
        // add two items to the list
        // complete the second one
        // expect: should show the second item
    }

    @Test
    void testDisplayIncompleteTasks () {
        // create a todolist
        // add two items to the list
        // complete the second one
        // expect: should show the first item
    }

    @Test
    void testSaveSingleList () {
        // create a todoapp controller
        // add two todolists
        // add a different task to each todolist
        // export the second todolist
        // expect: the file to present
        // expect: the contents to match the second todolists info
    }

    @Test
    void testSaveAllLists () {
        // create a todoapp controller
        // add two todolists
        // add a different task to each todolist
        // export the todolists
        // expect: the file to present
        // expect: the contents to match the todolists info
    }

    @Test
    void testLoadSingleList () {
        // create a todoapp
        // load a file from the test recources folder
        // call the load function
        // expect the todolist and items to match the file data
    }

    @Test
    void testLoadAllLists () {
        // create a todoapp
        // load a file from the test recources folder
        // call the load lists function
        // expect the todolists and items to match the file data
    }
}
