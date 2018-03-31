import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../authentication.service'
import { Cash } from '../model/cash';
import { DataService } from '../data.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {
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

withdraw(): void {
   this.dataService.withdraw(this.cash)
      .subscribe(() => this.goBack());
}

}
