import {Component, Input} from '@angular/core';
import {MspEntry} from "../entries/msp.entry";
import {Router} from "@angular/router";

@Component({
  selector: 'app-parliamentarian-grid-item',
  templateUrl: './msp-grid-item.component.html',
  styleUrls: ['./msp-grid-item.component.css']
})
export class MspGridItemComponent {
  @Input() mspEntry!: MspEntry;

  constructor(private router: Router) {
  }

  getImage(): string {
    if (this.mspEntry.PhotoURL !== "" && this.mspEntry.PhotoURL !== null) {
      return <string>this.mspEntry.PhotoURL;
    } else if (this.mspEntry.GenderTypeID === 2) {
      return "/assets/image/male.svg";
    } else {
      return "/assets/image/female.svg";
    }
  }

  onItemClick(): void {
    this.router.navigate(['/msp-details', this.mspEntry.PersonID]);
    console.log("Redirected to msp " + this.mspEntry.PersonID);
  }

}
