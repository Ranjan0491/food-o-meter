import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AlertComponent } from '../alert/alert.component';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor(public dialog: MatDialog) { }

  showMessage(message: string, type: MessageType) {
    this.dialog.open(AlertComponent, {
      data: { message: message, type: type.toString() }
    });
  }
}

export enum MessageType { SUCCESS, ERROR, WARNING }
