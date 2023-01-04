import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {catchError, EMPTY, map, mergeMap, Observable, Subscription, switchMap} from "rxjs";
import {MspEntry} from "../entries/msp.entry";
import {MspService} from "../services/msp.service";
import {MspPartyEntry} from "../entries/msp.party.entry";
import {PartyEntry} from "../entries/party.entry";
import {WebsiteEntity} from "../entries/website.entity";

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
  public mspWebsites: WebsiteEntity[] = [];
  private paramSub: Subscription | undefined;
  private mspEntrySub: Subscription | undefined;
  private mspPartyEntriesSub: Subscription | undefined;
  private partyEntriesSub: Subscription | undefined;
  private mspWebsiteSub: Subscription | undefined;
  private params!: Params;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private mspService: MspService
  ) {
  }

  ngOnInit(): void {
    try {
      this.getInfo();
    }catch (e: any) {
      console.log("zsfdxgchvjbknlòjblhvgcjfxhdzgsxhfcjln");
      this.router.navigate(['/list']);
    }
  }

  ngOnDestroy(): void {
    this.paramSub?.unsubscribe();
    this.mspEntrySub?.unsubscribe();
    this.mspPartyEntriesSub?.unsubscribe();
    this.partyEntriesSub?.unsubscribe();
    this.mspWebsiteSub?.unsubscribe();
  }

  private getInfo(): void {
    this.route.params.pipe(
      // mergeMap is like switchMap, but it waits for the previous observable to complete before starting the next one
      mergeMap((val: Params) => {
        this.params = val;
        this.personID = +val['personID']; // (+) converts string 'id' to a number
        return this.mspService.getMspEntry(this.personID);
      }),
      catchError((err: any) => { // if error caught in Observable<MspEntry>
        this.router.navigate(['/list']); // redirect to list
        return EMPTY; // return empty Observable
      }),
      mergeMap((val: MspEntry) => {
        this.mspEntry = val;
        return this.mspService.getMspPartyEntries();
      }),
      mergeMap((val: MspPartyEntry[]) => {
        for (let mspPartyEntry of val) { // get the IDs of the parties that the MSP is a member of
          if (mspPartyEntry.PersonID === this.mspEntry.PersonID) {
            this.mspPartyEntries.push(mspPartyEntry);
          }
        }
        return this.mspService.getPartyEntries();
      }),
      switchMap((val: PartyEntry[]) => {
        for (let partyEntry of val) {  //get the parties that the MSP is a member of
          if (
            this.mspPartyEntries.find(mspPartyEntry => mspPartyEntry.PartyID === partyEntry.ID) && // if the party is one that the MSP is a member of
            !this.partyEntries.find(somePartyEntry => somePartyEntry.ID === partyEntry.ID) // don't add duplicates
          ) {
            this.partyEntries.push(partyEntry);
          }
        }
        return this.mspService.getWebsiteEntries();
      }),
      map((val: WebsiteEntity[]) => {
        for (let websiteEntry of val) {
          if (websiteEntry.PersonID === this.mspEntry.PersonID) { // Find the website entries that match the personID
            this.mspWebsites.push(websiteEntry);
          }
        }
      })
    ).subscribe(() => {
    });
  }

  onBackClick(): void {
    this.router.navigate(['/list']);
  }

}
