import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {UserService} from './../../services/user.service';
import {User} from './../../models/user.mode';
import {InvoiceMaster} from './../../models/invoice.mode';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class DashboardComponent implements OnInit {

  view: any[] = [650, 300];
  view2: any[] = [400, 200];
  customColorsArray= [];
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  receivablesList = [];
  currentUser : User;

  receivableCount = 0;
  payableCount =0;

  constructor(private userService : UserService,private router : Router,
    private toastrService : ToastrService) {


    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.userService.getPayables(this.currentUser.userName).subscribe(
      res =>{
        this.payableCount = 0;

        if(res.length > 0){
          for(let invMst of res){
            this.payableCount = this.payableCount + invMst.totalAmount;
          }
        }
         

        this.userService.getReceivables(this.currentUser.userName).subscribe(
          response =>{
            this.receivableCount = 0;
            if(response.length > 0){
              for(let invMstr of response){
                this.receivableCount = this.receivableCount + invMstr.totalAmount;
              }
            }
            this.receivablesList = [{
              "name": "Payables",
              "value":  this.payableCount
            },
            {
              "name": "Receivables",
              "value": this.receivableCount
            }];
          }, error => {
            this.toastrService.error('Error while getting Receivables');
          }
        );

      }, error => {
        this.toastrService.error('Error while getting Payables');
      }
    );

   }
   
  ngOnInit() {
    this.customColorsArray = [{
      "name": "Payables",
      "value":  "#a8385d"
    },
    {
      "name": "Receivables",
      "value": "#3E50B4"
    }]; //#6c757d","#4eb727 "
}

gotToPayables(){
  this.router.navigate(['/pages/payables']);
}

gotToReceivables(){
  this.router.navigate(['/pages/receivables']);
}

onSelect(event) {
 console.log('EV '+event.name);

 if(event.name == "Payables"){
  this.gotToPayables();
 }else if(event.name == "Receivables"){
  this.gotToReceivables();
 }

  
}

}
