import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {UserService} from './../../services/user.service';
import { Router } from '@angular/router';
import { User,InvoiceCustomer } from '../../models/user.mode';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class CustomerComponent implements OnInit {

  currentUser : User;
  invoiceCustomerList :InvoiceCustomer[] = [];
  rows= [];
  columns = [];
  temp = [];

  constructor(private userService : UserService,private router : Router,) {
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.userService.getInvoiceCustomers(this.currentUser.userName).subscribe(
      res =>{
        this.invoiceCustomerList = res;
        for(let cust of this.invoiceCustomerList){
          if(cust.userId=='RonnieT'){
            cust.firstName='Anthony';
            cust.lastName='Turner';
            cust.company = 'Swapstech';
          }else if(cust.userId=='AnthonyT'){
            cust.firstName='Ronnie';
            cust.lastName='Tepper';
            cust.company='Oracle';
          }
        }
        this.temp = [... this.invoiceCustomerList];
        this.rows =  this.invoiceCustomerList;
      }, error => {

      }
    );
   }

   updateFilter(event) {
    const val = event.target.value.toLowerCase();
    const temp = this.temp.filter(function (d) {

      return d.userId.toLowerCase().indexOf(val) !== -1
        || d.upaCd.toLowerCase().indexOf(val) !== -1
        || !val;
    });
    this.rows = temp;
  }

  // showZeroCustomerMsg = false;
  // showZeroCustomers(){
  //   if(this.invoiceCustomerList.length < 1){
  //       this.showZeroCustomerMsg = true;
  //   }
  // }
  

  ngOnInit() {

    this.columns = [
      { name: 'First Name',prop:'firstName'  },
      { name: 'Last Name',prop:'lastName'  },
      { name: 'Company',prop:'company'  },
      { name: 'UPA' ,prop:'upaCd' }
    ];
  }



}
