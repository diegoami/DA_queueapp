package com.amicabile.queapp.controller;
import com.amicabile.queapp.domain.Task;
import com.amicabile.queapp.repository.TaskRepository;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository ) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task saved = taskRepository.save(task);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        final List<Task> resultList = new ArrayList<>();
        final Iterable<Task> all = taskRepository.findAll();
        all.forEach(resultList::add);
        return ResponseEntity.ok().body(resultList);
    }

}
