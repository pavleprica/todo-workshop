package rs.fon.hzs.todo.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rs.fon.hzs.todo.app.model.CreateTodoItemDto;
import rs.fon.hzs.todo.app.model.ReturnTodoItemDto;
import rs.fon.hzs.todo.app.model.TodoItem;
import rs.fon.hzs.todo.app.service.TodoItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todo-items")
@RequiredArgsConstructor
@Slf4j
public class TodoItemController {

    /*
    1. Get all todos GET - done
    2. Get todo by id GET - done
    3. Get todo by title GET - done
    4. Create todo POST - done
    5. Update todo PUT - done
    6. Mark is done PUT - done
    7. Mark is not done PUT - done
    8. Delete todo DELETE - done
    9. Delete by status DELETE - done
     */

    private final TodoItemService todoItemService;

    @GetMapping
    List<ReturnTodoItemDto> getAllTodos() {
        log.info("Getting all todos");
        return todoItemService.getAllTodos();
    }

    @GetMapping("/{id}")
    ReturnTodoItemDto getTodoItemById(@PathVariable Long id) {
        log.info("Getting todo by id {}", id);
        return todoItemService.getTodoItemById(id);
    }

    @GetMapping("/title-search")
    ReturnTodoItemDto getTodoItemByTitle(@RequestParam String title) {
        return todoItemService.getTodoItemByTitle(title);
    }

    @PostMapping
    ReturnTodoItemDto createTodoItem(@RequestBody @Valid CreateTodoItemDto todoItem) {
        TodoItem createTodo = TodoItem.builder()
                .title(todoItem.title())
                .description(todoItem.description())
                .isDone(todoItem.isDone())
                .todoItemType(todoItem.todoItemType())
                .build();
        return todoItemService.createTodoItem(createTodo);
    }

    @PutMapping("/{id}")
    ReturnTodoItemDto updateTodoItemById(@PathVariable Long id, @RequestBody TodoItem todoItemUpdated) {
        return todoItemService.updateTodoItemById(id, todoItemUpdated);
    }

    @PutMapping("/{id}/is-done")
    ReturnTodoItemDto markTodoItemAsDone(@PathVariable Long id) {
        return todoItemService.markIsDoneById(id);
    }

    @PutMapping("/{id}/is-not-done")
    ReturnTodoItemDto markTodoItemAsNotDone(@PathVariable Long id) {
        return todoItemService.markIsNotDoneById(id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        todoItemService.deleteTodoItemById(id);
    }

    @DeleteMapping
    void deleteByTitle(@RequestParam String title) {
        todoItemService.deleteTodoItemByTitle(title);
    }

    @DeleteMapping("/is-done")
    void deleteByDone() {
        todoItemService.deleteTodoItemByIsDone();
    }

    @DeleteMapping("/is-not-done")
    void deleteByNotDone() {
        todoItemService.deleteTodoItemByIsNotDone();
    }

}
