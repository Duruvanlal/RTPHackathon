import { Component, OnInit, ViewChild, TemplateRef, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { YodleeService } from './../../services/yodlee.service';
import { ToastrService } from 'ngx-toastr';
import { NgxSpinnerService } from 'ngx-spinner';

declare var jquery: any;
declare var $: any;
declare let window: any;
declare let document: any;


@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AccountsComponent implements OnInit, AfterViewInit {

  @ViewChild('editpayment') public editpayment: TemplateRef<any>;

  jwtUserToken = null;
  rows = [];
  temp = [];
  loadingIndicator: boolean = true;
  reorderable: boolean = true;

  columns = [
  ];

  constructor(private yodleeService: YodleeService, private spinner: NgxSpinnerService, private toastrService: ToastrService) {
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
    this.yodleeService.getUserAccounts().subscribe(
      data =>{
        console.log('Data ' + JSON.stringify(data));
        this.temp = [...data.account];
        this.rows =  data.account;
        this.spinner.hide();
       // this.toastrService.error('Error in fetching the accounts');
      },error =>{
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
      { name: 'Account Name',prop:'accountName' },
      { name: 'Display Name',prop:'displayedName'  },
      { name: 'Account Type' ,prop:'accountType' },
      { name: 'Account Number',prop:'accountNumber'  },
      { name: 'Account Status' ,prop:'accountStatus' }
    ];
    this.columns.push({ name: 'Action', cellTemplate: this.editpayment });

    this.rows = [
    ];
    this.loadingIndicator = false;

    (function (window) {
      //Open FastLink
      var fastlinkBtn = document.getElementById('btn-fastlink');
      fastlinkBtn.addEventListener('click', function () {
        // jQuery.get( "/token")
        // .done(function( data ) {
           //     var token = data.jwtUserToken;
              //  console.log('token '+token);
                window.fastlink.open({
                  fastLinkURL: 'https://node.sandbox.yodlee.com/authenticate/restserver',
                  jwtToken: 'Bearer eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIwMDk4YmVmMC01NjQxZjBmOC00Y2NmLTRmYTItYmU1Mi00ODY3ZmI0NzQ2ZmMiLCJpYXQiOjE1NTgzNTg1NTEsImV4cCI6MTU1ODM2MDM1MSwic3ViIjoic2JNZW1xcU1obFREaUtweVpwMSJ9.dg-x4zkuwRq9K53DqE8RBCDBVXrqUkE3vho2o8ies2XqWOcaHTonlMHr7hNTAym3wUn5sGxZ5KFP6XU5x-a9RfCr4xARBY5P3tSdt6wW5HAjwhLqkKQBjO79IQ_oKJI0Ay0-9Fjg-re-WSTM65XUvm_Wy4AL9kLcY6LxHeBHLwqBE0E2wPc94Avs9UanjsBIYAzcd819E3CwMFqo2YUApSbkE4oQ-Ara-kjlAkOXGYTPpRoFHOvgJ7gBNt5wyuCrA6yyt_fbX0XunKx7GLonng4_5DQPJdaJNjwPQG0V-_pizrDMTyIk2jUaQSUI5xX2403NZmg5oCZyHSAcC721dQ',
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

      jQuery("#closeFastlink").click(function() {
       //   var token = document.getElementById("token").value; 
      });

    }(window));
  }

  ngAfterViewInit() {

   
  }
}
