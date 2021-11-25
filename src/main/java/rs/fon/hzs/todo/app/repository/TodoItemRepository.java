package rs.fon.hzs.todo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import rs.fon.hzs.todo.app.model.TodoItem;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    Optional<TodoItem> findByTitle(String title);

    @Transactional
    @Modifying
    void deleteByTitle(String title);

    @Transactional
    @Modifying
    void deleteAllByIsDone(Boolean isDone);

}
