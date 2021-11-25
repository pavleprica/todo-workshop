package rs.fon.hzs.todo.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rs.fon.hzs.todo.app.exceptions.TodoItemNotFound;
import rs.fon.hzs.todo.app.model.ReturnTodoItemDto;
import rs.fon.hzs.todo.app.model.TodoItem;
import rs.fon.hzs.todo.app.repository.TodoItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    public List<ReturnTodoItemDto> getAllTodos() {
        return todoItemRepository.findAll()
                .stream()
                .map(todoItem -> mapToDto(todoItem))
                .collect(Collectors.toList());
    }

    public ReturnTodoItemDto getTodoItemById(Long id) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        if (optionalTodoItem.isPresent()) {
            return mapToDto(optionalTodoItem.get());
        } else {
            throw new TodoItemNotFound("Requested id not present [" + id + "]");
        }

    }

    public ReturnTodoItemDto getTodoItemByTitle(String title) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findByTitle(title);

        if (optionalTodoItem.isPresent()) {
            return mapToDto(optionalTodoItem.get());
        } else {
            throw new TodoItemNotFound("Requested title not present [" + title + "]");
        }
    }

    public ReturnTodoItemDto createTodoItem(TodoItem todoItem) {
        try {
            return mapToDto(todoItemRepository.save(todoItem));
        } catch (DataIntegrityViolationException exception) {
            log.error("Couldn't create todo item, {}", exception.getLocalizedMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't create duplicates");
        }
    }

    public ReturnTodoItemDto updateTodoItemById(Long id, TodoItem todoItem) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        if (optionalTodoItem.isPresent()) {
            TodoItem savedTodoItem = optionalTodoItem.get();
            savedTodoItem.setTitle(todoItem.getTitle());
            savedTodoItem.setDescription(todoItem.getDescription());
            savedTodoItem.setIsDone(todoItem.getIsDone());
            savedTodoItem.setTodoItemType(todoItem.getTodoItemType());
            return mapToDto(todoItemRepository.save(savedTodoItem));
        } else {
            throw new TodoItemNotFound("Requested id not present [" + id + "]");
        }
    }

    public ReturnTodoItemDto markIsDoneById(Long id) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        if (optionalTodoItem.isPresent()) {
            TodoItem savedTodoItem = optionalTodoItem.get();
            savedTodoItem.setIsDone(true);
            return mapToDto(todoItemRepository.save(savedTodoItem));
        } else {
            throw new TodoItemNotFound("Requested id not present [" + id + "]");
        }
    }

    public ReturnTodoItemDto markIsNotDoneById(Long id) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        if (optionalTodoItem.isPresent()) {
            TodoItem savedTodoItem = optionalTodoItem.get();
            savedTodoItem.setIsDone(false);
            return mapToDto(todoItemRepository.save(savedTodoItem));
        } else {
            throw new TodoItemNotFound("Requested id not present [" + id + "]");
        }
    }

    public void deleteTodoItemById(Long id) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        if (optionalTodoItem.isPresent()) {
            todoItemRepository.deleteById(id);
        } else {
            throw new TodoItemNotFound("Requested id not present [" + id + "]");
        }
    }

    public void deleteTodoItemByTitle(String title) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findByTitle(title);

        if (optionalTodoItem.isPresent()) {
            todoItemRepository.deleteByTitle(title);
        } else {
            throw new TodoItemNotFound("Requested title not present [" + title + "]");
        }
    }

    public void deleteTodoItemByIsDone() {
        todoItemRepository.deleteAllByIsDone(true);
    }

    public void deleteTodoItemByIsNotDone() {
        todoItemRepository.deleteAllByIsDone(false);
    }

    private ReturnTodoItemDto mapToDto(TodoItem todoItem) {
        return new ReturnTodoItemDto(
              todoItem.getId(),
              todoItem.getTitle(),
              todoItem.getDescription(),
              todoItem.getCreatedAt(),
              todoItem.getIsDone(),
              todoItem.getTodoItemType()
        );
    }

}