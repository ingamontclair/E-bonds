import { Component, OnInit, Input  } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Bond } from '../model/bond';
import { DataService } from '../data.service';
import { Position } from '../model/position';
import { TradeOrder } from '../model/tradeOrder';
import { Location } from '@angular/common';

@Component({
  selector: 'app-sellbond',
  templateUrl: './sellbond.component.html',
  styleUrls: ['./sellbond.component.css']
})
export class SellbondComponent implements OnInit {
  bondId: number;
  positions: Position[];
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
           this.tradeOrder.direction = "sell";

        });

        this.dataService.getBondById(this.bondId)
        .subscribe(p => {
           this.positions = p;
          });
        console.log('bonds');


  }
  goBack(): void {
      this.location.back();
  }
  
  sellBond(): void {
     this.dataService.sellBond(this.tradeOrder)
        .subscribe(() => this.goBack());
  }
}
