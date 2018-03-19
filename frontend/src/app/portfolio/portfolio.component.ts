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
  }

/*  export positions: Position[] = [
    { symbol: 'Apple', lastPrice: '600', curr:'USD', quantity:100 },
    { symbol: 'IBM', lastPrice: '500', curr:'USD', quantity:200 }

  ];*/
}
