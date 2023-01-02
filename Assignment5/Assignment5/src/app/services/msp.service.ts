import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MspEntry} from "../entries/msp.entry";

@Injectable({
  providedIn: 'root'
})
export class MspService {

  constructor(private httpClient: HttpClient) {
  }

  public getMspEntries(): Observable<MspEntry[]> {
    const url = 'https://data.parliament.scot/api/members';
    let parliamentarianEntryArray$: Observable<MspEntry[]>;
    parliamentarianEntryArray$ =  this.httpClient.get<MspEntry[]>(url);
    return parliamentarianEntryArray$;
  }

  public getMspEntry(id: number): Observable<MspEntry> {
    const url = 'https://data.parliament.scot/api/members/' + id;
    let parliamentarianEntry$: Observable<MspEntry>;
    parliamentarianEntry$ =  this.httpClient.get<MspEntry>(url);
    return parliamentarianEntry$;
  }

}
