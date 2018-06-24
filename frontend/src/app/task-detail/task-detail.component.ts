import { Component, OnInit, Input } from '@angular/core';
import { Task } from '../task';
import { TasksService } from '../tasks.service';
@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {

  constructor(private tasksService: TasksService) { }

  ngOnInit() {
  }

  onUpdate(task: Task): void {
    this.tasksService.updateTask(task);
  }

  onResolve(task: Task): void {
    this.tasksService.resolveTask(task);
  }

  onPostpone(task: Task): void {
    this.tasksService.postponeTask(task);
  }

  @Input() task: Task;
}
