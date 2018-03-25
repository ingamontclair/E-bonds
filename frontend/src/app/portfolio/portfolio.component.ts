import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Position } from '../model/position';
import { DataService } from '../data.service';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

positions: Position[];
balance: number;
accountId: number = 1;

  constructor(
      private route: ActivatedRoute,
      private router: Router,
      private dataService: DataService) { }

  ngOnInit() {
    this.dataService.getPortfolio()
    .subscribe(p => {
       this.positions = p;
      });
    console.log('position');


  this.dataService.getBalance(this.accountId)
     .subscribe(p => {
          console.log("----"+p);
          this.balance = p;
  });
  }

/*  export positions: Position[] = [
    { symbol: 'Apple', lastPrice: '600', curr:'USD', quantity:100 },
    { symbol: 'IBM', lastPrice: '500', curr:'USD', quantity:200 }

  ];*/
}
