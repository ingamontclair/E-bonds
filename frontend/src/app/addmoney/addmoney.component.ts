import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../authentication.service'
import { Cash } from '../model/cash';
import { DataService } from '../data.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-addmoney',
  templateUrl: './addmoney.component.html',
  styleUrls: ['./addmoney.component.css']
})
export class AddmoneyComponent implements OnInit {
  @Input() cash: Cash = {} as Cash;
  constructor(
          private route: ActivatedRoute,
          private router: Router,
          private authenticationService: AuthenticationService,
          private dataService: DataService,
          private location: Location
  ) { }

  ngOnInit() {

  }

goBack(): void {
    this.location.back();
}

addMoney(): void {
   this.dataService.addMoney(this.cash)
      .subscribe(() => this.goBack());
}

}
