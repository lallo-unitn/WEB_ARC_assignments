/**
 * Interface that represent a member of the parliament.
 *
 * @interface MspEntry
 */
export interface MspEntry {
  readonly PersonID: number;
  readonly PhotoURL?: string;
  readonly Notes: string;
  readonly BirthDate: string;
  readonly ParliamentaryName: string;
  readonly GenderTypeID: number;
  readonly IsCurrent: boolean;
}
