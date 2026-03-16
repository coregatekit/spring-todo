package dev.coregate.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.coregate.todo.models.TodoDTO;
import dev.coregate.todo.repositories.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
  private final TodoRepository todoRepository;

  public TodoServiceImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  public List<TodoDTO> getAllTodos() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllTodos'");
  }

  @Override
  public Optional<TodoDTO> getTodoById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getTodoById'");
  }

  @Override
  public TodoDTO saveTodo(TodoDTO todoDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveTodo'");
  }

  @Override
  public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateTodo'");
  }

  @Override
  public void toggleTodoCompleted(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'toggleTodoCompleted'");
  }

  @Override
  public void deleteTodoById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteTodoById'");
  }

}