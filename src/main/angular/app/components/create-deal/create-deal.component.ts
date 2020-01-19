import { Component, OnInit } from '@angular/core';
import {DealService} from "../../services/deal.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Deal} from "../../services/deal.interface";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-deal',
  templateUrl: './create-deal.component.html',
  styleUrls: ['./create-deal.component.scss']
})
export class CreateDealComponent implements OnInit {

  form: FormGroup
  deal: Deal
  result: Deal
  sub: Subscription


  constructor(private dealService: DealService, private fb: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.form = this.fb.group({
      item: '',
      walmartPrice: '',
      costcoPrice: ''
    })
  }

  onSubmit() {
    this.deal = this.form.value as Deal
    console.log(this.deal);

    this.sub = this.dealService.create(this.deal).subscribe(response => {
      this.result = response
      console.log(this.result)
      this.sub.unsubscribe()
      this.router.navigate(['/']).then(r => {});
    })
  }

}
