import {Component, Input} from '@angular/core';
import {WebsiteEntity} from "../entries/website.entity";

@Component({
  selector: 'app-msp-website-details',
  templateUrl: './msp-website-details.component.html',
  styleUrls: ['./msp-website-details.component.css']
})
export class MspWebsiteDetailsComponent {

  @Input() mspWebsite!: WebsiteEntity;

  constructor() {
  }

}
