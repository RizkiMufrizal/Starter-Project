import { Constants } from './../constants';
import { RedirectService } from './redirect.service';
import { LocalStorageService, LocalStorage } from 'ngx-webstorage';
import { Injectable, Inject } from '@angular/core';

@Injectable()
export class SessionManagerService {

  constructor(
    @Inject(RedirectService) private redirectService: RedirectService,
    private localStorageService: LocalStorageService
  ) { }

  @LocalStorage('access_token')
  private accessToken

  getAccessToken(): string {
    return this.accessToken
  }

  isAccessToken(): boolean {
    return this.accessToken != null ? true : false
  }

  saveAccessToken(accessToken: string) {
    this.localStorageService.store('access_token', accessToken);
  }

  clear() {
    this.localStorageService.clear();
  }

  logout() {
    this.clear();
    this.redirectService.redirectNotAuthorization();
  }

  handleUnAuthorizedUser(err) {
    if (err.status == Constants.HTTP_UNAUTHORIZED || err.status == Constants.HTTP_FORBIDDEN) {
      this.redirectService.redirectNotAuthorization();
      this.clear();
    }
  }
}
