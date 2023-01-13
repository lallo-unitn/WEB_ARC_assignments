/**
 * Interface that represent a website of a member of the parliament.
 *
 * @interface WebsiteEntity
 */
export interface WebsiteEntity {
  readonly ID: number;
  readonly PersonID: number;
  readonly WebsiteTypeID: number;
  readonly WebURL: string;
  readonly IsDefault: boolean;
}
