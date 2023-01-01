import{ParliamentarianEntry} from "../entries/parliamentarian.entry";

export class Parliamentarian {
  private _parliamentarianData: ParliamentarianEntry;
  constructor(parliamentarianData: ParliamentarianEntry) {
    this._parliamentarianData = parliamentarianData;
  }

  get parliamentarianData(): ParliamentarianEntry {
    return this._parliamentarianData;
  }

  set parliamentarianData(value: ParliamentarianEntry) {
    this._parliamentarianData = value;
  }
}
