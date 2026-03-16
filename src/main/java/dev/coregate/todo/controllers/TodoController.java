package dev.coregate.todo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.coregate.todo.models.TodoDTO;
import dev.coregate.todo.services.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping
  public ResponseEntity<List<TodoDTO>> getAllTodos() {
    List<TodoDTO> todos = todoService.getAllTodos();
    return ResponseEntity.ok(todos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
    Optional<TodoDTO> todo = todoService.getTodoById(id);
    return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO) {
    TodoDTO createdTodo = todoService.saveTodo(todoDTO);
    return ResponseEntity.ok(createdTodo);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<TodoDTO> toggleCompleted(@PathVariable Long id) {
    try {
      todoService.toggleTodoCompleted(id);
      return ResponseEntity.ok().build();
    } catch (Exception _) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
    try {
      TodoDTO updatedTodo = todoService.updateTodo(id, todoDTO);
      return ResponseEntity.ok(updatedTodo);
    } catch (Exception _) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
    try {
      todoService.deleteTodoById(id);
      return ResponseEntity.noContent().build();
    } catch (Exception _) {
      return ResponseEntity.notFound().build();
    }
  }
}
