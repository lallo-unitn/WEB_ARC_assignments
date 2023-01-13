/**
 * Interface that represent the relation between a {@link MspEntry} and a {@link PartyEntry}.
 *
 * @interface MspPartyEntry
 */
export interface MspPartyEntry {
  readonly ID: number;
  readonly PersonID: number;
  readonly PartyID: number;
  readonly ValidFromDate: string;
  readonly ValidUntilDate: string;
  readonly MemberPartyRoles: string;

}
