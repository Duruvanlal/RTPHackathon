import { Component, OnInit, ViewChild, TemplateRef, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { YodleeService } from './../../services/yodlee.service';
import { UserService } from './../../services/user.service';
import { ToastrService } from 'ngx-toastr';
import { NgxSpinnerService } from 'ngx-spinner';
import { UserPmtAccount } from './../../models/user.mode';
//import {UserPmtAccount} from './../../models/user.mode';

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

  yoddleToken = null;
  rows = [];
  temp = [];
  loadingIndicator: boolean = true;
  reorderable: boolean = true;

  columns = [
  ];

  constructor(private yodleeService: YodleeService, private spinner: NgxSpinnerService, 
    private toastrService: ToastrService,private userService: UserService) {
    // this.yodleeService.getUserToken().subscribe(
    //   data => {
    //     console.log('jwtUserToken ' + data.jwtUserToken);
    //     this.jwtUserToken = data.jwtUserToken;
    //     document.getElementById("token").value = this.jwtUserToken;
    //   },
    //   error => {
    //     this.toastrService.error('Error in getting the token');
    //   }
    // );
    this.spinner.show();
    this.yoddleToken = localStorage.getItem('yoddleToken');
    this.yodleeService.getUserAccounts().subscribe(
      data => {

        this.temp = [...data.account];
        this.rows = data.account;

        for (let row of this.rows) {
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
    
        this.userService.postUserAct(this.userPmtAct).subscribe(
          (res) =>{
            this.toastrService.success('Accounts successfully Linked to Zillpay');
          },(error)=>{
           // this.toastrService.error('Error in linking accounts to Zillpay');
          }
        );

        this.spinner.hide();
        // this.toastrService.error('Error in fetching the accounts');
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
        || d.accountStatus.toLowerCase().indexOf(val) !== -1
        || !val;
    });
    this.rows = temp;
  }
  ngOnInit() {

    this.columns = [
      { name: 'Account Name', prop: 'accountName' },
      { name: 'Display Name', prop: 'displayedName' },
      { name: 'Account Type', prop: 'accountType' },
      { name: 'Account Number', prop: 'accountNumber' },
      { name: 'Account Status', prop: 'accountStatus' }
    ];
    this.columns.push({ name: 'Action', cellTemplate: this.editpayment });

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
}
