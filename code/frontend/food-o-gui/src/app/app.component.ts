import { Component } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { environment } from 'src/environments/environment';
import { UserServiceService } from './_service/user-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'food-o-gui';

  constructor(private router: Router, private userService: UserServiceService) {
    let subscription = router.events.subscribe((event) => {
      if (event instanceof NavigationStart) {
        // Checks if the browser has been refreshed
        if (!router.navigated) {
          if (sessionStorage.getItem(environment.sessionUser.id) !== null) {
            this.userService.sendLoginEvent(environment.loginEvent.loggedIn);
          }
        }
      }
    });
  }
}
