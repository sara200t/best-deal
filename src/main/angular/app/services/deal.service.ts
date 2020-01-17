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

  save(data: Deal): Observable<Deal> {
    let jsonData = JSON.stringify(data);
    console.log('Deal: ' + jsonData);
    return this.httpClient.post<Deal>("api/best-deals", jsonData);
  }

  findAll(): Observable<Deal[]> {
    return this.httpClient.get<Deal[]>("api/best-deals");
  }
}
