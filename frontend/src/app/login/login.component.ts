import { Component, OnInit, Input  } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/user';
import { AuthenticationService } from '../authentication.service'
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
        private authenticationService: AuthenticationService,    
        private location: Location
        ) { }

    ngOnInit() {
        // reset login status
        

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

      loginUser(): void {
          this.authenticationService.login(this.user)
            .add(
                data => {
                    console.log("new log login");
                    this.router.navigate([this.returnUrl]);
                  //this.goPortfolio();
             });
            console.log('login user');
      }

}
