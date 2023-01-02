import {Component, Input} from '@angular/core';
import {PartyEntry} from "../entries/party.entry";

@Component({
  selector: 'app-msp-parties-details',
  templateUrl: './msp-parties-details.component.html',
  styleUrls: ['./msp-parties-details.component.css']
})
export class MspPartiesDetailsComponent {

  @Input() partyEntry!: PartyEntry;

  constructor() {
  }

}
