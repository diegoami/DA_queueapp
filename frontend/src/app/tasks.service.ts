import { Injectable } from '@angular/core';
import { Task } from './task';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { environment } from '../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
  })
};


@Injectable({
  providedIn: 'root'
})
export class TasksService {

  getTasksUrl = environment.getTasksUrl;
  putTaskUrl = environment.putTasksUrl;

  constructor(private http: HttpClient, private datePipe: DatePipe) { }

  private transformDate(date) {
    return this.datePipe.transform(date, 'yyyy/MM/dd');
  }

  private padNumber(number) {
    return ('0' + number).slice(-2);
  }

  getTasks(): Observable<Task[]> {
      return this.http.get<Task[]>(this.getTasksUrl);
  }

  updateTask(task: Task): void {
    this.http.put(this.putTaskUrl + task.id, JSON.stringify(task), httpOptions).
      subscribe();
  }

  resolveTask(task: Task): void {
    task.status = 'DONE';
    task.resolvedAt = this.transformDate(new Date());
    this.updateTask(task);
  }

  postponeTask(task: Task): void {
    const dueDate = new Date(task.dueDate);
    dueDate.setDate(dueDate.getDate() + 1 );
    task.dueDate = dueDate.getFullYear() + '/' + this.padNumber(dueDate.getMonth() + 1) + '/' + this.padNumber(dueDate.getDate());
    this.updateTask(task);
  }


  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
