import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { UserPmtAccount } from './../../models/user.mode';
import { NgForm} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UserService } from '../../services/user.service';
import { User } from './../../models/user.mode';

@Component({
  selector: 'app-new-account',
  templateUrl: './new-account.component.html',
  styleUrls: ['./new-account.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class NewAccountComponent implements OnInit {

  userPmtAccount = new UserPmtAccount();
  userPmtAccountList : UserPmtAccount[] = [];
  currentUser: User;
  constructor(private toastrService : ToastrService,private userService: UserService) { 
    this.currentUser = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {
    
  }

  registerAccount(form: NgForm){
    if(form.valid){
        console.log('Form: '+JSON.stringify(this.userPmtAccount));
        this.userPmtAccount.userId = this.currentUser.userName;
        this.userPmtAccountList.push(this.userPmtAccount);
        this.userService.postUserAct(this.userPmtAccountList).subscribe(
          res =>{
            this.toastrService.success('Account Registration done');
          },error =>{
            this.toastrService.info('Error while saving');
          }
        );

    }else{
      this.toastrService.info('Please fill all the fields');
    }
  }
}
