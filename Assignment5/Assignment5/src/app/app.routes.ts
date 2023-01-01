import {Routes} from "@angular/router";
import {MspListComponent} from "./msp-list/msp-list.component";
import {MspDetailsComponent} from "./msp-details/msp-details.component";

export const appRoutes: Routes = [
  {path: 'list', component: MspListComponent},
  {path: 'msp-details/:personID', component: MspDetailsComponent},
  {path: '', redirectTo: '/list', pathMatch: 'full'},
  {path: "**", redirectTo: '/list', pathMatch: 'full'}
];
