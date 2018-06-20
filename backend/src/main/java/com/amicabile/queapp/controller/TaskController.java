package com.amicabile.queapp.controller;
import com.amicabile.queapp.domain.Task;
import com.amicabile.queapp.dto.TaskDTO;
import com.amicabile.queapp.repository.TaskRepository;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    final TaskRepository taskRepository;
    final ModelMapper modelMapper;

    public TaskController(TaskRepository taskRepository, ModelMapper modelMapper ) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task saved = taskRepository.save(task);
        return ResponseEntity.ok().body(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@RequestParam Long id, @RequestBody TaskDTO task) {

        Task storedTask = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " not found"));
        storedTask.setResolvedAt(task.getResolvedAt());
        storedTask.setCreatedAt(task.getCreatedAt());
        storedTask.setPriority(task.getPriority());
        storedTask.setStatus(task.getStatus());
        storedTask.setUpdatedAt(task.getUpdatedAt());
        storedTask.setTitle(task.getTitle());
        storedTask.setDescription(task.getDescription());
        storedTask.setDueDate(task.getDueDate());
        Task saved = taskRepository.save(storedTask);

        return ResponseEntity.ok().body(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> findAll() {
        final List<TaskDTO> resultList = new ArrayList<>();
        final Iterable<Task> all = taskRepository.findAll();
        all.forEach(task -> resultList.add(convertToDto(task)));
        return ResponseEntity.ok().body(resultList);
    }


    private TaskDTO convertToDto(Task task) {
        TaskDTO taskDto = modelMapper.map(task, TaskDTO.class);
        return taskDto ;
    }
}
