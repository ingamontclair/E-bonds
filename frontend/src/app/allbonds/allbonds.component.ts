import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Position } from '../model/position';
import { DataService } from '../data.service';


@Component({
  selector: 'app-allbonds',
  templateUrl: './allbonds.component.html',
  styleUrls: ['./allbonds.component.css']
})
export class AllbondsComponent implements OnInit {
positions: Position[];
balance: number;
accountId: number  = 1;

  constructor(
        private route: ActivatedRoute,
        private router: Router,
        private dataService: DataService
  ) { }

  ngOnInit() {

    this.dataService.getAllBonds()
    .subscribe(p => {
       this.positions = p;
      });
    console.log('position');

    this.dataService.getBalance(this.accountId)
       .subscribe(p => {
            this.balance = p;
    });
  }

}
