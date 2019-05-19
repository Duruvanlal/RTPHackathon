import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { GlobalProperty } from '../../../../global';


@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class UserMenuComponent implements OnInit {


  public user:any;


  constructor(
        private global:GlobalProperty,

    ) { }

  ngOnInit() {
    this.user = JSON.parse(this.global.principal);
    console.log("this.user = ",this.user);
  }






}
