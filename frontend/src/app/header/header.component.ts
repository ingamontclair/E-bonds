import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/user';
import { AuthenticationService } from '../authentication.service'

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})


export class HeaderComponent implements OnInit {
user: User[];

  constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService
  ) { }

  ngOnInit() {
    
  }

  logout() {
    console.log("logout");
    localStorage.removeItem("currentUser");
    this.router.navigate(['login']);
  }

  get currentUser() : User {
    return this.authenticationService.currentUser();
  }
}
