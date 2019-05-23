import { Component, OnInit, ViewEncapsulation,ViewChild, TemplateRef } from '@angular/core';
import {User} from './../../models/user.mode';
import {UserService} from './../../services/user.service';
import { ModalDirective } from 'ngx-bootstrap/modal';
import {InvoiceMaster, InvoiceDetail} from './../../models/invoice.mode';


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
  @ViewChild('viewInvoiceModal') viewInvoiceModal: ModalDirective;

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
      { name: 'Cust Id' ,prop:'buyerUserId' },
      { name: 'Action', cellTemplate: this.viewInvoice  }
    ];
  }

}
