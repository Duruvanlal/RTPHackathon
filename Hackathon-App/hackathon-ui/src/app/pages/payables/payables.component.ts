import { Component, OnInit, ViewEncapsulation, TemplateRef, ViewChild } from '@angular/core';
import {InvoiceMaster, InvoiceDetail,RfpRequest} from './../../models/invoice.mode';
import {User} from './../../models/user.mode';
import {UserService} from './../../services/user.service';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ToastrService } from 'ngx-toastr';

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

   constructor(private userService : UserService,private toastrService : ToastrService) {
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
  reasonForAction = '';
  approveInvoice() {
    let rfpRequest = new RfpRequest();
    rfpRequest.action = 'APPROVE';
    rfpRequest.referenceId= this.invoiceDetail.invoiceRefId;
    rfpRequest.rejectReason = this.reasonForAction

    this.userService.postRfpReq(rfpRequest).subscribe(
      res=>{
        this.toastrService.success('Approval done');
      },error =>{
        this.toastrService.error('Error in Approval process');
      }
    );
    this.reasonForAction = '';
    this.userService.refresh();
  }

  rejectInvoice() {
    if (this.reasonForAction != null && this.reasonForAction != undefined &&
      this.reasonForAction.length > 0) {

        let rfpRequest = new RfpRequest();
        rfpRequest.action = 'REJECT';
        rfpRequest.referenceId= this.invoiceDetail.invoiceRefId;
        rfpRequest.rejectReason = this.reasonForAction;

        this.userService.postRfpReq(rfpRequest).subscribe(
          res=>{
            this.toastrService.success('Invoice has rejected');
          },error =>{
            this.toastrService.error('Error in rejection process');
          }
        );
        this.reasonForAction = '';
        this.userService.refresh();
    } else {
      this.toastrService.info('Reject reason is required');
    }
    this.reasonForAction = '';

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

