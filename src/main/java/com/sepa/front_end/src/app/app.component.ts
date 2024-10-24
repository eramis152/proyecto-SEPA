import { transaction } from './../model/transaction';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import Swal from 'sweetalert2'
import { TransactionService } from './services/transaction.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  title = 'front_end';

  currentDateV1!: string;  // Variable para guardar la siguiente fecha
  currentDateV2!: string;
  transactionForm: transaction;

  constructor(
    private router: Router,
    private service: TransactionService
  ) {
    this.transactionForm = new transaction();
  }

  ngOnInit(): void {
    const fechaActual = new Date();

    const fechaV1 = this.convertirFecha(fechaActual);
    const hour = this.unirFechaHora(fechaActual);
    this.currentDateV1 = fechaV1;
    const joinDateHour = [fechaV1, hour.trim()];
    const fechaV2 = joinDateHour.join('T');
    this.currentDateV2 = fechaV2;

  }

  convertirFecha(fechaActual: Date): string{
      const dateString = new Date(fechaActual).toLocaleString();
      const split = dateString.split(',');
      const dateConv= split[0];
      const dateConvReserse = dateConv.split('/').reverse().join('/');

      return dateConvReserse;
  }

  unirFechaHora(fechaActual: Date): string{
    const dateString = new Date(fechaActual).toLocaleString();
    const split = dateString.split(',');
    const hour= split[1];


    return hour;
}


  onSubmit(_t6: NgForm) {
    this.transactionForm.dateV1 = this.currentDateV1;
    this.transactionForm.dateV2 = this.currentDateV2;

    if (_t6.valid) {
      console.log(this.transactionForm);
      this.service.emitTransaction(this.transactionForm).subscribe( response =>{
        console.log(response);
      })

      Swal.fire({
        icon: "success",
        title: "¡Transacción realizada!",
        showConfirmButton: false,
        timer: 1500
      });
      this.transactionForm = new transaction();
      _t6.reset();

    }
  }
}

