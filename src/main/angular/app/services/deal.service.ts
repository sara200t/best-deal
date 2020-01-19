import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Deal} from "./deal.interface";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class DealService {

  constructor(private httpClient: HttpClient) {
  }

  // private handleError(error: HttpErrorResponse) {
  //   console.log(error)
  //   if (error.error instanceof ErrorEvent) {
  //     // A client-side or network error occurred. Handle it accordingly.
  //     console.error('An error occurred:', error.error.message);
  //   } else {
  //     // The backend returned an unsuccessful response code.
  //     // The response body may contain clues as to what went wrong,
  //     console.error(
  //         `Backend returned code ${error.status}, ` +
  //         `body was: ${error.error}`);
  //   }
  //   // return an observable with a user-facing error message
  //   return throwError(
  //       'Something bad happened; please try again later.');
  // };

  create(deal: Deal): Observable<Deal> {
    let jsonData = JSON.stringify(deal);
    console.log('Creating Deal: ' + jsonData);
    return this.httpClient.post<Deal>("api/best-deals", deal);
  }

  findAll(): Observable<Deal[]> {
    return this.httpClient.get<Deal[]>("api/best-deals");
  }
}
