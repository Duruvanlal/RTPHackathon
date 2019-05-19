import { Menu } from './menu.model';

/*export const verticalMenuItems = [ 
   
]*/

export const verticalMenuItems = [ 
    new Menu (4, 'Accounts', '/pages/accounts', null, 'bank', null, false, 0),
    new Menu (1, 'Contractor', '/pages/employee', null, 'bank', null, false, 0),
    new Menu (2, 'Timesheets', null, null, 'bank', null, true, 0),
    new Menu (3, 'View Timesheet', '/pages/customers', null, 'bank', null, false, 2),
    
    
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