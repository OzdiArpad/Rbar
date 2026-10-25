import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class home {
  constructor(
    private titleService: Title,
    private router: Router,
  ) {}

  strLogIn: string = 'Bejelentkezés';

  ngOnInit() {
    this.titleService.setTitle('R Bár');
  }
}
