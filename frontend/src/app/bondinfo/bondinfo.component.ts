import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Bond } from '../model/bond';
import { DataService } from '../data.service';

@Component({
  selector: 'app-bondinfo',
  templateUrl: './bondinfo.component.html',
  styleUrls: ['./bondinfo.component.css']
})
export class BondinfoComponent implements OnInit {
bondId: number;
bonds: Bond[];
  constructor(
        private route: ActivatedRoute,
        private router: Router,
        private dataService: DataService
  ) { }

  ngOnInit() {
      this.route.params.subscribe(params => {
         this.bondId = +params['id']
      });

      this.dataService.getBondInfo(this.bondId)
      .subscribe(b => {
         this.bonds = b;
        });
      console.log('bonds');
  }

}
