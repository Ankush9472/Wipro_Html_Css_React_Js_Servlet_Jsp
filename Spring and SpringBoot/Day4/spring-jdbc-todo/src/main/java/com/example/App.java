package com.example;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Todo;
import com.example.config.JdbcConfig;
import com.example.dao.TodoDao;
import com.example.dao.TodoDaoImpl;

// Controller
public class App {

    public static void main(String[] args) {

        // Load Spring container using Java-based config (no XML!)
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);

        // Get the DAO bean
        TodoDao dao = context.getBean(TodoDaoImpl.class);

        // Create 4 Todo objects
        Todo todo  = new Todo(1, "task1", "task1 learn java");
        Todo todo1 = new Todo(2, "task2", "implement java code");
        Todo todo2 = new Todo(3, "task3", "push the code in github");
        Todo todo3 = new Todo(4, "task4", "create a image using docker and push in docker hub");

        // Save all 4 todos to the database
        dao.saveTodo(todo);
        dao.saveTodo(todo1);
        dao.saveTodo(todo2);
        dao.saveTodo(todo3);

        // Fetch all todos and print them
        List<Todo> todos = dao.getAllTodos();
        for (Todo t : todos) {
            System.out.println(t);
        }

        // Commented-out lines for other operations (uncomment to test):
        // Todo todoUpdated = new Todo(2, "task2", "coding");
        // dao.updateTodo(todoUpdated);
        //
        // System.out.println(dao.getTodo(4));
        // dao.deleteTodo(4);
    }
}