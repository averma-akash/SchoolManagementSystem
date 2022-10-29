import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ToastService } from 'src/app/_service/toast.service';
import { Subscription } from 'rxjs'

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.css'],
  host: {'[class.ngb-toasts]': 'true'}
})
export class ToastComponent implements OnInit, OnDestroy {

 toastTimeOut = 7000;
 subscription: Subscription = new Subscription;
 msg : any;

  constructor(private toastService: ToastService) { }

  ngOnInit(): void {
    this.msg = [];
    this.subscribeNotification();
  }
  subscribeNotification() {
    this.subscription = this.toastService.toastMessage
    .subscribe(notification => {
      notification ? this.msg.push(notification) : (this.msg = [])
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  onCancel() {
    this.toastService.remove(null);
  }

  @ViewChild('cancelToast', {static:false}) set element(element: ElementRef) {
    if(element) {
      element.nativeElement.focus();
    }
  }

  @ViewChild('descToast', {static:false}) set elementDesc(elementDesc: ElementRef) {
    if(elementDesc) {
      elementDesc.nativeElement.innerHTML =elementDesc.nativeElement.innerHTML.replace(/nbsp;/g,' ');
    }
  }
}
 