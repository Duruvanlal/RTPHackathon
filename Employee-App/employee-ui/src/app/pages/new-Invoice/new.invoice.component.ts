import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { GlobalProperty } from '../../../global';
import { UserService } from '../../services/user.service';
import { Invoice } from '../../models/invoice.mode';


@Component({
  selector: 'app-new-invoice',
  templateUrl: './new.invoice.component.html',
  styleUrls: ['./new.invoice.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers:[UserService]
})
export class NewInvoiceComponent implements OnInit {
  public loading = true;
  public user:any;
  public invoice = new Invoice();
  public userList = [];




  constructor(private global:GlobalProperty,
            private userService:UserService) { }

  ngOnInit() { 
    if(this.global.principal){
      this.user = JSON.parse(this.global.principal);
      console.log("this.user",this.user);
      this.getAllUsersInfo();
    }
  }

  // Load all Users Information.
  private getAllUsersInfo(){
    this.userService.getAllUsers().subscribe((response)=>{
        console.log("Response = " , response);
        this.userList = <any>response;
        this.loading = false;
    });
  }

  // Update Buyer Information on dropdown change.
  public selectSender(event){
    console.log(event);
    this.invoice.sender = event.value.company;
    console.clear();
    console.log("this.invoice.sender = ", this.invoice.sender);
  }

  public selectReceiver(event){
    console.log(event);
    this.invoice.receiver = event.value.company;
    console.clear();
    console.log("this.invoice.sender = ", this.invoice.sender);
  }











}
