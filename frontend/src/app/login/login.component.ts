import { Component, OnInit, Input  } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/user';
import { DataService } from '../data.service';
import { Location } from '@angular/common';

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
        private location: Location
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
        console.log('inside verify');
        console.log(users);
        console.log('************');



        if (users.length > 0 ) {
          console.log(users[0].eMail);
          console.log('login successful');
          this.router.navigateByUrl('portfolio');
        }
        else {
          console.log('invalid credentials');
        }
      }
}
