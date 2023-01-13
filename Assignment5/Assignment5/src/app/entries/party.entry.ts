/**
 * Interface that represent a party of the parliament.
 *
 * @interface PartyEntry
 */
export interface PartyEntry {
  readonly ID: number;
  readonly Abbreviation: string;
  readonly ActualName: string;
  readonly PreferredName: string;
  readonly Notes: string;
  readonly ValidFromDate: string;
  readonly ValidUntilDate: string;
  readonly MemberParties: string;
  readonly PartyRoles: string;

}
