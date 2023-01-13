import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {MspEntry} from "../entries/msp.entry";
import {MspPartyEntry} from "../entries/msp.party.entry";
import {PartyEntry} from "../entries/party.entry";
import {WebsiteEntity} from "../entries/website.entity";

@Injectable({
  providedIn: 'root'
})
/**
 * @classdesc This class contains the service methods for the MSP application.
 */
export class MspService {

  constructor(private httpClient: HttpClient) {
  }

  /**
   * @returns The observable of the array of MSP entries
   */
  public getMspEntries(): Observable<MspEntry[]> {
    const url = 'https://data.parliament.scot/api/members';
    let mspEntryArray$: Observable<MspEntry[]>;
    mspEntryArray$ = this.httpClient.get<MspEntry[]>(url);
    return mspEntryArray$;
  }

  /**
   * @param {number} id  is the id of the MSP entry
   * @returns The observable of the MspEntry with requested id
   * @throws {Error} if id is not present
   */
  public getMspEntry(id: number): Observable<MspEntry> {
    const url = 'https://data.parliament.scot/api/members/' + id;
    let mspEntry$: Observable<MspEntry>;
    mspEntry$ = this.httpClient.get<MspEntry>(url);
    return mspEntry$.pipe(
      catchError(err => { // Handle errors, in our case useful for when the ID is not found
        console.log(err);
        throw err;
      })
    );
  }

  /**
   * @returns The observable of the array of MSP - party relation entries
   */
  public getMspPartyEntries(): Observable<MspPartyEntry[]> {
    const url = 'https://data.parliament.scot/api/memberparties';
    let mspPartyEntryArray$: Observable<MspPartyEntry[]>;
    mspPartyEntryArray$ = this.httpClient.get<MspPartyEntry[]>(url);
    return mspPartyEntryArray$;
  }

  /**
   * @returns The observable of the array of party entries
   */
  public getPartyEntries(): Observable<PartyEntry[]> {
    const url = 'https://data.parliament.scot/api/parties/';
    let partyEntries$: Observable<PartyEntry[]>;
    partyEntries$ = this.httpClient.get<PartyEntry[]>(url);
    return partyEntries$;
  }

  /**
   * @returns The observable of the array of website entries
   */
  public getWebsiteEntries(): Observable<WebsiteEntity[]> {
    const url = 'https://data.parliament.scot/api/websites/';
    let websiteEntries$: Observable<WebsiteEntity[]>;
    websiteEntries$ = this.httpClient.get<WebsiteEntity[]>(url);
    return websiteEntries$;
  }
}








