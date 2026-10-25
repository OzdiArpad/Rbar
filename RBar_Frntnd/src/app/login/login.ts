import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';


@Component({
  imports: [CommonModule],
  selector: 'app-login',
  styleUrl: 'login.css',
  templateUrl: 'login.html',
  standalone: true,
})
export class login {
  constructor(
    private titleService: Title,
    private router: Router,
  ) {}

  ngOnInit() {
    this.titleService.setTitle('R Bár');
  }

  strLogIn: string = 'Bejelentkezés';

  BtnLoginGoogle() {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  }

  BtnLoginMicrosoft() {}
}
