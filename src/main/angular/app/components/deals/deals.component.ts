import { Component, OnInit } from '@angular/core';
import {DealService} from "../../services/deal.service";

@Component({
  selector: 'app-deals',
  templateUrl: './deals.component.html',
  styleUrls: ['./deals.component.scss']
})
export class DealsComponent implements OnInit {

  deals$ = this.dealService.findAll();

  constructor(private dealService: DealService) {
  }

  ngOnInit() {
  }

}
