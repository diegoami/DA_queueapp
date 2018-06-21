import { Component, OnInit } from '@angular/core';
import { TasksService } from '../tasks.service';
import { Task} from '../task';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  tasks: Task[];
  constructor(private tasksService: TasksService) { }

  getTasks(): void {
    this.tasksService.getTasks()
      .subscribe(tasks => this.tasks = tasks);
  }

  ngOnInit() {
  }

}
