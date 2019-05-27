import { Injectable } from '@angular/core';

@Injectable()
export class GlobalProperty {
    url = 'http://ec2-3-17-234-174.us-east-2.compute.amazonaws.com:9090/hackathon/';
    yodleeUrl = 'https://sandbox.api.yodlee.com/ysl';
    principal = localStorage.getItem("user");
    yoddleToken = localStorage.getItem("yoddleToken");
}