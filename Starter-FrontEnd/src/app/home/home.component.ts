import { SessionManagerService } from './../helpers/session-manager.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [SessionManagerService]
})
export class HomeComponent {

  constructor(
    private sessionManagerService: SessionManagerService
  ) { }

  logout() {
    this.sessionManagerService.logout()
  }
}
