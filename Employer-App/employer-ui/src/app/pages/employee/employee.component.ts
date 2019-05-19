import { Component, OnInit, ViewEncapsulation,Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { Contractor } from '../../models/contractor.mode';
import { NgForm} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { GlobalProperty } from '../../../global';
import { User } from 'app/models/user.mode';
import { ContractorService } from '../../services/contractor.service';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [ContractorService,UserService]
})
export class EmployeeComponent implements OnInit {

  constructor(public dialog: MatDialog, 
              private contractorService:ContractorService,
              private userService:UserService,
              private global : GlobalProperty,) { }
  public principal:any;
  private contractorList = [];

  ngOnInit() { 
    this.principal = JSON.parse(this.global.principal);
    if(this.principal.company){
      this.getContractorList();
    }
  }



  private getContractorList(){
    this.contractorService.findAll(this.principal.company.companyId).subscribe((response)=>{
      console.log("List = ",response);
      this.getUserList(response);
    });
  }
  private getUserList(_contractorList){
    this.userService.findAllUsers(this.principal.company.id).subscribe((response)=>{
      for(let i in _contractorList){
        let _contractor = {
          firstName:undefined,
          lastName:undefined,
          startDate:_contractorList[i].startDate,
          endDate:_contractorList[i].endDate,
          email:undefined,
          phone:undefined,
          userName:undefined,
          userId:undefined,
          hourlyRate:_contractorList[i].hourlyRate
        }
        for(let j in response){
          if(response[j].userId == _contractorList[i].userId){
            _contractor.firstName = response[j].firstName;
            _contractor.lastName = response[j].lastName;
            _contractor.email = response[j].email;
            _contractor.phone = response[j].phone;
            _contractor.userName = response[j].userName;
            _contractor.userId = response[j].userId;
            break;
          }
        }
        this.contractorList.push(_contractor);
      }
    });
  }




  openDialog(): void {
    const dialogRef = this.dialog.open(AddNewEmployeeDialog, {
      width: '40%'
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.getContractorList();
      }
    });
  }


}


@Component({
  selector: 'register-customer-dialog',
  templateUrl: 'register-customer-dialog.html',
  providers: [ContractorService]
})
export class AddNewEmployeeDialog implements OnInit{

  public principal:any;
  public contractor = new Contractor();
  public user = new User();

  constructor(
              public dialogRef: MatDialogRef<AddNewEmployeeDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private toastrService: ToastrService,
              private global : GlobalProperty,
              private contractorService:ContractorService
        ) {}
    
    ngOnInit() {  
      this.principal = JSON.parse(this.global.principal);
      this.contractor.companyId = this.principal.company.companyId;
    }
  
    onNoClick(): void {
      this.dialogRef.close();
    }

    hireEmployee(form: NgForm){
      if(form.valid){
        console.log("yes");
        console.log("contractor = " , this.contractor);
        console.log("yes = ", this.user);
        this.contractorService.hire(this.contractor,this.user.firstName, this.user.lastName, this.user.userName).subscribe((response)=>{
          this.toastrService.success("Contractor Registered succesfully.");
          this.dialogRef.close(true);
        });
      }else{
        this.toastrService.error("Please Provide all required information.");
      }
    }

}
