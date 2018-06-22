package com.amicabile.queapp.controller;
import com.amicabile.queapp.domain.Task;
import com.amicabile.queapp.domain.TaskStatus;
import com.amicabile.queapp.dto.TaskDTO;
import com.amicabile.queapp.repository.TaskRepository;

import com.amicabile.queapp.service.TaskService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    final TaskService taskService;
    final ModelMapper modelMapper;

    public TaskController(TaskService taskService, ModelMapper modelMapper ) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody TaskDTO taskDto) {
        logger.info("Calling update "+id+ " with task "+taskDto);
        Task storedTask = taskService.update(id, taskDto);
        return ResponseEntity.ok().body(storedTask );
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> findAll() {
        logger.info("Calling findAll");
        final List<TaskDTO> resultList = taskService.retrieveAllTasks();
        return ResponseEntity.ok().body(resultList);
    }



}
