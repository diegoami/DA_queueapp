import { Component, OnInit } from '@angular/core';
import { TasksService } from '../tasks.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  tasks: Task[];
  constructor(private tasksService: TasksService) { }

  getTasks(): void {
    this.tasks = this.taskService.getTasks();
  }

  ngOnInit() {
  }

}
