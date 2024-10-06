import { transaction } from './../model/transaction';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

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

  constructor(){
    this.transactionForm = new transaction;
  }

  onSubmit(_t6: NgForm) {

    if(this.transactionForm.name == undefined || this.transactionForm.lastName == undefined || this.transactionForm.iban == undefined || this.transactionForm.fechaCad == undefined || this.transactionForm.cvv == undefined){
      alert("Rellena todos los campos");
    }else{
      console.log(this.transactionForm);
    }

    }
}
