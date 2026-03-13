package com.example.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin // allow your React app; tighten origins in production
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoItem> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> get(@PathVariable long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TodoItem> create(@RequestBody TodoItemRequest req) {
        TodoItem created = service.create(req.title(), req.completed());
        return ResponseEntity.created(URI.create("/api/todos/" + created.id())).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoItem> update(@PathVariable long id, @RequestBody TodoItemRequest req) {
        return service.update(id, req.title(), req.completed())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Simple DTO for requests
    public record TodoItemRequest(String title, Boolean completed) {}
}

@Service
class TodoService {
    private final Map<Long, TodoItem> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public List<TodoItem> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<TodoItem> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    public TodoItem create(String title, Boolean completed) {
        long id = seq.getAndIncrement();
        TodoItem item = new TodoItem(id, title, completed != null && completed);
        store.put(id, item);
        return item;
    }

    public Optional<TodoItem> update(long id, String title, Boolean completed) {
        return Optional.ofNullable(store.computeIfPresent(id, (k, v) -> {
            String newTitle = title != null ? title : v.title();
            boolean newCompleted = completed != null ? completed : v.completed();
            return new TodoItem(id, newTitle, newCompleted);
        }));
    }

    public boolean delete(long id) {
        return store.remove(id) != null;
    }
}

// Response model sent to the React app
record TodoItem(long id, String title, boolean completed) {}
