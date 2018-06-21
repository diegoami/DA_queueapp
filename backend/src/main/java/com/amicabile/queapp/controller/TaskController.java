package com.amicabile.queapp.controller;
import com.amicabile.queapp.domain.Task;
import com.amicabile.queapp.domain.TaskStatus;
import com.amicabile.queapp.dto.TaskDTO;
import com.amicabile.queapp.repository.TaskRepository;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
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
    public ResponseEntity<Task> update(@RequestParam Long id, @RequestBody TaskDTO taskDto) {

        Task storedTask = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " not found"));
        storedTask.setResolvedAt(retrieveDate(taskDto.getResolvedAt()));
        storedTask.setCreatedAt(retrieveDate(taskDto.getCreatedAt()));
        storedTask.setPriority(taskDto.getPriority());
        storedTask.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
        storedTask.setUpdatedAt(retrieveDate(taskDto.getUpdatedAt()));
        storedTask.setTitle(taskDto.getTitle());
        storedTask.setDescription(taskDto.getDescription());
        storedTask.setDueDate(retrieveDate(taskDto.getDueDate()));
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
        TaskDTO taskDto = new TaskDTO();
        taskDto.setCreatedAt(convertToDate(task.getCreatedAt()));
        taskDto.setId(task.getId());
        taskDto.setResolvedAt(convertToDate(task.getResolvedAt()));
        taskDto.setUpdatedAt(convertToDate(task.getUpdatedAt()));
        taskDto.setStatus(task.getStatus().name());
        taskDto.setDueDate(convertToDate(task.getDueDate()));
        taskDto.setDescription(task.getDescription());
        taskDto.setTitle(task.getTitle());
        taskDto.setPriority(task.getPriority());
        return taskDto;
    }


    private String convertToDate(Date date) {
        if (date != null) {
            return format.format(date);
        } else {
            return null;
        }
    }
    private Date retrieveDate(String dateString) {
        try {
            return format.parse(dateString);
        } catch (java.text.ParseException pe) {
            return null;
        }
    }
}
