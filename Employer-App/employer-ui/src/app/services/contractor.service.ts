import { Injectable, Renderer2 } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { GlobalProperty } from '../../global';

@Injectable()
export class ContractorService {

  constructor(private http: HttpClient,
              private global : GlobalProperty
            ){} 

  public hire(_contractor,_firstName,_lastName,_userName) {
    let _url = this.global.url+"contractor/hire/"+_firstName+"/"+ _lastName + "/" + _userName;
    return this.http.post(_url,_contractor);
  }

  public findAll(_companyId) {
    return this.http.get(this.global.url + "contractor/"+_companyId);
  }

}