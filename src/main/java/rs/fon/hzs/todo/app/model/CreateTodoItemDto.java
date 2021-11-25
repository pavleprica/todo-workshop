package rs.fon.hzs.todo.app.model;

import javax.validation.constraints.NotBlank;

public record CreateTodoItemDto(
        @NotBlank
        String title,
        @NotBlank
        String description,
        Boolean isDone,
        TodoItemType todoItemType
) {
}
