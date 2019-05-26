import { Component, OnInit, ViewChild, TemplateRef, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { YodleeService } from './../../services/yodlee.service';
import { UserService } from './../../services/user.service';
import { ToastrService } from 'ngx-toastr';
import { NgxSpinnerService } from 'ngx-spinner';
import { UserPmtAccount } from './../../models/user.mode';
import { User } from './../../models/user.mode';
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
  @ViewChild('createUPAModal') createUPAModal: ModalDirective;


  yoddleToken = null;
  rows = [];
  temp = [];
  loadingIndicator: boolean = true;
  reorderable: boolean = true;
  currentUser: User;
  userPmtAccountList : UserPmtAccount[]=[];
  selectedUserPmtAccount = new UserPmtAccount();
  selectedUPA : string;
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

  }

  linkAccounts() {
    this.spinner.show();
    this.yodleeService.getUserAccounts().subscribe(
      data => {

        let yodleeRows : any;
        yodleeRows = data.account;

        for (let row of yodleeRows) {
          if (row.accountName == this.currentUser.userName) {
            let userPmtAccount = new UserPmtAccount();

            userPmtAccount.accountType = row.accountType;
            userPmtAccount.shortName = row.accountName;
            userPmtAccount.accountName = row.accountName;
            userPmtAccount.bankName = row.providerName;
            userPmtAccount.userId = row.accountName;
            userPmtAccount.accountNumber = row.accountNumber;
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
         //   this.toastrService.error('Error in linking the accounts');
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
      { name: 'Account Name', prop: 'accountName' },
      { name: 'Short Name', prop: 'shortName' },
      { name: 'Account Type', prop: 'accountType' },
      { name: 'Account Number', prop: 'accountNumber' },
      { name: 'Routing Number', prop: 'routingNumber' },
    ];
    this.columns.push({ name: 'UPA', cellTemplate: this.editpayment });

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
    this.createUPAModal.show();
    this.selectedUserPmtAccount = row;
    console.log('invoiceDetail '+JSON.stringify(this.selectedUserPmtAccount));
  }
  createUPA(){
    this.selectedUserPmtAccount.upaCD = this.selectedUPA;
    this.userService.updateUserAct(this.selectedUserPmtAccount).subscribe(
      res=>{
          this.toastrService.success("UPA created and added");
      },error => {
        this.toastrService.success("Error while creating UPA");
      }
    );
  }
  newAccount(){
    this.router.navigate(['/pages/new-account']);
  }
  public isValidString(str: string): boolean {
    if (!(str != null && str != undefined && str.length > 0)) {
      return false;
    } else {
      return true;
    }
  }
}
