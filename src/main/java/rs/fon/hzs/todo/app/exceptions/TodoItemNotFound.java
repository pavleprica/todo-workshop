package rs.fon.hzs.todo.app.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoItemNotFound extends RuntimeException {

    public TodoItemNotFound(String message) {
        super(message);
    }

}
