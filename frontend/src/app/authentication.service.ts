import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import { Http, Headers, RequestOptions } from '@angular/http';

import { User } from './model/user';

@Injectable()
export class AuthenticationService {
loggedInUser: User;
users: User[];
result: Response;

    constructor(
   
    private _http: Http

    ) { }


    /*
             return this._http.get("/api/users/"+user.eMail+"/"+user.pass)
          .map(result =>
            this.result = result.json().data);

            */

    login(submittedUser: User) {
                  return this._http.get("/api/users/"+submittedUser.eMail+"/"+submittedUser.pass)
      .map(result => 
        this.result = result.json().data)
      .subscribe(u => {
             this.loggedInUser = u;
             console.log("inside auth service inside login");
             console.log(this.loggedInUser);
             if (this.loggedInUser !== null) {

             // store user details and jwt token in local storage to keep user logged in between page refreshes
               localStorage.setItem('currentUser', JSON.stringify(this.loggedInUser));
             }
             return u;
    });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }

    public currentUser() {
        this.users   = JSON.parse(localStorage.getItem("currentUser"));
        if ((this.users !== null) && (this.users.length > 0)){
          return this.users[0];   
        }
        return null;
      }
}
