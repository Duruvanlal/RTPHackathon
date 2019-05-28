import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {UserService} from './../../services/user.service';
import {User} from './../../models/user.mode';
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

  view: any[] = [750, 400];
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
        this.payableCount = res.length;

        this.userService.getReceivables(this.currentUser.userName).subscribe(
          res =>{
            this.receivableCount = res.length;
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
