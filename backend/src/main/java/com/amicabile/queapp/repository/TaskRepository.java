package com.amicabile.queapp.repository;

import com.amicabile.queapp.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
