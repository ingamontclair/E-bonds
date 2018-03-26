import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Bond } from '../model/bond';
import { DataService } from '../data.service';
import { Position } from '../model/position';
import { TradeOrder } from '../model/tradeOrder';
import { Location } from '@angular/common';


@Component({
  selector: 'app-buybond',
  templateUrl: './buybond.component.html',
  styleUrls: ['./buybond.component.css']
})
export class BuybondComponent implements OnInit {
  bondId: number;
  positions: Position[];
  bonds: Bond[];
  @Input() tradeOrder: TradeOrder = {} as TradeOrder;
  constructor(
            private route: ActivatedRoute,
            private router: Router,
            private dataService: DataService,
            private location: Location
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.bondId = +params['id'];
      this.tradeOrder.bondId = this.bondId;
      this.tradeOrder.direction = "buy";

   });
        this.dataService.getBondInfo(this.bondId)
        .subscribe(b => {
           this.bonds = b;
          });
        console.log('bonds');

  }
  goBack(): void {
    this.location.back();
  }
buyBond(): void {
   this.dataService.buyBond(this.tradeOrder)
      .subscribe(() => this.router.navigate(["portfolio"]));
}

}
