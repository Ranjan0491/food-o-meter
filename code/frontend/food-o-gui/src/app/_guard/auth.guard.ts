import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserServiceService } from '../_service/user-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private userService: UserServiceService,
    private router: Router) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log("session user id: " + sessionStorage.getItem(environment.sessionUser.id));
    let isAuthenticated = sessionStorage.getItem(environment.sessionUser.id) !== null;
    let isAuthorized = false;
    if (isAuthenticated) {
      let urlSuffix = route.url[0].toString();
      console.log("urlSuffix: " + urlSuffix);
      let loggedInUserRole = sessionStorage.getItem(environment.sessionUser.role);
      if (urlSuffix === 'customer' && loggedInUserRole === environment.customerRole) {
        isAuthorized = true;
      } else if (urlSuffix === 'admin' && loggedInUserRole === environment.adminRole) {
        isAuthorized = true;
      } else if (urlSuffix === 'staff' && (loggedInUserRole === environment.chefRole || loggedInUserRole === environment.deliveryAgentRole)) {
        isAuthorized = true;
      }
    }
    if (!(isAuthenticated && isAuthorized)) {
      sessionStorage.clear();
      this.userService.sendLoginEvent('logout');
      this.router.navigate(['unauthorized']);
    }

    console.log("IsAuthenticated? " + isAuthenticated + ", isAuthorized? " + isAuthorized);

    return isAuthenticated && isAuthorized;
  }

}
