export interface ParliamentarianEntry {
  readonly PersonID: number;
  readonly PhotoURL?: string;
  readonly Notes: string;
  readonly BirthDate: string;
  readonly ParliamentaryName: string;
  readonly GenderTypeID: number;
  readonly IsCurrent: boolean;
}
