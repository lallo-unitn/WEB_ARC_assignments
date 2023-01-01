import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-parliamentarian-details',
  templateUrl: './msp-details.component.html',
  styleUrls: ['./msp-details.component.css']
})
export class MspDetailsComponent {

  constructor(private router: Router) {
  }

  onBackClick(): void {
    this.router.navigate(['/list']);
  }



}
