import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { GlobalProperty } from '../../../global';
import { UserService } from '../../services/user.service';
import { InvoiceMaster, InvoiceDetail,RTPRequest } from '../../models/invoice.mode';
import { User,UserPmtAccount,InvoiceCustomer } from './../../models/user.mode';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { formatDate } from "@angular/common";

const dateFormat = "MM/dd/yyyy";
const dateTimeFormat = "MM/dd/yyyy HH:mm:ss";
const locale = 'en-US'


@Component({
  selector: 'app-new-invoice',
  templateUrl: './new.invoice.component.html',
  styleUrls: ['./new.invoice.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class NewInvoiceComponent implements OnInit {
  public user: any;
  public userList: User[] = [];
  public userPmtAccountList : UserPmtAccount[]=[];
  public receiverPmtAccountList : UserPmtAccount[]=[]; // UserPmtAccount
  public sender = new User();
  public receiver = new User();
  public dueDate: any;
  invoiceTable: InvoiceDetail[] = [];
  invoiceRow = new InvoiceDetail();
  currentUser : User;

  invoiceMaster = new InvoiceMaster();
  invoiceDetailList: InvoiceDetail[] = [];

  constructor(private global: GlobalProperty, private router: Router,
    private userService: UserService, private toastrService: ToastrService, ) { 
      this.currentUser = JSON.parse(localStorage.getItem('user'));
      console.log('currentUser'+ JSON.stringify(this.currentUser));
      this.invoiceMaster.sellerUserId = this.currentUser.userName;
    }

  ngOnInit() {
    // if(this.global.principal){
    //   this.user = JSON.parse(this.global.principal);
    //   console.log("this.user",this.user);

    // }
    this.getAllUsersInfo();
    this.sender.address.address1 = '';
    this.sender.address.address2 = '';
    this.sender.address.postalCode = '';
    this.sender.address.city = '';
    this.sender.address.state = '';
    this.sender.address.country = '';

    this.receiver.address.address1 = '';
    this.receiver.address.address2 = '';
    this.receiver.address.postalCode = '';
    this.receiver.address.city = '';
    this.receiver.address.state = '';
    this.receiver.address.country = '';
  }

  calculateDueDate() {
    // console.log('Date ' + this.invoiceMaster.inventorySubDateTime);
    // let dd = this.invoiceMaster.inventorySubDateTime.getDate() + 2;
    // let mm = this.invoiceMaster.inventorySubDateTime.getMonth() + 1;
    // let y = this.invoiceMaster.inventorySubDateTime.getFullYear();

    // this.dueDate = mm + '/' + dd + '/' + y;
    // this.invoiceMaster.inventoryDueDateTime = new Date(this.dueDate);
  }
  // Load all Users Information.
  private getAllUsersInfo() {
    this.userService.getAllUsers().subscribe((response) => {
      this.userList = response;
    });
    
    this.userService.getReceiverAccounts(this.currentUser.userName).subscribe((res) => {
      this.receiverPmtAccountList = res;
    });

    this.userService.getUserAccounts(this.currentUser.userName).subscribe((res) => {
      this.userPmtAccountList = res;
    });
  }

  // Update Buyer Information on dropdown change.
  public selectSender(event) {
    this.invoiceMaster.sellerUserId = event.userId;
    this.invoiceMaster.sellerUpaId = event.upaCD;
    this.sender.userName = event.userId;
  }

  public selectReceiver(event) {
    console.log('event.userName '+event.userName+' '+JSON.stringify(event));
    for(let user of this.userList){
    
      if(user.userName == event.userId){
      
        this.invoiceMaster.buyerUserId = event.userId;
        this.invoiceMaster.buyerUpaId = event.upaCD;
        this.receiver.userName = event.userId;
        this.receiver.firstName = user.firstName;
        this.receiver.lastName = user.lastName;
        this.receiver.address.address1 = user.address.address1;
        this.receiver.address.address2 = user.address.address2;
        this.receiver.address.postalCode = user.address.postalCode;
        this.receiver.address.city = user.address.city;
        this.receiver.address.state = user.address.state;
        this.receiver.address.country = user.address.country;
        console.log('Matched' +  this.receiver.address.address1);
      }
    }
  }

  addTableRow() {

    if (!this.isValidString(this.invoiceRow.productName)) {
      this.toastrService.info('Please fill Product Name');
      return;
    }
    if (this.isValidNumber(this.invoiceRow.productQty) &&
      this.isValidNumber(this.invoiceRow.unitPrice)) {
      this.invoiceRow.totalAmount = this.invoiceRow.productQty * this.invoiceRow.unitPrice;
    } else {
      this.toastrService.info('Please fill productQty and unitPrice');
      return;
    }

    this.invoiceRow.totalAmount = this.invoiceRow.totalAmount.toFixed(2);
    this.invoiceTable.push(this.invoiceRow);
    this.calculatetotalAmount();
    this.invoiceRow = <any>{};
    console.log('add ' + JSON.stringify(this.invoiceTable.length));
  }

  sendInvoice() {
    console.log('Mast ' + JSON.stringify(this.invoiceTable));

    if (this.isValidString(this.invoiceMaster.sellerUserId)
      && this.isValidString(this.invoiceMaster.buyerUserId)
      && this.invoiceMaster.inventoryDueDateTime !=null
      && this.invoiceTable.length > 0) {

        this.invoiceMaster.inventryTerm = 'Inventory';
        this.invoiceMaster.totalAmount = this.amountSubTotal;
        this.invoiceMaster.inventorySubDateTime = this.dateTimeFormatter(new Date());
        this.invoiceMaster.inventoryDueDateTime = this.dateTimeFormatter(this.invoiceMaster.inventoryDueDateTime);
     //   this.invoiceMaster.poNumber = 101;
        this.invoiceMaster.invoiceDetail = this.invoiceTable;
        // buyer, seller and sub due date auto filled in form
      
        this.userService.submitInvoice(this.invoiceMaster).subscribe((res) => {

          let rtpRequest = new RTPRequest();
          rtpRequest.paymentReasonType='INVOICE';
          rtpRequest.createdBy = this.currentUser.userName;
          rtpRequest.paymentReasonRefId = res.invoiceRefId;
          rtpRequest.paymentAmount = res.totalAmount;
          rtpRequest.payerUpaCd = this.invoiceMaster.buyerUpaId;
          rtpRequest.requestorUpaCd = this.invoiceMaster.sellerUpaId;

          this.userService.postZillTransaction(rtpRequest).subscribe(
            res =>{

            },
            error => {
              
            }
          );

          this.toastrService.success('Invoice saved successfully');
          this.router.navigate(['/pages/receivables']);
        },(error) => {
          this.toastrService.info('Error during saving the invoice');
        });
    
    }else{
      console.log('Invoice ' + JSON.stringify(this.invoiceMaster));
      this.toastrService.info('Please fill mandatory info');
    }

  }

  amountSubTotal: any;
  calculatetotalAmount() {
    this.amountSubTotal = 0;
    for (let row of this.invoiceTable) {
      if (this.isValidNumber(row.totalAmount)) {
        this.amountSubTotal = parseFloat(this.amountSubTotal) + parseFloat(row.totalAmount);
      }
    }
  }

  removeTableRow(row) {
    this.removeByAttr(this.invoiceTable, 'productName', row.productName);
  }

  removeByAttr(arr, attr, value) {
    let i = arr.length;
    while (i--) {
      if (arr[i]
        && arr[i].hasOwnProperty(attr)
        && (arguments.length > 2 && arr[i][attr] === value)) {

        arr.splice(i, 1);

      }
    }
    this.calculatetotalAmount();
    return arr;
  }

  public isValidNumber(value: number) {
    if (value != undefined && !isNaN(value)) {
      return true;
    }
    return false;
  }

  public isValidString(str: string): boolean {
    if (!(str != null && str != undefined && str.length > 0)) {
      return false;
    } else {
      return true;
    }
  }

  dateFormatter(dateToConvert: any) {
    return formatDate(dateToConvert, dateFormat, locale);
  }

  dateTimeFormatter(dateToConvert: any) {
    return formatDate(dateToConvert, dateTimeFormat, locale);
  }

}
