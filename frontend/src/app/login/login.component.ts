import { Component, OnInit, Input  } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/user';
import { DataService } from '../data.service';
import { Location } from '@angular/common';
import { HeaderComponent } from '../header/header.component'

@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html'
})


export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;
    returnUrl: string;
    @Input() user: User = {} as User;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private dataService: DataService,
        private location: Location,
        private header: HeaderComponent
        ) { }

    ngOnInit() {
        // reset login status


        // get return url from route parameters or default to '/'

    }
      goPortfolio(): void {
        this.location.go("./portfolio");
      }

      loginUser(): void {
          this.dataService.loginUser(this.user)
            .subscribe(user => {
                this.verifyUser(user);
            });
          console.log('login user');
      }

      verifyUser(users: User[]): void {
      if (users.length > 0 ) {
        localStorage.setItem("user", JSON.stringify(users[0]));
        this.header.login();
        console.log(users[0].eMail);
        console.log('login successful');
        this.router.navigateByUrl('portfolio');
       }
        else {
          console.log('invalid credentials');
        }
      }
}
