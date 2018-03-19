import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/user';
import { Injectable } from '@angular/core';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

@Injectable()
export class HeaderComponent implements OnInit {
user: User;

  constructor(
        private route: ActivatedRoute,
        private router: Router
  ) { }

  ngOnInit() {

  }

  login(){
       this.user = JSON.parse(localStorage.getItem("user"))
       console.log("ngOnInit");
       console.log(this.user);
       if ((localStorage.getItem("user") === null) && (this.router.url !== 'registration') && (this.router.url !== 'login')){
          this.router.navigate(['login']);
       }
  }

  logout() {
    console.log("logout");
    localStorage.removeItem("user");
    this.router.navigate(['login']);
  }
}
