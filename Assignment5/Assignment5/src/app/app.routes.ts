import {Routes} from "@angular/router";
import {ParliamentariansOverviewComponent} from "./parliamentarians-overview/parliamentarians-overview.component";

export const appRoutes: Routes = [
  {path: 'list', component: ParliamentariansOverviewComponent},
  {path: '', redirectTo: '/list', pathMatch: 'full'},
  {path: "**", redirectTo: '/list', pathMatch: 'full'}
];
