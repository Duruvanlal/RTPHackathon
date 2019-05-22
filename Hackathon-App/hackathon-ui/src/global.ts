import { Injectable } from '@angular/core';

@Injectable()
export class GlobalProperty {
    url = 'http://localhost:9090/employee/';
    principal = localStorage.getItem("user");
}