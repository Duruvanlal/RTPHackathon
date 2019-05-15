import { Injectable } from '@angular/core';

@Injectable()
export class GlobalProperty {
    url = 'https://localhost:8080/employee/';
    principal = localStorage.getItem("user");
}