import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {MspEntry} from "../entries/msp.entry";
import {MspService} from "../services/msp.service";
import {MspPartyEntry} from "../entries/msp.party.entry";
import {PartyEntry} from "../entries/party.entry";

@Component({
  selector: 'app-parliamentarian-details',
  templateUrl: './msp-details.component.html',
  styleUrls: ['./msp-details.component.css']
})
export class MspDetailsComponent implements OnInit, OnDestroy {
  public personID!: number;
  public mspEntry!: MspEntry;
  public mspPartyEntries: MspPartyEntry[] = [];
  public partyEntries: PartyEntry[] = [];
  private paramSub: Subscription | undefined;
  private mspEntrySub: Subscription | undefined;
  private mspPartyEntriesSub: Subscription | undefined;
  private partyEntriesSub: Subscription | undefined;

  constructor(private router: Router, private route: ActivatedRoute, private mspService: MspService) {
  }

  ngOnInit(): void {
    this.paramSub = this.route.params.subscribe(val => {
      this.personID = +val['personID']; // (+) converts string 'id' to a number
    });
    this.mspEntrySub = this.mspService.getMspEntry(this.personID).subscribe(data => {
      this.mspEntry = data;
      if (!data) {
        this.router.navigate(['/']); // If the data is not found, redirect to the home page
      }
    });
    this.mspPartyEntriesSub = this.mspService.getMspPartyEntries().subscribe(data => {
      for (let entry of data) {
        if (entry.PersonID === this.mspEntry.PersonID) { // Find the party entry that matches the person ID
          this.mspPartyEntries.push(entry);
        }
      }
      this.partyEntriesSub = this.mspService.getPartyEntries().subscribe(data => {
        for (let entry of data) {
          if (  // Find the party entry that matches the party ID and is not already in the list
            this.mspPartyEntries.find(x => x.PartyID === entry.ID) &&
            !this.partyEntries.find(x => x.ID === entry.ID)
          ) {
            this.partyEntries.push(entry);
          }
        }
      });
    });
  }

  ngOnDestroy(): void {
    this.paramSub?.unsubscribe();
    this.mspEntrySub?.unsubscribe();
    this.mspPartyEntriesSub?.unsubscribe();
    this.partyEntriesSub?.unsubscribe();
  }

  onBackClick(): void {
    this.router.navigate(['/list']);
  }

}
