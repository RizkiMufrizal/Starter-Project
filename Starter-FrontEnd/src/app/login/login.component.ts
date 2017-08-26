import { Token } from './../models/token';
import { LoginService } from './login.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionManagerService } from '../helpers/session-manager.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService, SessionManagerService]
})
export class LoginComponent implements OnInit {

  private loginFormGroup: FormGroup

  constructor(
    private loginService: LoginService,
    private sessionManagerService: SessionManagerService,
    private loginForm: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.createForm()
  }

  createForm() {
    this.loginFormGroup = this.loginForm.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  login(f: FormGroup) {
    if (!f.invalid) {
      this.loginService.login(f.value.username, f.value.password)
        .subscribe((data: Token) => {
          this.sessionManagerService.saveAccessToken(data.access_token)
          this.router.navigate(['/home']);
        }, error => {
          console.log('error')
        });
    }
  }

}
