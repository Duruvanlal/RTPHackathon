import { Injectable, Renderer2 } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { GlobalProperty } from '../../global';
import {YodleeAccount} from './../models/yodlee.account.model';
import { Subject, BehaviorSubject, Observable, of, concat } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class YodleeService {

  headers = new HttpHeaders();
  
  token : any;

  constructor(private http: HttpClient,private global: GlobalProperty) { 
  }

  public getUserToken(): Observable<any> {
    return this.http.get<any>('/token');
  }

  public getUserAccounts(): Observable<YodleeAccount> {

    this.headers = new HttpHeaders({
      "Authorization": "Bearer eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIwMDk4YmVmMC01NjQxZjBmOC00Y2NmLTRmYTItYmU1Mi00ODY3ZmI0NzQ2ZmMiLCJpYXQiOjE1NTgzNTg1NTEsImV4cCI6MTU1ODM2MDM1MSwic3ViIjoic2JNZW1xcU1obFREaUtweVpwMSJ9.dg-x4zkuwRq9K53DqE8RBCDBVXrqUkE3vho2o8ies2XqWOcaHTonlMHr7hNTAym3wUn5sGxZ5KFP6XU5x-a9RfCr4xARBY5P3tSdt6wW5HAjwhLqkKQBjO79IQ_oKJI0Ay0-9Fjg-re-WSTM65XUvm_Wy4AL9kLcY6LxHeBHLwqBE0E2wPc94Avs9UanjsBIYAzcd819E3CwMFqo2YUApSbkE4oQ-Ara-kjlAkOXGYTPpRoFHOvgJ7gBNt5wyuCrA6yyt_fbX0XunKx7GLonng4_5DQPJdaJNjwPQG0V-_pizrDMTyIk2jUaQSUI5xX2403NZmg5oCZyHSAcC721dQ", 
      "Api-Version": "1.1"
  })
  //  this.headers = new HttpHeaders();
  //  this.headers.set('', 'Bearer eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIwMDk4YmVmMC01NjQxZjBmOC00Y2NmLTRmYTItYmU1Mi00ODY3ZmI0NzQ2ZmMiLCJpYXQiOjE1NTgwMzI4NjYsImV4cCI6MTU1ODAzNDY2Niwic3ViIjoic2JNZW1xcU1obFREaUtweVpwMSJ9.HnSB4nl61L_zfwnTcu950eD86fSkOaU-49NZUjLOZFcsAGrM_ei0sewl25qyTTP2mME2l8-mv3j51x9hwgmyzFKoIlrOGlDiwDsG29lQRKETbOvAtFRygcz_rbSG08mil8_GwYmSaBKcWSzRcrGe0YJlL_8VHvmULrlYBoIEd5RG8DjDpYWl0bnqI7T7A0STDsZdsFO5Y6IaOAFEtV5KX8PPFw3hofDnvDfVy_V0i2R9yLN52lyuDM5tt4HGBwCRx7LlufUDscj2ed2I_bxDNgUPfilrc92mLxjTXAlmYzHW59Jlvs8sEIRAsEMj6Hv9PGL3IaTCLrjsBgrCBZKNEA');
 //   this.headers.set('Api-Version', '1.1');
    return this.http.get<YodleeAccount>('/accounts',{ headers: this.headers });
  }


}
