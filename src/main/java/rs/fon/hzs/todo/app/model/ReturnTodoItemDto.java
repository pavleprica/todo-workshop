package rs.fon.hzs.todo.app.model;

import java.time.Instant;

public record ReturnTodoItemDto(
        Long id,
        String title,
        String description,
        Instant createdAt,
        Boolean isDone,
        TodoItemType todoItemType
) {
}
