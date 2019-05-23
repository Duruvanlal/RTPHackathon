import { Injectable } from '@angular/core';

@Injectable()
export class GlobalProperty {
    url = 'http://localhost:9090/employee/';
    yodleeUrl = 'https://sandbox.api.yodlee.com/ysl';
    principal = localStorage.getItem("user");
    yoddleToken = localStorage.getItem("yoddleToken");
}