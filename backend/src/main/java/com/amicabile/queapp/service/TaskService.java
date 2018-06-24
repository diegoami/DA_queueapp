package com.amicabile.queapp.service;

import com.amicabile.queapp.domain.Task;
import com.amicabile.queapp.domain.TaskStatus;
import com.amicabile.queapp.dto.TaskDTO;
import com.amicabile.queapp.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;


import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private Random rand = new Random();
    final TaskRepository taskRepository;

    public TaskService (TaskRepository taskRepository ) {
        this.taskRepository = taskRepository;
    }

    public void createGenericTask() {
        Date createdAt = new Date();
        LocalDateTime createdAtDateTime = createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        int days = rand.nextInt(7) + 1;
        LocalDateTime dueDateTime = createdAtDateTime.plusDays(days);
        Date dueDate = Date.from(dueDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Task task = new Task();
        int taskNumber = rand.nextInt(1000000) + 1;
        task.setCreatedAt(createdAt);
        task.setUpdatedAt(createdAt);
        int priority = rand.nextInt(10) + 1;
        task.setPriority(priority);
        task.setTitle("TASK #" + taskNumber );
        task.setDescription("DESCRIPTION FOR TASK #" + taskNumber );

        task.setStatus(TaskStatus.PENDING);
        task.setDueDate(dueDate);
        task.setResolvedAt(null);
        this.taskRepository.save(task);
    }

    public List<TaskDTO> retrieveAllTasks() {
        final List<TaskDTO> resultList = new ArrayList<>();
        final Iterable<Task> all = taskRepository.findAll();
        all.forEach(task -> {
            if (task.getStatus().equals(TaskStatus.PENDING))
                resultList.add(convertToDto(task));
        });

        Collections.sort(resultList, Comparator.comparing(TaskDTO::getDueDate).thenComparing(Comparator.comparing(TaskDTO::getPriority)).reversed());

        return resultList;
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

    private Date retrieveDate(String dateString) {
        try {
            if (dateString != null) {
                return dateFormat.parse(dateString);
            } else {
                return null;
            }
        } catch (java.text.ParseException pe) {
            return null;
        }
    }

    public Task update(Long id, TaskDTO taskDto) {

        Task storedTask = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " not found"));
        storedTask.setResolvedAt(retrieveDate(taskDto.getResolvedAt()));
        storedTask.setCreatedAt(retrieveDate(taskDto.getCreatedAt()));
        storedTask.setPriority(taskDto.getPriority());
        storedTask.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
        storedTask.setUpdatedAt(new Date());
        storedTask.setTitle(taskDto.getTitle());
        storedTask.setDescription(taskDto.getDescription());
        storedTask.setDueDate(retrieveDate(taskDto.getDueDate()));
        Task saved = taskRepository.save(storedTask);
        return saved;

    }

    private String convertToDate(Date date) {
        if (date != null) {
            return dateFormat.format(date);
        } else {
            return null;
        }
    }

    private Sort sortByDatePriority() {
        return new Sort(Sort.Direction.ASC, "dueDate", "priority");
    }
}
