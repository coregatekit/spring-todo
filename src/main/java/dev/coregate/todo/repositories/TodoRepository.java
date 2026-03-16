package dev.coregate.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.coregate.todo.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
