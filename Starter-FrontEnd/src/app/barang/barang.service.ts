import { Paging } from './../models/paging';
import { environment } from './../../environments/environment';
import { Token } from './../models/token';
import { Injectable, Inject } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'
import { SessionManagerService } from '../helpers/session-manager.service';

@Injectable()
export class BarangService {

    headerAuth: Headers
    requestOptAuth: RequestOptions
    body: string

    constructor(
        private http: Http,
        @Inject(SessionManagerService) private sessionManagerService: SessionManagerService
    ) {
        this.headerAuth = new Headers({
            'Authorization': `Bearer ${this.sessionManagerService.getAccessToken()}`,
            'Content-Type': 'application/json'
        });
        this.requestOptAuth = new RequestOptions({
            headers: this.headerAuth
        });
    }

    getBarangs(): Observable<Paging> {
        return this.http.get(`/api/barangs`, this.requestOptAuth)
            .map((response: Response) => {
                return response.json();
            })
            .catch((err) => {
                return Observable.throw(err)
            })
    }

}
