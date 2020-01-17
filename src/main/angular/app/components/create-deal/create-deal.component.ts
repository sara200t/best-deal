import { Component, OnInit } from '@angular/core';
import {DealService} from "../../services/deal.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-create-deal',
  templateUrl: './create-deal.component.html',
  styleUrls: ['./create-deal.component.scss']
})
export class CreateDealComponent implements OnInit {
  item = new FormControl('')
  walmartPrice = new FormControl('')
  costcoPrice = new FormControl('')

  constructor(private dealService: DealService) { }

  ngOnInit() {
  }

  onSubmit() {

  }
}
