import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {productsDatas} from '../Types/productsDatas';
import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {Router} from "@angular/router";

@Component({
  imports: [CommonModule],
  selector: 'app-products',
  styleUrl: 'products.css',
  templateUrl: 'products.html',
  standalone: true,
})
export class products {
  strLogIn: string = 'Bejelentkezés';

  productList: productsDatas[] = [];
  chosedList: productsDatas[] = [];
  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef,
    private titleService: Title,
    private router: Router,
  ) {}

  ngOnInit() {
    this.titleService.setTitle('R Bár');
    const raw = sessionStorage.getItem('ProductList');

    if (raw === null) {
      this.http
        .get<productsDatas[]>('http://localhost:8080/Product/GetAll', { withCredentials: true })
        .subscribe({
          next: (datas) => {
            this.productList = datas;
            this.chosedList = datas;
            console.log(this.productList);
            sessionStorage.setItem('ProductList', JSON.stringify(this.productList));
            this.cdr.detectChanges();
          },
        });
    } else {
      this.productList = JSON.parse(raw) as productsDatas[];
      this.cdr.detectChanges();
    }
  }

  choseAlcohol() {
    const raw = sessionStorage.getItem('ProductList');
    if (raw != null) {
      const allProduct: any[] = JSON.parse(raw);
      this.chosedList = allProduct.filter((p) => p.productType === 'Alkohol');
      this.cdr.detectChanges();
    }
  }

  choseAll() {
    const raw = sessionStorage.getItem('ProductList');
    if (raw !== null) {
      const allProduct: any[] = JSON.parse(raw);
      this.chosedList = allProduct.filter((p) => p.productType);
      this.cdr.detectChanges();
    }
  }
}
