import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Deal} from "./deal.interface";

@Injectable({
  providedIn: 'root'
})
export class DealService {

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Deal[]> {
    return this.httpClient.get<Deal[]>("api/best-deals");
  }
}
