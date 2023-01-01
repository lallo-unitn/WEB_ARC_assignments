import {Component, Input} from '@angular/core';
import {ParliamentarianEntry} from "../entries/parliamentarian.entry";

@Component({
  selector: 'app-parliamentarian-grid-item',
  templateUrl: './parliamentarian-grid-item.component.html',
  styleUrls: ['./parliamentarian-grid-item.component.css']
})
export class ParliamentarianGridItemComponent {
  @Input() parliamentarian!: ParliamentarianEntry;

  getImage(): string {
    if(this.parliamentarian.PhotoURL !== "" && this.parliamentarian.PhotoURL !== null){
      return <string>this.parliamentarian.PhotoURL;
    }
    else if (this.parliamentarian.GenderTypeID === 2) {
      return "/assets/image/male.svg";
    } else {
      return "/assets/image/female.svg";
    }
  }

}
