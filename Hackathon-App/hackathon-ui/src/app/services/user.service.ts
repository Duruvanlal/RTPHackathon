import { Injectable, Renderer2 } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { GlobalProperty } from '../../global';
import { User,Token,UserPmtAccount } from './../models/user.mode';
import {InvoiceMaster,InvoiceDetail} from './../models/invoice.mode';
import { Subject, BehaviorSubject, Observable, of, concat } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class UserService {

  constructor(private http: HttpClient,private router:Router,
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

  public getUserAccounts(userId): Observable<UserPmtAccount[]> {
    return this.http.get<UserPmtAccount[]>(this.global.url + "user-accounts/"+userId);
  }

  public getReceiverAccounts(userId): Observable<UserPmtAccount[]> {
    return this.http.get<UserPmtAccount[]>(this.global.url + "receiver-user-accounts/"+userId);
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

  public updateUserAct(data) {
    return this.http.put(this.global.url + "user-accounts", data);
  }

  public refresh() {
    this.router.routeReuseStrategy.shouldReuseRoute = function () { return false; };
    let currentUrl = this.router.url + '?';
    this.router.navigateByUrl(currentUrl)
      .then(() => {
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      });
  }
}