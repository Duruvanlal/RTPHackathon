import { Menu } from './menu.model';

/*export const verticalMenuItems = [ 
   
]*/

export const verticalMenuItems = [ 
    new Menu (4, 'Accounts', '/pages/accounts', null, 'bank', null, false, 0),
  //  new Menu (1, 'Invoice', '/pages/invoices', null, 'bank', null, false, 0),
    new Menu (5, 'Invoice', null, null, 'file-text-o', null, true, 0) ,
    new Menu (6, 'New Invoice', '/pages/new/invoice', null, 'plus-circle', null, false, 5),  
    new Menu (7, 'Payables', '/pages/payables', null, 'credit-card', null, false, 5),  
    new Menu (8, 'Receivables', '/pages/receivables', null, 'credit-card', null, false, 5),  
   /* new Menu (2, 'Enter Timesheet', '/pages/invoices', null, 'bank', null, false, 1),
    new Menu (3, 'View Timesheet', '/pages/customers', null, 'bank', null, false, 1),*/
    
    
]

export const horizontalMenuItems = [ 
    new Menu (1, 'Dashboard', '/pages/dashboard', null, 'tachometer', null, false, 0),
    new Menu (40, 'Pages', null, null, 'file-text-o', null, true, 0),
    new Menu (43, 'Login', '/login', null, 'sign-in', null, false, 0),    
    new Menu (44, 'Register', '/register', null, 'registered', null, false, 0),
    new Menu (45, 'Blank', '/pages/blank', null, 'file-o', null, false, 40),
    new Menu (46, 'Error', '/pagenotfound', null, 'exclamation-circle', null, false, 40),
    new Menu (200, 'External Link', null, 'http://themeseason.com', 'external-link', '_blank', false, 0)
]