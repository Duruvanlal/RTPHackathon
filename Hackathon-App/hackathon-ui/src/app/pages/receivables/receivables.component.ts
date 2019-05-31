import { Component, OnInit, ViewEncapsulation,ViewChild, TemplateRef } from '@angular/core';
import {User} from './../../models/user.mode';
import {UserService} from './../../services/user.service';
import { ModalDirective } from 'ngx-bootstrap/modal';
import {InvoiceMaster, InvoiceDetail} from './../../models/invoice.mode';
import { Router } from '@angular/router';
import { CurrencyPipe,DatePipe } from '@angular/common';


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

  @ViewChild('viewInvoice')  viewInvoice: TemplateRef<any>;
  @ViewChild('company')  company: TemplateRef<any>;
  @ViewChild('viewInvoiceModal') viewInvoiceModal: ModalDirective;

  rows= [];
  columns = [];
  temp = [];
  constructor(private userService : UserService,private router : Router,) {
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

  invoiceDetail :InvoiceDetail;

  action(row){
    this.viewInvoiceModal.show();
    this.invoiceDetail = row.invoiceDetail;
    console.log('invoiceDetail '+JSON.stringify(this.invoiceDetail));
  }

  goToNewInvoice(){
    this.router.navigate(['/pages/new/invoice']);
  }
  ngOnInit() {

    this.columns = [
      { name: 'Invoice Ref Id',prop:'invoiceRefId' },
      { name: 'PO Number',prop:'poNumber'  },
      { name: 'Invoice Date' ,prop:'inventorySubDateTime',pipe: new DatePipe('en-US') },
      { name: 'Due Date',prop:'inventoryDueDateTime',pipe: new DatePipe('en-US')  },
      { name: 'Amount' ,prop:'totalAmount',pipe: new CurrencyPipe('en-US') },
      { name: 'Customer' ,cellTemplate: this.company  },
      { name: 'Status' ,prop:'status'  },
      { name: 'Action', cellTemplate: this.viewInvoice  }
    ];
  }

  isUserRonnie(){
    if(this.currentUser.userName=='RonnieT'){
      return true;
    }
    return false;
}

isUserAnthony(){
  if(this.currentUser.userName=='AnthonyT'){
    return true;
  }
  return false;
}

isExternalUser(){
  if(this.currentUser.userName!='AnthonyT' && this.currentUser.userName!='RonnieT'){
    return true;
  }
  return false;
}

}
