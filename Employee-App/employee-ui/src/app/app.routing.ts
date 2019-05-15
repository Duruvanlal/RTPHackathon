import { Routes, RouterModule, PreloadAllModules  } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { AccountsComponent } from './pages/accounts/accounts.component';
import { PagesComponent } from './pages/pages.component';
import { InvoicesComponent } from './pages/invoices/invoices.component';
import { NewInvoiceComponent } from './pages/new-Invoice/new.invoice.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { NewCustomerComponent } from './pages/add-new-customer/add.new.customer.component'; 


export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'register', loadChildren: 'app/pages/register/register.module#RegisterModule' },
  { path: 'login', loadChildren: 'app/pages/login/login.module#LoginModule' },
  { 
    path: 'pages', 
    component: PagesComponent,
    children:[
          { path: '', redirectTo: 'invoices', pathMatch: 'full' },
          { path: 'accounts', component:  AccountsComponent},
          { path: 'invoices', component: InvoicesComponent},
          { path: 'send/invoice', component:  NewInvoiceComponent},
          { path: 'customers', component:  CustomersComponent},
          { path: 'new/customer', component:  NewCustomerComponent}
      ]
  },
  
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules,
    useHash: true
});