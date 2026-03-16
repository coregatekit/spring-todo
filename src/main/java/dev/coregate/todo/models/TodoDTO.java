package dev.coregate.todo.models;

public record TodoDTO(
  Long id,
  String name,
  String description,
  boolean completed
) {
}