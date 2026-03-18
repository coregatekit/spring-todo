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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @Tag(name = "Todo API", description = "API for managing todo items")
    @Operation(summary = "Get all todo items", description = "Returns a list of all todo items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of todo items")
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    @Tag(name = "Todo API", description = "API for managing todo items")
    @Operation(summary = "Get a todo item by ID", description = "Returns a single todo item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the todo item"),
            @ApiResponse(responseCode = "404", description = "Todo item not found")
    })
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        Optional<TodoDTO> todo = todoService.getTodoById(id);
        return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Tag(name = "Todo API", description = "API for managing todo items")
    @Operation(summary = "Create a new todo item", description = "Creates a new todo item and returns it")
    @ApiResponse(responseCode = "200", description = "Successfully created the todo item")
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO createdTodo = todoService.saveTodo(todoDTO);
        return ResponseEntity.ok(createdTodo);
    }

    @PatchMapping("/{id}")
    @Tag(name = "Todo API", description = "API for managing todo items")
    @Operation(summary = "Toggle the completed status of a todo item", description = "Toggles the completed status of a todo item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully toggled the completed status of the todo item"),
            @ApiResponse(responseCode = "404", description = "Todo item not found")
    })
    public ResponseEntity<TodoDTO> toggleCompleted(@PathVariable Long id) {
        try {
            todoService.toggleTodoCompleted(id);
            return ResponseEntity.ok().build();
        } catch (Exception _) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Tag(name = "Todo API", description = "API for managing todo items")
    @Operation(summary = "Update a todo item", description = "Updates a todo item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the todo item"),
            @ApiResponse(responseCode = "404", description = "Todo item not found")
    })
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
        try {
            TodoDTO updatedTodo = todoService.updateTodo(id, todoDTO);
            return ResponseEntity.ok(updatedTodo);
        } catch (Exception _) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Tag(name = "Todo API", description = "API for managing todo items")
    @Operation(summary = "Delete a todo item", description = "Deletes a todo item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the todo item"),
            @ApiResponse(responseCode = "404", description = "Todo item not found")
    })
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodoById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception _) {
            return ResponseEntity.notFound().build();
        }
    }
}
