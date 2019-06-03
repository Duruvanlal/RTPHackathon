import { Component, OnInit, ViewChild, TemplateRef, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { YodleeService } from './../../services/yodlee.service';
import { UserService } from './../../services/user.service';
import { ToastrService } from 'ngx-toastr';
import { NgxSpinnerService } from 'ngx-spinner';
import { UserPmtAccount } from './../../models/user.mode';
import { User,UPAMaster } from './../../models/user.mode';
import {AccountBalance} from './../../models/invoice.mode';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal';

declare var jquery: any;
declare var $: any;
declare let window: any;
declare let document: any;


@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class AccountsComponent implements OnInit, AfterViewInit {

  @ViewChild('editpayment') public editpayment: TemplateRef<any>;
  @ViewChild('accountBalance') public accountBalance: TemplateRef<any>;
  @ViewChild('createUPAModal') createUPAModal: ModalDirective;
  @ViewChild('linkUPAModal') linkUPAModal: ModalDirective;
  @ViewChild('showAccBalanceModal') showAccBalanceModal: ModalDirective;
  

  yoddleToken = null;
  rows = [];
  temp = [];
  loadingIndicator: boolean = true;
  reorderable: boolean = true;
  currentUser: User;
  userPmtAccountList : UserPmtAccount[]=[];
  upaMaster : UPAMaster[] =[];
  selectedUserPmtAccount = new UserPmtAccount();
  selectedUPA : string;
  linkUPAId : any;
  selectedAccountBalance = new AccountBalance();
  columns = [
  ];

  constructor(private yodleeService: YodleeService, private router : Router,
    private spinner: NgxSpinnerService,
    private toastrService: ToastrService, private userService: UserService) {

    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.yoddleToken = localStorage.getItem('yoddleToken');

    this.userService.getUserAccounts(this.currentUser.userName).subscribe((res) => {
        this.userPmtAccountList = res;
        this.temp = [...res];
        this.rows = res;
    });

    this.userService.getUserUpa(this.currentUser.userName).subscribe(
      res => {
        this.upaMaster =res;
      },error=>{

      }
    );

  }

  linkAccounts() {
    this.spinner.show();
    this.yodleeService.getUserAccounts().subscribe(
      data => {

        let yodleeRows : any;
        yodleeRows = data.account;
        this.userPmtAct = [];
        for (let row of yodleeRows) {
          if (row.accountName == this.currentUser.userName) {
            let userPmtAccount = new UserPmtAccount();

            userPmtAccount.accountType = "DDA_Checking";
            userPmtAccount.shortName = row.accountName+" Checking";
            userPmtAccount.accountName = row.accountName;
            userPmtAccount.bankName = row.providerName;
            userPmtAccount.userId = row.accountName;
            userPmtAccount.accountNumber = row.accountNumber;
            if(this.currentUser.userName=="RonnieT"){
              userPmtAccount.accountNumber = "CIBTEAM100002";
            }else if(this.currentUser.userName=="AnthonyT"){
              userPmtAccount.accountNumber = "NYCTEAM100002";
            }
            userPmtAccount.routingNumber = '026009593';

            this.userPmtAct.push(userPmtAccount);
          }
        }

        this.userService.postUserAct(this.userPmtAct).subscribe(
          (res) => {
            this.toastrService.success('Accounts successfully Linked to Zillpay');
            this.userService.getUserAccounts(this.currentUser.userName).subscribe((res) => {
              this.userPmtAccountList = res;
              this.temp = [...res];
              this.rows = res;
          });
          }, (error) => {
            this.spinner.hide();
            this.toastrService.success('Accounts successfully Linked to Zillpay');
          }
        );

        this.spinner.hide();
      }, error => {
        this.spinner.hide();
        this.toastrService.error('Error in fetching the accounts');
      }
    );

  }

  updateFilter(event) {
    const val = event.target.value.toLowerCase();
    const temp = this.temp.filter(function (d) {

      return d.accountName.toLowerCase().indexOf(val) !== -1
        || d.accountType.toLowerCase().indexOf(val) !== -1
        || d.accountNumber.toLowerCase().indexOf(val) !== -1
        || !val;
    });
    this.rows = temp;
  }
  ngOnInit() {

    this.columns = [
      { name: 'Account Name', prop: 'shortName' },
      { name: 'Account Type', prop: 'accountType' },
      { name: 'Account Number', prop: 'accountNumber' },
      { name: 'Routing Number', prop: 'routingNumber' },
      { name: 'Account Balance', cellTemplate: this.accountBalance },
    ];
    this.columns.push({ name: 'Link UPA', cellTemplate: this.editpayment });

    this.loadingIndicator = false;
    document.getElementById("token").value = this.yoddleToken;

    (function (window) {
      //Open FastLink
      var fastlinkBtn = document.getElementById('btn-fastlink');
      fastlinkBtn.addEventListener('click', function () {
        // jQuery.get( "/token")
        // .done(function( data ) {
        //     var token = data.jwtUserToken;
        //  console.log('token '+token);
        var token = document.getElementById("token").value;
        window.fastlink.open({
          fastLinkURL: 'https://node.sandbox.yodlee.com/authenticate/restserver',
          jwtToken: token,
          params: '',
          onSuccess: function (data) {
            console.log(data);
          },
          onError: function (data) {
            console.log(data);
          },
          onExit: function (data) {
            console.log(data);
          },
          onEvent: function (data) {
            console.log(data);
          }
        }, 'container-fastlink');
        //       });

      }, false);

      jQuery("#closeFastlink").click(function () {
        //   var token = document.getElementById("token").value; 
      });

    }(window));
  }

  userPmtAct: UserPmtAccount[] = [];

  ngAfterViewInit() {

  }

  action(row){
    this.linkUPAModal.show();
    this.selectedUserPmtAccount = row;
    console.log('invoiceDetail '+JSON.stringify(this.selectedUserPmtAccount));
  }

  viewAcctBalance(row){
    this.spinner.show();
    this.userService.getAccountBalance(row.accountNumber).subscribe(
      res => {
        if(res){
          this.selectedAccountBalance = res;
          this.spinner.hide();
          this.showAccBalanceModal.show();
        }else{
          this.spinner.hide();
          this.toastrService.info('Account Balance info not found');
        }
      }, error => {
        this.spinner.hide();
        this.toastrService.error('Error while fetching Account Balance');
      }
    );
  }

  closeLinkUPA(){
    this.linkUPAModal.hide();
  }

  linkUPA(){
     this.selectedUserPmtAccount.upaCD = this.linkUPAId;
    this.spinner.show();
    this.userService.updateUserAct(this.selectedUserPmtAccount).subscribe(
      res=>{
        this.spinner.hide();
          this.toastrService.success("UPA linked successfully");
          this.userService.refresh();
      },error => {
        this.spinner.hide();
        this.toastrService.success("Error while linking UPA");
        this.createUPAModal.hide();
      }
    );
  }

  closeUpaModel(){
    this.selectedUPA = '';
    this.createUPAModal.hide();
  }
  createUPA(){
  
    if(this.isValidString(this.selectedUPA)){
      let upaMaster = new UPAMaster();
      upaMaster.upaCd = this.selectedUPA;
      upaMaster.userId = this.currentUser.userName;
      upaMaster.userUpaMstrId = this.currentUser.userName+"_"+this.selectedUPA;
      this.spinner.show();
      this.userService.postUpa(upaMaster).subscribe(
        res =>{
          this.spinner.hide();
          this.toastrService.success("UPA created successfully");
          this.userService.refresh();
        },error =>{
          this.spinner.hide();
          this.toastrService.error("Error while creating UPA");
        }
      );
    }else{
      this.toastrService.info('Please input UPA ID');
    }
        

  }
  newAccount(){
    this.router.navigate(['/pages/new-account']);
  }

  generateUPA(){
    this.createUPAModal.show();
  }
  public isValidString(str: string): boolean {
    if (!(str != null && str != undefined && str.length > 0)) {
      return false;
    } else {
      return true;
    }
  }
}
