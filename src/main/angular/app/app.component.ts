import {Component} from '@angular/core';
import {DealService} from "./services/deal.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  deals$ = this.dealService.findAll();

  constructor(private dealService: DealService) {
  }

}
