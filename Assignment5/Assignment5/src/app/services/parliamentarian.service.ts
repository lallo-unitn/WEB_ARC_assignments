import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ParliamentarianEntry} from "../entries/parliamentarian.entry";

@Injectable({
  providedIn: 'root'
})
export class ParliamentarianService {

  constructor(private httpClient: HttpClient) {
  }

  public getParliamentarians(): Observable<ParliamentarianEntry[]> {
    const url = 'https://data.parliament.scot/api/members';
    let parliamentarianEntryArray$: Observable<ParliamentarianEntry[]>;
    parliamentarianEntryArray$ =  this.httpClient.get<ParliamentarianEntry[]>(url);
    return parliamentarianEntryArray$;
  }
}
