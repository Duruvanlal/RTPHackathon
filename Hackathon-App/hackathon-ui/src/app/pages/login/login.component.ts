import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, AbstractControl, FormBuilder, Validators} from '@angular/forms';
import { User } from 'app/models/user.mode';
import { UserService } from '../../services/user.service';
import { NgForm} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { GlobalProperty } from '../../../global';
import { NgxSpinnerService } from 'ngx-spinner';



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
              private toastrService: ToastrService,private spinner: NgxSpinnerService,
              private global : GlobalProperty,
              private router: Router) {
     this.user = new User();
  }

  public login(form: NgForm){
    if(form.valid){
      this.spinner.show();
      this.userService.login(this.user).subscribe((response)=>{
        if(response && response != null){
          localStorage.setItem('user', JSON.stringify(response));
          this.global.principal = JSON.stringify(response);
          // READ
          let item = JSON.parse(localStorage.getItem('user'));
          console.log("item==",item);
          this.router.navigate(['/pages']);
        }
        this.spinner.hide();
      },error=>{this.spinner.hide();});
    }

    this.userService.getYodleeToken().subscribe(
      (res)=>{
        localStorage.setItem('yoddleToken', res.jwtToken);
      },(error) =>{
        localStorage.setItem('yoddleToken', null);
      }
    );
  }


}

