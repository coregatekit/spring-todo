package dev.coregate.todo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.coregate.todo.models.Todo;
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
    return todoRepository.findAll().stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  @Override
  public Optional<TodoDTO> getTodoById(Long id) {
    return todoRepository.findById(id).map(this::convertToDTO);
  }

  @Override
  public TodoDTO saveTodo(TodoDTO todoDTO) {
    Todo todo = convertToEntity(todoDTO);
    Todo savedTodo = todoRepository.save(todo);
    return convertToDTO(savedTodo);
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

  private TodoDTO convertToDTO(Todo todo) {
    return new TodoDTO(
      todo.getId(),
      todo.getName(),
      todo.getDescription(),
      todo.isCompleted()
    );
  }

  private Todo convertToEntity(TodoDTO todoDTO) {
    Todo todo = new Todo();
    todo.setName(todoDTO.name());
    todo.setDescription(todoDTO.description());
    todo.setCompleted(todoDTO.completed());
    return todo;
  }
}