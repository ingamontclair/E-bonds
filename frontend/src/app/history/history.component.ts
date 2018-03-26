import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { History } from '../model/history'
import { DataService } from '../data.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
histories: History[];
accountId: number;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private dataService: DataService

  ) { }

  ngOnInit() {
    this.dataService.getHistory(this.accountId)
    .subscribe(p => {
       this.histories = p;
       console.log(this.histories);
      });
      
  }

}
