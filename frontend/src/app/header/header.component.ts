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
    this.user = JSON.parse(localStorage.getItem("currentUser"));
    if ((this.user !== null) && (this.user.length > 0)){
      return this.user[0];   
    }
    return null;
  }
}
