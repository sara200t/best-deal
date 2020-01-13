import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CreateDealComponent} from "./components/create-deal/create-deal.component";

const routes: Routes = [
  { path: 'create-deal', component: CreateDealComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(
    routes,
    { enableTracing: true }
  )],
  exports: [RouterModule]
})
export class AppRoutingModule { }
