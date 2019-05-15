import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './register.component';
//import { ToastrModule } from 'ngx-toastr';
import { MatSliderModule,MatCheckboxModule,MatFormFieldModule,MatSlideToggleModule,MatIconModule,MatDatepickerModule,MatNativeDateModule, MatInputModule,MatTabsModule,MatSelectModule,MatRadioModule,MatTableModule,MatPaginatorModule,MatAutocompleteModule } from '@angular/material';

 


export const routes = [
  { path: '', component: RegisterComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatCheckboxModule,
    MatFormFieldModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    RegisterComponent
  ]
})
export class RegisterModule { }
