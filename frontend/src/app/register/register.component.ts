import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/user';
import { DataService } from '../data.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
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

  }

  goBack(): void {
    this.location.back();
  }

  registerUser(): void {
      this.dataService.registerUser(this.user)
        .subscribe(() => this.goBack());
    }

}
