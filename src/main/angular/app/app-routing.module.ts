import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CreateDealComponent} from "./components/create-deal/create-deal.component";
import {DealsComponent} from "./components/deals/deals.component";

const routes: Routes = [
  { path: 'create-deal', component: CreateDealComponent },
  { path: '**', component: DealsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(
    routes,
    { enableTracing: true }
  )],
  exports: [RouterModule]
})
export class AppRoutingModule { }
