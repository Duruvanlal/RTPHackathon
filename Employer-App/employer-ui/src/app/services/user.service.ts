import { Injectable, Renderer2 } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { GlobalProperty } from '../../global';

@Injectable()
export class UserService {

  constructor(private http: HttpClient,
              private global : GlobalProperty
            ){} 

  public registerUser(_user) {
    return this.http.post(this.global.url+"register",_user);
  }

  public login(_user) {
    return this.http.post(this.global.url+"login",_user);
  }

  public findAllUsers(_companyId) {
    return this.http.get(this.global.url+"user/"+_companyId);
  }

}