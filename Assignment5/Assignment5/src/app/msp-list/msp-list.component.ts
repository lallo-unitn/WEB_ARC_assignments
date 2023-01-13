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

  /**
   * Fill the mspEntries array with the MSP entries. Depending on the value of {@link isPreviousMSPsChecked},
   * the MSP entries are filtered to only contain current MSPs or all MSPs.
   */
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

  /**
   * @description This method is called when the checkbox is toggled. It updates the value of {@link isPreviousMSPsChecked} and
   * @param $event The event that is triggered when the checkbox is toggled
   */
  reload($event: MatSlideToggleChange) {
    this.isPreviousMSPsChecked = $event.checked;
    this.ngOnInit();
  }
}
