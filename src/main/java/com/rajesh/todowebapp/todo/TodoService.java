package com.rajesh.todowebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    static int todocount=0;
    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(++todocount, "in28minutes","Learn AWS",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo(++todocount, "in28minutes","Learn DevOps",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo(++todocount, "in28minutes","Learn Full Stack Development",
                LocalDate.now().plusYears(3), false ));
    }
    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todocount,username,description,targetDate,done);
        todos.add(todo);
    }
    public void deleteById(int id){
        todos.removeIf(todo -> todo.getId()==id);
    }
    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }
    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate =
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
