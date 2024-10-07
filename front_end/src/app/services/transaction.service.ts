import { transaction } from './../../model/transaction';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class TransactionService {


  private url: string =  'http://localhost:4200/transaction';  // URL base

  constructor(private http: HttpClient) { }

  emitTransaction(transaction: transaction): Observable<transaction>{

    return this.http.post<transaction>(this.url,transaction);

  }

}
