package com.amicabile.queapp;

import com.amicabile.queapp.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.Random;


@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private Random rand = new Random();
    final TaskService taskService;
    private int lenTasks = 0;

    public ScheduledTasks(TaskService taskService) {
        this.taskService = taskService;
    }

    @Scheduled(fixedRate = 100)
    public void scheduleTask() {
        int  n = rand.nextInt(100) + 1;
        if (n == 1 || lenTasks  == 0) {
            taskService.createGenericTask();
            lenTasks++;
        }


    }


}
