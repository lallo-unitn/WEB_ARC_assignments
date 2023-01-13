import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Assignment5';

  constructor(private activatedRoute: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
    const path = this.activatedRoute.snapshot.queryParams['path'];
    console.log('path ->', path);
    const navigateTo = '/' + path;

    if (path) {
      this.router.navigate([navigateTo]);
    }
  }
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/
