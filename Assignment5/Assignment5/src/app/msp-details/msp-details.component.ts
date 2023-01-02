import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Observable, Subscription} from "rxjs";
import {MspEntry} from "../entries/msp.entry";
import {MspService} from "../services/msp.service";

@Component({
  selector: 'app-parliamentarian-details',
  templateUrl: './msp-details.component.html',
  styleUrls: ['./msp-details.component.css']
})
export class MspDetailsComponent implements OnInit, OnDestroy {

  public mspPersonalDetails$!: Observable<MspEntry>;
  private sub: Subscription | undefined;
  public personID!: number;

  constructor(private router: Router, private route: ActivatedRoute, private mspService: MspService) {
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(val => {
      this.personID = +val['personID']; // (+) converts string 'id' to a number
      this.mspPersonalDetails$ = this.mspService.getMspEntry(this.personID); // Getting the data from the service
      this.mspPersonalDetails$.subscribe(val => {
        if (!val) {
          this.router.navigate(['/']); // If the data is not found, redirect to the home page
        }
      });
    });
  }

  ngOnDestroy(): void {
    this.sub?.unsubscribe();
  }

  onBackClick(): void {
    this.router.navigate(['/list']);
  }

}
