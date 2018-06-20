package com.amicabile.queapp.service;

import com.amicabile.queapp.domain.Task;
import com.amicabile.queapp.domain.TaskStatus;
import com.amicabile.queapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class TaskService {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

    final TaskRepository taskRepository;

    public TaskService (TaskRepository taskRepository ) {
        this.taskRepository = taskRepository;
    }

    public void createGenericTask() {
        Date createdAt = new Date();
        LocalDateTime createdAtDateTime = createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime dueDateTime = createdAtDateTime.plusDays(5);
        Date dueDate = Date.from(dueDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Task task = new Task();
        task.setCreatedAt(createdAt);
        task.setUpdatedAt(createdAt);
        task.setPriority(1);
        task.setTitle("TASK " + dateFormat8.format(createdAtDateTime));
        task.setDescription("DESCRIPTION " + dateFormat8.format(createdAtDateTime));

        task.setStatus(TaskStatus.PENDING);
        task.setDueDate(dueDate);
        task.setResolvedAt(null);
        this.taskRepository.save(task);
    }
}
