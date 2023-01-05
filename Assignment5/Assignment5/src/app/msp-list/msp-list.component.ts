import {Component, OnInit} from '@angular/core';
import {MspService} from "../services/msp.service";
import {MspEntry} from "../entries/msp.entry";
import {MatSlideToggleChange} from "@angular/material/slide-toggle";

@Component({
  selector: 'app-parliamentarians-overview',
  templateUrl: './msp-list.component.html',
  styleUrls: ['./msp-list.component.css']
})
export class MspListComponent implements OnInit {
  public isPreviousMSPsChecked: boolean = false;
  public mspEntries!: MspEntry[];

  constructor(private mspService: MspService) {
  }

  ngOnInit(): void {
    this.mspService.getMspEntries().subscribe(data => {
      let dataEntries: MspEntry[] = [];
      for (let entry of data) {
        if (entry.IsCurrent || this.isPreviousMSPsChecked) { // If the checkbox is checked, we want to show all MSPs
          dataEntries.push(entry);
        }
      }
      this.mspEntries = dataEntries;
      this.mspEntries.sort((a, b) => a.ParliamentaryName.localeCompare(b.ParliamentaryName));
    });
  }

  reload($event: MatSlideToggleChange) {
    this.isPreviousMSPsChecked = $event.checked;
    this.ngOnInit();
  }
}
