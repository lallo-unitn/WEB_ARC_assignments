import {Component, OnInit} from '@angular/core';
import {MspService} from "../services/msp.service";
import {MspEntry} from "../entries/msp.entry";

@Component({
  selector: 'app-parliamentarians-overview',
  templateUrl: './msp-list.component.html',
  styleUrls: ['./msp-list.component.css']
})
export class MspListComponent implements OnInit {

  public parliamentarians!: MspEntry[];

  constructor(private parliamentarianService: MspService) {
  }

  ngOnInit(): void {
    this.parliamentarianService.getParliamentarians().subscribe(data => {
      this.parliamentarians = data;
    });
  }

}
