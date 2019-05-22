import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, AbstractControl, FormBuilder, Validators} from '@angular/forms';
import { User,Address,Company } from '../../models/user.mode';
import { NgForm} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UserService } from '../../services/user.service';




@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class RegisterComponent {
   
    public user = new User();
    public addressSameAsCompany:Boolean;
    constructor(private toastrService: ToastrService,
      private router: Router,
      private userService: UserService){
      this.addressSameAsCompany = false;
    }

    public registerEntity(form: NgForm){
      console.log('JSON '+JSON.stringify(this.user));
    
      if(form.valid){
        this.userService.registerUser(this.user).subscribe((response)=>{
          this.toastrService.success("Entity succesfully registered.");
          this.router.navigate(['/login']);
        });
      }else{
        this.toastrService.error("Please provide all required information.");
      }

    }

   
}
