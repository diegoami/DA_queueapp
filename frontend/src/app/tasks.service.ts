import { Injectable } from '@angular/core';
import { Task } from './task';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {catchError} from "rxjs/internal/operators";


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
  })
};


@Injectable({
  providedIn: 'root'
})
export class TasksService {

  getTasksUrl = 'http://localhost:9095/tasks/all';
  putTaskUrl = 'http://localhost:9095/tasks/';
  constructor(private http: HttpClient) { }

  getTasks(): Observable<Task[]> {
      return this.http.get<Task[]>(this.getTasksUrl);
  }

  updateTask(task: Task): void {
    console.log('in updateTask');
    const urlToCall = this.putTaskUrl + task.id;
    let payload = JSON.stringify(task);
    console.log(urlToCall);
    console.log(payload);
    this.http.put(urlToCall, payload, httpOptions).subscribe();
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
