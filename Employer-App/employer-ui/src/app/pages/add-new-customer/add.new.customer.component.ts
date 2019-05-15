import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Customer } from '../../models/customer.mode';


@Component({
  selector: 'app-add-new-customer',
  templateUrl: './add.new.customer.component.html',
  styleUrls: ['./add.new.customer.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class NewCustomerComponent implements OnInit {

  public customer = new Customer();

  constructor() { }

  ngOnInit() {

  }



}
