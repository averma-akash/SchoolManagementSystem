import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, } from '@angular/router';
import { TokenStorageService } from './JWTTokenStorage/token-storage.service'
@Injectable({
  providedIn: 'root'
})
export class RouterService implements CanActivate {

  constructor(
    private tokenService : TokenStorageService,
    private router : Router
  ) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean  {
    if(this.tokenService.isLoggedIn())
    return true;
    this.router.navigate(['login']);
    return false;
  }
}
