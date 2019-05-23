import { Injectable, Renderer2 } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { GlobalProperty } from '../../global';
import { User,Token,UserPmtAccount } from './../models/user.mode';
import {InvoiceMaster,InvoiceDetail} from './../models/invoice.mode';
import { Subject, BehaviorSubject, Observable, of, concat } from 'rxjs';

@Injectable()
export class UserService {

  constructor(private http: HttpClient,
    private global: GlobalProperty
  ) { }

  public registerUser(_user) {
    return this.http.post(this.global.url + "register", _user);
  }

  public login(_user) {
    return this.http.post(this.global.url + "login", _user);
  }

  public getYodleeToken() : Observable<Token>{
    return this.http.get<Token>(this.global.url + "token");
  }

  public getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.global.url + "users");
  }

  public getAllUserAccounts(): Observable<UserPmtAccount[]> {
    return this.http.get<UserPmtAccount[]>(this.global.url + "user-accounts");
  }

  public submitInvoice(invoice) {
    return this.http.post(this.global.url + "invoice", invoice);
  }

  public getReceivables(sellerId): Observable<InvoiceMaster[]> {
    return this.http.get<InvoiceMaster[]>(this.global.url + "invoice/seller/"+sellerId);
  }

  public getPayables(buyerId): Observable<InvoiceMaster[]> {
    return this.http.get<InvoiceMaster[]>(this.global.url + "invoice/buyer/"+buyerId);
  }

  public postUserAct(data) {
    return this.http.post(this.global.url + "user-accounts", data);
  }
}