import { Component, OnInit, ViewEncapsulation, TemplateRef, ViewChild } from '@angular/core';
import {InvoiceMaster, InvoiceDetail} from './../../models/invoice.mode';
import {User} from './../../models/user.mode';
import {UserService} from './../../services/user.service';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-payables',
  templateUrl: './payables.component.html',
  styleUrls: ['./payables.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class PayablesComponent implements OnInit {

  invoiceMasterList : InvoiceMaster[] = []; 
  currentUser : User;
  @ViewChild('viewInvoice')  viewInvoice: TemplateRef<any>;
  @ViewChild('viewInvoiceModal') viewInvoiceModal: ModalDirective;

  rows= [];
  columns = [];
  temp = [];

   constructor(private userService : UserService) {
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.userService.getPayables(this.currentUser.userName).subscribe(
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
        || d.sellerUserId.toLowerCase().indexOf(val) !== -1
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

  ngOnInit() {

    this.columns = [
      { name: 'Invoice Ref Id',prop:'invoiceRefId' },
      { name: 'PO Number',prop:'poNumber'  },
      { name: 'Invoice Date' ,prop:'inventorySubDateTime' },
      { name: 'Due Date',prop:'inventoryDueDateTime'  },
      { name: 'Merchant ID' ,prop:'sellerUserId' },
      { name: 'Action', cellTemplate: this.viewInvoice  }
    ];
  }

}

