import { Injectable } from '@angular/core';

@Injectable()
export class GlobalProperty {
    url = 'https://localhost:9090/employer/';
    principal = localStorage.getItem("user");
}