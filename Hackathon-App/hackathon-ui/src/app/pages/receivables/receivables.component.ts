import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {InvoiceMaster} from './../../models/invoice.mode';
import {User} from './../../models/user.mode';
import {UserService} from './../../services/user.service';

@Component({
  selector: 'app-receivables',
  templateUrl: './receivables.component.html',
  styleUrls: ['./receivables.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class ReceivablesComponent implements OnInit {

  invoiceMasterList : InvoiceMaster[] = []; 
  currentUser : User;

  rows= [];
  columns = [];
  temp = [];
  constructor(private userService : UserService) {
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.userService.getReceivables(this.currentUser.userName).subscribe(
      res =>{
        this.invoiceMasterList = res;
        this.temp = [... this.invoiceMasterList];
        this.rows =  this.invoiceMasterList;
      }, error => {

      }
    );
   }

   updateFilter(event) {
    const val = event.target.value.toLowerCase();
    const temp = this.temp.filter(function (d) {

      return d.invoiceRefId.toLowerCase().indexOf(val) !== -1
        || d.buyerUserId.toLowerCase().indexOf(val) !== -1
        || !val;
    });
    this.rows = temp;
  }

  ngOnInit() {

    this.columns = [
      { name: 'Invoice Ref Id',prop:'invoiceRefId' },
      { name: 'PO Number',prop:'poNumber'  },
      { name: 'Invoice Date' ,prop:'inventorySubDateTime' },
      { name: 'Due Date',prop:'inventoryDueDateTime'  },
      { name: 'Buyer Id' ,prop:'buyerUserId' }
    ];
  }

}
