import { Injectable } from '@angular/core';

import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';
import { User } from './model/user';
import { Position } from './model/position';
@Injectable()
export class DataService {

  result:Response;

  constructor(private _http: Http) { }

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
          .map(result => this.result = result.json().data);
                 //return result;
   }
  getPortfolio(){
           return this._http.get("/api/bonds/positions/1")
            .map(result => this.result = result.json().data);
                   //return result;
  }
}
