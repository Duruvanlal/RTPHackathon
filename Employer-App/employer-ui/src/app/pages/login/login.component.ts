import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, AbstractControl, FormBuilder, Validators} from '@angular/forms';
import { User } from 'app/models/user.mode';
import { UserService } from '../../services/user.service';
import { NgForm} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { GlobalProperty } from '../../../global';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class LoginComponent {
 
  public user:User;
  public showError:Boolean;

  constructor(private userService:UserService,
              private toastrService: ToastrService,
              private global : GlobalProperty,
              private router: Router) {
     this.user = new User();
  }

  public login(form: NgForm){
    if(form.valid){
      this.userService.login(this.user).subscribe((response)=>{
        if(response && response != null){
          localStorage.setItem('user', JSON.stringify(response));
          this.global.principal = JSON.stringify(response);
          // READ
          let item = JSON.parse(localStorage.getItem('user'));
          console.log("item==",item);
          this.router.navigate(['/pages']);
        }else{
          this.showError = true;
          setTimeout( ()=>{
            this.showError = false;
            }, 5000)
        }
      });
    }
  }


}

