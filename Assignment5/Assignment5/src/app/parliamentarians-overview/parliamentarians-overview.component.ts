import {Component, OnInit} from '@angular/core';
import {ParliamentarianService} from "../services/parliamentarian.service";
import {ParliamentarianEntry} from "../entries/parliamentarian.entry";

@Component({
  selector: 'app-parliamentarians-overview',
  templateUrl: './parliamentarians-overview.component.html',
  styleUrls: ['./parliamentarians-overview.component.css']
})
export class ParliamentariansOverviewComponent implements OnInit {

  public parliamentarians!: ParliamentarianEntry[];
  public parliamentarian!: ParliamentarianEntry;

  public defaultImage: string = "";

  constructor(private parliamentarianService: ParliamentarianService) {
  }

  ngOnInit(): void {
    this.parliamentarianService.getParliamentarians().subscribe(data => {
      this.parliamentarians = data;
    });
  }

}
