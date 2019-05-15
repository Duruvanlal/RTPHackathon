import { Component, ViewEncapsulation, OnInit,HostListener } from '@angular/core';
import { AppSettings } from './app.settings';
import { Settings } from './app.settings.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent  implements OnInit {
    constructor(public appSettings:AppSettings, public router:Router){
        
    }   

    ngOnInit() {        
             
  }

}
