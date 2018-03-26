import { Injectable } from '@angular/core';

import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';
import { User } from './model/user';
import { Position } from './model/position';
import { Bond } from './model/bond';
import { TradeOrder } from './model/tradeOrder';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class DataService {
accountId: number;
user: User;
  result: Response;

  constructor(
    private _http: Http,
    private authService: AuthenticationService
  ) { }

  getUsers() {
    return this._http.get("/api/users")
      .map(result => this.result = result.json().data);
  }

  registerUser(user: User){
    return this._http.post("/api/users/register", user).pipe(
      //tap((user: User) => this.log(`added user w/ id=${user.id}`)),
      //catchError(this.handleError<User>('registerUser'))
    );
  }
  loginUser(user: User){
         return this._http.get("/api/users/"+user.eMail+"/"+user.pass)
          .map(result =>
            this.result = result.json().data);
                 //return result;
   }
  
  
  
  
   getPortfolio(){
          this.user = this.authService.currentUser();
          this.accountId = this.user.accountId;
           return this._http.get("/api/bonds/positions/"+ this.accountId)
            .map(result => this.result = result.json().data);
                   //return result;
  }

  getAllBonds(){
             return this._http.get("/api/bonds/positions")
              .map(result => this.result = result.json().data);
                     //return result;
  }

  getBondInfo(bondId: number){
             return this._http.get("/api/bonds/"+bondId)
              .map(result => this.result = result.json().data);
                     //return result;
  }

  getBondById(bondId: number){
             return this._http.get("/api/bonds/positions/"+ this.authService.currentUser().accountId + "/"+bondId)
              .map(result => this.result = result.json().data);
                       //return result;
  }

  sellBond(tradeOrder: TradeOrder){
    tradeOrder.accountId = this.authService.currentUser().accountId;  
      return this._http.post("/api/bonds/sellbonds", tradeOrder).pipe(
        //tap((user: User) => this.log(`added user w/ id=${user.id}`)),
        //catchError(this.handleError<User>('registerUser'))
      );
  }
  buyBond(tradeOrder: TradeOrder){
      tradeOrder.accountId = this.authService.currentUser().accountId;  
        return this._http.post("/api/bonds/buybonds", tradeOrder).pipe(
          //tap((user: User) => this.log(`added user w/ id=${user.id}`)),
          //catchError(this.handleError<User>('registerUser'))
        );
  }

  getBalance(accountId: number){
       return this._http.get("/api/bonds/balance/" + this.authService.currentUser().accountId)
           .map(result => this.result = result.json());
                         //return result;
  }

  getHistory(accountId: number){
    return this._http.get("/api/bonds/history/" + this.authService.currentUser().accountId)
        .map(result => this.result = result.json().data);
                      //return result;
}
}
