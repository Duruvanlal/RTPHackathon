import { Routes, RouterModule, PreloadAllModules  } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { AccountsComponent } from './pages/accounts/accounts.component';
import { PagesComponent } from './pages/pages.component';
import { InvoicesComponent } from './pages/invoices/invoices.component';
import { NewInvoiceComponent } from './pages/new-Invoice/new.invoice.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { NewCustomerComponent } from './pages/add-new-customer/add.new.customer.component'; 
import {PayablesComponent} from './pages/payables/payables.component';
import {ReceivablesComponent} from './pages/receivables/receivables.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import {NewAccountComponent} from './pages/new-account/new-account.component';
import {CustomerComponent} from './pages/customer/customer.component';


export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'register', loadChildren: 'app/pages/register/register.module#RegisterModule' },
  { path: 'login', loadChildren: 'app/pages/login/login.module#LoginModule' },
  { 
    path: 'pages', 
    component: PagesComponent,
    children:[
          { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
          { path: 'customers', component:  CustomerComponent},
          { path: 'accounts', component:  AccountsComponent},
          { path: 'new-account', component:  NewAccountComponent},
          { path: 'invoices', component: InvoicesComponent},
          { path: 'new/invoice', component:  NewInvoiceComponent},
          { path: 'payables', component:  PayablesComponent},
          { path: 'receivables', component:  ReceivablesComponent},
          { path: 'customers', component:  CustomersComponent},
          { path: 'new/customer', component:  NewCustomerComponent},
          { path: 'dashboard', component:  DashboardComponent},
      ]
  },
  
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules,
    useHash: true
});