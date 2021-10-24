import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AlertComponent } from '../alert/alert.component';
import { ExceptionResponse } from '../_model/exception-response';

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

  showErrorResponseMessage(error: ExceptionResponse, type: MessageType) {
    this.dialog.open(AlertComponent, {
      data: { message: error.message + ' at ' + error.localDateTime + (error.stackTrace ? '<br>' + error.stackTrace.toLocaleString() : ''), type: type.toString() }
    });
  }
}

export enum MessageType { SUCCESS = "SUCCESS", ERROR = "ERROR", WARNING = "WARNING" }
