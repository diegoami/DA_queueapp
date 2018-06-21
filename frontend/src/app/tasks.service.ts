import { Injectable } from '@angular/core';
import { Task } from './task';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
  })
};


@Injectable({
  providedIn: 'root'
})
export class TasksService {

  taskUrl = 'http://localhost:9095/tasks/all';
  constructor(private http: HttpClient) { }

  getTasks(): Observable<Task[]> {
      return this.http.get<Task[]>(this.taskUrl);
  }
}
