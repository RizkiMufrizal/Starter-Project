import { environment } from './../../environments/environment';
import { Token } from './../models/token';
import { Injectable, Inject } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'
import { SessionManagerService } from '../helpers/session-manager.service';

@Injectable()
export class LoginService {

    headerAuth: Headers
    requestOptAuth: RequestOptions
    basic: string
    body: string

    constructor(
        private http: Http,
        @Inject(SessionManagerService) private sessionManagerService: SessionManagerService
    ) {
        this.basic = `${environment.clientId}:${environment.clientSecret}`
        this.headerAuth = new Headers({
            'Authorization': `Basic ${btoa(this.basic)}`,
            'Content-Type': 'application/x-www-form-urlencoded'
        });
        this.requestOptAuth = new RequestOptions({
            headers: this.headerAuth
        })
    }

    login(username: string, password: string): Observable<Token> {
        this.body = `grant_type=${environment.grantType}&client_secret=${environment.clientSecret}&client_id=${environment.clientId}&username=${username}&password=${password}`
        return this.http.post(`/oauth/token`, this.body, this.requestOptAuth)
            .map((response: Response) => {
                return response.json();
            })
            .catch((err) => {
                return Observable.throw(err)
            })
    }
}
