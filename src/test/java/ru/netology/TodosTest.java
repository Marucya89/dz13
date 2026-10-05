package ru.netology;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testOneTaskFound() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { meeting };
        Task[] actual = todos.search("приложения");
        Assertions.assertArrayEquals(expected,actual);
    }

    /*@Test
    public void testFewTaskFound() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить хлеб");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic };
        Task[] actual = todos.search("хлеб");
        Assertions.assertArrayEquals(expected,actual);
    }*/

    @Test
    public void searchShouldFindMatchingTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(555, "Выкатка 3й версии", "Приложение НетоБанка", "Вторник после обеда");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("родителям");
        assertArrayEquals(new Task[]{simpleTask}, result);

        result = todos.search("Яйца");
        assertArrayEquals(new Task[]{epic}, result);

        result = todos.search("НетоБанка");
        assertArrayEquals(new Task[]{meeting}, result);
    }


    @Test
    public void testNoTaskFound() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить хлеб");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { };
        Task[] actual = todos.search("Работа");
        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void searchShouldReturnEmptyWhenNoMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Задача"));
        assertEquals(0, todos.search("несуществующий").length);
    }

    @Test
    public void searchMultipleMatches() {
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(2, "Купить хлеб");
        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);

        Task[] result = todos.search("Купить");
        assertEquals(2, result.length);
        assertArrayEquals(new Task[]{task1, task2}, result);
    }


}