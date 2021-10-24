import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationComponent } from '../confirmation/confirmation.component';

@Injectable({
  providedIn: 'root'
})
export class ConfirmationService {

  constructor(public dialog: MatDialog) { }

  showMessage(message: string) {
    return this.dialog.open(ConfirmationComponent, {
      data: message
    });
  }
}
