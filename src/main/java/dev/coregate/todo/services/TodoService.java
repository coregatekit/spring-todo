package dev.coregate.todo.services;

import java.util.List;
import java.util.Optional;

import dev.coregate.todo.models.TodoDTO;

public interface TodoService {
  List<TodoDTO> getAllTodos();
  Optional<TodoDTO> getTodoById(Long id);
  TodoDTO saveTodo(TodoDTO todoDTO);
  TodoDTO updateTodo(Long id, TodoDTO todoDTO);
  void toggleTodoCompleted(Long id);
  void deleteTodoById(Long id);
}