import { ErrorHandler, Injectable, Injector, NgZone } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";

@Injectable({
    providedIn: 'root'
})
class GlobalErrorHandler implements ErrorHandler {

constructor(
    private injector : Injector,
    public snackBar: MatSnackBar,
    private zone: NgZone) {}

    handleError(error: Error): void {
        let message;
        message = error.message ? error.message :error.toString();
        this.zone.run(() => {
            // The second parameter is the text in the button. 
            // In the third, we send in the css class for the snack bar.
            this.snackBar.open(message, 'X', {panelClass: ['error']});
          });
    }

} 