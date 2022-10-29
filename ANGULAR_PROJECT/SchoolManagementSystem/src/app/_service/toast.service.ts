import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  toastMessage : Subject<Object> = new Subject<Object>();
  constructor() { }

  show(option: Object) {
    this.toastMessage.next(option);
  }

  remove(toast) {
    this.toastMessage.next(toast);
  }
}
