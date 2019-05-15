import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routing } from './app.routing';
import { AppSettings } from './app.settings';
import { AppComponent } from './app.component';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};
import { MatSliderModule,MatFormFieldModule,MatSlideToggleModule,MatIconModule,MatDatepickerModule,MatNativeDateModule, MatInputModule,MatTabsModule,MatSelectModule,MatRadioModule,MatTableModule,MatPaginatorModule,MatAutocompleteModule } from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HeaderComponent } from './theme/components/header/header.component';
import { FooterComponent } from './theme/components/footer/footer.component';
import { SidebarComponent } from './theme/components/sidebar/sidebar.component';
import { VerticalMenuComponent } from './theme/components/menu/vertical-menu/vertical-menu.component';
import { HorizontalMenuComponent } from './theme/components/menu/horizontal-menu/horizontal-menu.component';
import { BackTopComponent } from './theme/components/back-top/back-top.component';
import { UserMenuComponent } from './theme/components/user-menu/user-menu.component';
import { BlankComponent } from './pages/blank/blank.component';
import { AccountsComponent } from './pages/accounts/accounts.component';
import { PagesComponent } from './pages/pages.component';
import { InvoicesComponent } from './pages/invoices/invoices.component';
import { NewInvoiceComponent } from './pages/new-Invoice/new.invoice.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { NewCustomerComponent } from './pages/add-new-customer/add.new.customer.component'; 
import 'hammerjs';
import { CommonModule } from '@angular/common'
import { HttpClientModule } from '@angular/common/http';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { GlobalProperty } from '../global';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    VerticalMenuComponent,
    HorizontalMenuComponent,
    BackTopComponent,
    UserMenuComponent,
    BlankComponent,
    AccountsComponent,
    PagesComponent,
    InvoicesComponent,
    NewInvoiceComponent,
    CustomersComponent,
    NewCustomerComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    routing,
    BrowserAnimationsModule,
    MatSliderModule,
    MatSlideToggleModule,
    ToastrModule.forRoot(), 
    MatIconModule,
    MatInputModule,
    MatTabsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatRadioModule,
    MatTableModule ,
    MatPaginatorModule,
    MatAutocompleteModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [ AppSettings,
    {
      provide: PERFECT_SCROLLBAR_CONFIG,
      useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
    }, GlobalProperty ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
