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

  currentDate!: string;  // Variable para guardar la siguiente fecha
  transactionForm: transaction;

  constructor(
    private router: Router,
    private service: TransactionService
  ) {
    this.transactionForm = new transaction();
  }

  ngOnInit(): void {
    const fechaActual = new Date();

    // Guardar la siguiente fecha en la variable nextDate
    this.currentDate = this.formatDate(this.addDays(fechaActual, 1));

    console.log('Fecha Actual:', this.formatDate(fechaActual));

  }

  // Función para agregar días a una fecha
  addDays(date: Date, days: number): Date {
    const result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
  }

  // Función para formatear la fecha en DD/MM/YYYY
  formatDate(date: Date): string {
    const day = ('0' + date.getDate()).slice(-2);
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
  }

  onSubmit(_t6: NgForm) {
    this.transactionForm.date = this.currentDate;

    if (_t6.valid) {

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

