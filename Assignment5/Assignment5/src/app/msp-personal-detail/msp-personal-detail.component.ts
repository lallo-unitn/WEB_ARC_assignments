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

  getMspImage(): string {
    if (this.mspEntry.PhotoURL !== "" && this.mspEntry.PhotoURL !== null) {
      return <string>this.mspEntry.PhotoURL;
    } else if (this.mspEntry.GenderTypeID === 2) {
      return "/assets/image/male.svg";
    } else {
      return "/assets/image/female.svg";
    }
  }

  getBirthDate(): string {
    return formatDate(this.mspEntry.BirthDate, 'MMM dd, yyyy', 'en-UK');
  }
}
