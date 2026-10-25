import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import { Title } from '@angular/platform-browser';

@Component({
  selector:"app-home",
  standalone: true,
  imports:[CommonModule],
  templateUrl:"./home.html",
  styleUrl:"./home.css",
})
export class home {
  constructor(private titleService: Title) {}

  ngOnInit(){
    this.titleService.setTitle("Kijárat Bár")
  }
}
