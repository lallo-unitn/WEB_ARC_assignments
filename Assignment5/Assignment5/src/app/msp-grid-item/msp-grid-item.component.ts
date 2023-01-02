import {Component, Input} from '@angular/core';
import {MspEntry} from "../entries/msp.entry";
import {Router} from "@angular/router";

@Component({
  selector: 'app-parliamentarian-grid-item',
  templateUrl: './msp-grid-item.component.html',
  styleUrls: ['./msp-grid-item.component.css']
})
export class MspGridItemComponent {
  @Input() msp!: MspEntry;

  constructor(private router: Router) {
  }

  getImage(): string {
    if (this.msp.PhotoURL !== "" && this.msp.PhotoURL !== null) {
      return <string>this.msp.PhotoURL;
    } else if (this.msp.GenderTypeID === 2) {
      return "/assets/image/male.svg";
    } else {
      return "/assets/image/female.svg";
    }
  }

  onItemClick(): void {
    this.router.navigate(['/msp-details', this.msp.PersonID]);
    console.log("Redirected to msp " + this.msp.PersonID);
  }

}
