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
    console.log('Updating');
    console.log(task);
    this.tasksService.updateTask(task);
  }

  @Input() task: Task;
}
