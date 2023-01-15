import { Component, OnInit } from '@angular/core';
import { filter, map, Observable, of, reduce, tap } from 'rxjs';

@Component({
  selector: 'app-sample-example',
  templateUrl: './sample-example.component.html',
  styleUrls: ['./sample-example.component.css']
})
export class SampleExampleComponent implements OnInit {

  ObservableVarable$: any; //Declared an Observable, best practice to use $ Sign with observable variable
  ObservableUserArray: any;
  subscribedData: any;

  constructor() { }

  ngOnInit(): void {
    this.ObservableUserArray = ['Akash', 'Ankit', 'Prabha', 'Anjali']; // Simple array with some value
    //We will use this array to create an observable
    //Two ways to create observable 
    // 1st : Using RxJS "of" operator
    // 2nd : Using new Observable

    //1st approach to get an observable
    this.ObservableVarable$ = of(this.ObservableUserArray);
    console.log("1st approac Observable" + this.ObservableVarable$);

    this.ObservableVarable$.toPromise().then(response => {
      console.log("to promise " + response)
    }).catch(error => {
      console.log("Error")
    });

    //2nd Approach
    new Observable(observer => {
      setTimeout(() => {
        observer.next("starting")
      }, 2000);
      setTimeout(() => {
        observer.next("started")
      }, 4000);
      setTimeout(() => {
        observer.next("completed")
      }, 6000);
    })
      // Till here we have created a new observable but it will not impact anything
      // as we dind't subscribed it yet.
      // There are 3 ways we can subscribe to any observable
      // -> Subscribe
      // -> toPromise
      // -> pipe
      .subscribe({
        next: data => {
          this.subscribedData = data;
          console.log("Subscribed Data" + data)
        },
        error: err => {
          console.log("Error");
        }
      })

  }

  getPipeWithFilter() {
    let arr1$ = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    let filterData = arr1$.pipe(
      filter(x => x % 2 === 0),
      reduce((acc, one) => acc + one, 0)
    )
    filterData.subscribe(x => console.log(x));

    let testMap = arr1$.pipe(
      map(val => { return val * 2}
    )).subscribe(val => { console.log(val)});
    

    let testTap = arr1$.pipe(
      tap(val => {
        console.log("Tap " + val);
      }),
      map(val => { return val * 2}
    )).subscribe(val => { console.log(val)});
  }

}
