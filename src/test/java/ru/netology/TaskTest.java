package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    public void testSimpleTaskMatchesSuccess(){
        SimpleTask task = new SimpleTask(123, "Позвонить домой");
        boolean actual = task.matches("домой");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testSimpleTaskMatchesFailed(){
        SimpleTask task = new SimpleTask(123, "Позвонить домой");
        boolean actual = task.matches("на работу");
        Assertions.assertFalse(actual);
    }

    @Test
    public void testSimpleTaskGetters() {
        SimpleTask task = new SimpleTask(1, "Задача");
        assertEquals(1, task.getId());
        assertEquals("Задача", task.getTitle());
    }

    @Test
    public void testEpicMatches() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);
        assertTrue(epic.matches("Яйца"));
        assertFalse(epic.matches("Кофе"));
    }

    @Test
    public void testEpicGetters() {
        String[] subtasks = {"Молоко", "Яйца"};
        Epic epic = new Epic(2, subtasks);
        assertEquals(2, epic.getId());
        assertArrayEquals(subtasks, epic.getSubtasks());
    }

    @Test
    public void testMeetingMatches() {
        Meeting meeting = new Meeting(3, "Планерка", "Проект А", "Завтра");
        assertTrue(meeting.matches("Планерка"));
        assertTrue(meeting.matches("Проект"));
        assertFalse(meeting.matches("Совещание"));
    }

    @Test
    public void testMeetingGetters() {
        Meeting meeting = new Meeting(3, "Тема", "Проект", "Старт");
        assertEquals(3, meeting.getId());
        assertEquals("Тема", meeting.getTopic());
        assertEquals("Проект", meeting.getProject());
        assertEquals("Старт", meeting.getStart());
    }

    @Test
    public void testEquals() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Task task3 = new Task(2);
        assertEquals(task1, task2);
        assertNotEquals(task1, task3);
        assertEquals(task1.hashCode(), task2.hashCode());
    }

}