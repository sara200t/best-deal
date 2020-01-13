import { Component, OnInit } from '@angular/core';
import {DealService} from "../../services/deal.service";

@Component({
  selector: 'app-create-deal',
  templateUrl: './create-deal.component.html',
  styleUrls: ['./create-deal.component.scss']
})
export class CreateDealComponent implements OnInit {

  constructor(private dealService: DealService) { }

  ngOnInit() {
  }

}
