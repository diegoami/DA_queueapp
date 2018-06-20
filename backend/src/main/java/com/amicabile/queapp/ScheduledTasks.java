package com.amicabile.queapp;
import com.amicabile.queapp.domain.Task;
import com.amicabile.queapp.domain.TaskStatus;
import com.amicabile.queapp.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.Random;


@Component

public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private Random rand = new Random();
    final TaskRepository taskRepository;

    public ScheduledTasks(TaskRepository taskRepository ) {
        this.taskRepository = taskRepository;
    }

    @Scheduled(fixedRate = 100)
    public void scheduleTask() {
        int  n = rand.nextInt(500) + 1;
        if (n == 1) {
            Date createdAt = new Date();
            LocalDateTime createdAtDateTime = createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            // plus one
            LocalDateTime dueDateTime = createdAtDateTime.plusDays(5);
            Date dueDate = Date.from(dueDateTime.atZone(ZoneId.systemDefault()).toInstant());

            // conver
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


}
