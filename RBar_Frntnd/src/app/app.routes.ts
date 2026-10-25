import { Routes } from '@angular/router';
import { home } from './home/home';
import { login } from './login/login';
import { products } from './products/products';

export const routes: Routes = [
  {path:'', component: home}
  ,{path:'login', component: login}
    ,{path:'products', component: products}
];
