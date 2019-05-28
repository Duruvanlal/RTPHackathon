import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, AbstractControl, FormBuilder, Validators} from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { User,Address,Entity } from '../../models/user.mode';
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
      private router: Router,  private spinner: NgxSpinnerService,
      private userService: UserService){
      this.addressSameAsCompany = false;
    }

    public registerEntity(form: NgForm){    
      if(form.valid){
        if(this.addressSameAsCompany){
         this.user.address = this.user.entity.entityAddress;
        }
        console.log('User  '+JSON.stringify(this.user));
        this.spinner.show();
        this.userService.registerUser(this.user).subscribe((response)=>{
          this.toastrService.success("Entity succesfully registered.");
          this.spinner.hide();
          this.router.navigate(['/login']);
        },error=>{
          this.spinner.hide();
        });

      }else{
        this.toastrService.error("Please provide all required information.");
      }

    }

   
}
