import {Component, Input} from '@angular/core';
import {MspEntry} from "../entries/msp.entry";
import {Router} from "@angular/router";

@Component({
  selector: 'app-msp-grid-item',
  templateUrl: './msp-grid-item.component.html',
  styleUrls: ['./msp-grid-item.component.css']
})
export class MspGridItemComponent {
  @Input() mspEntry!: MspEntry;

  constructor(private router: Router) {
  }

  /**
   * This method is used to get the PhotoURL of the MSP.
   * If the PhotoURL is null, it returns a default image: the gender of the avatar is determined by
   * attribute GenderTypeId of the {@link mspEntry} given in component input.
   */
  getImage(): string {
    if (
      this.mspEntry.PhotoURL !== "" &&
      this.mspEntry.PhotoURL !== null &&
      this.mspEntry.PhotoURL !== undefined) {
      return <string>this.mspEntry.PhotoURL;
    } else if (this.mspEntry.GenderTypeID === 2) {
      return "assets/image/male.png";
    } else {
      return "assets/image/female.png";
    }
  }

  onItemClick(): void {
    this.router.navigate(['/msp-details', this.mspEntry.PersonID]);
    console.log("Redirected to msp " + this.mspEntry.PersonID);
  }

}
