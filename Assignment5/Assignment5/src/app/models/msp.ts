import{MspEntry} from "../entries/msp.entry";

export class Msp {
  private _parliamentarianData: MspEntry;
  constructor(parliamentarianData: MspEntry) {
    this._parliamentarianData = parliamentarianData;
  }

  get parliamentarianData(): MspEntry {
    return this._parliamentarianData;
  }

  set parliamentarianData(value: MspEntry) {
    this._parliamentarianData = value;
  }
}
