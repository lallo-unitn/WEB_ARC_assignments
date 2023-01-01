import {Component, Input} from '@angular/core';
import {MspEntry} from "../entries/msp.entry";
import {Router} from "@angular/router";

@Component({
  selector: 'app-parliamentarian-grid-item',
  templateUrl: './msp-grid-item.component.html',
  styleUrls: ['./msp-grid-item.component.css']
})
export class MspGridItemComponent {
  @Input() parliamentarian!: MspEntry;

  constructor(private router: Router) {
  }

  getImage(): string {
    if (this.parliamentarian.PhotoURL !== "" && this.parliamentarian.PhotoURL !== null) {
      return <string>this.parliamentarian.PhotoURL;
    } else if (this.parliamentarian.GenderTypeID === 2) {
      return "/assets/image/male.svg";
    } else {
      return "/assets/image/female.svg";
    }
  }

  onItemClick(): void {
    this.router.navigate(['/parliamentarian-details']);
  }

}
