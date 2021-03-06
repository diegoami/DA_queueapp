import {Component, OnDestroy, OnInit} from '@angular/core';
import { TasksService } from '../tasks.service';
import { Task} from '../task';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit, OnDestroy {

  tasks: Task[];
  interval: any;
  selectedTask: Task;

  constructor(private tasksService: TasksService) { }

  getTasks(): void {
    this.tasksService.getTasks()
      .subscribe(tasks => this.tasks = tasks);
  }

  onSelect(task: Task): void {
    this.selectedTask = task;
  }



  ngOnInit() {
    this.getTasks();
    this.interval = setInterval(() => {
      this.getTasks();
    }, 5000);
  }

  ngOnDestroy() {
    clearInterval(this.interval);
  }



}
