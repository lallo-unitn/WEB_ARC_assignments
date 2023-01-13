import {Component, Input} from '@angular/core';
import {MspEntry} from "../entries/msp.entry";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-msp-personal-detail',
  templateUrl: './msp-personal-detail.component.html',
  styleUrls: ['./msp-personal-detail.component.css']
})
export class MspPersonalDetailComponent {

  @Input() mspEntry!: MspEntry;

  constructor() {
  }
  /**
   * This method is used to get the PhotoURL of the MSP.
   * If the PhotoURL is null, it returns a default image: the gender of the avatar is determined by
   * attribute GenderTypeId of the {@link mspEntry} given in component input.
   */
  getMspImage(): string {
    if (this.mspEntry.PhotoURL !== "" && this.mspEntry.PhotoURL !== null) {
      return <string>this.mspEntry.PhotoURL;
    } else if (this.mspEntry.GenderTypeID === 2) {
      return "assets/image/male.png";
    } else {
      return "assets/image/female.png";
    }
  }

  getBirthDate(): string {
    return formatDate(this.mspEntry.BirthDate, 'MMM dd, yyyy', 'en-UK');
  }
}
