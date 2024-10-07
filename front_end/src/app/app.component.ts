import { transaction } from './../model/transaction';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  title = 'front_end';

  transactionForm: transaction;

  constructor( private router: Router){
    this.transactionForm = new transaction;
  }

  onSubmit(_t6: NgForm) {

    if(_t6.valid){

      Swal.fire({
        icon: "success",
        title: "¡Transaccion realizada!",
        showConfirmButton: false,
        timer: 1500
      });
      
      console.log(this.transactionForm);
      this.transactionForm = new transaction;
      _t6.reset();
    }

    }
}
