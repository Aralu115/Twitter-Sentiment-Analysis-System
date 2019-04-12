import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SearchComponent} from './search/search.component';
import {AboutComponent} from './about/about.component';
import {ResultsComponent} from './results/results.component';
import {TestdataComponent} from './testdata/testdata.component';

const routes: Routes = [
  {path: '', component: SearchComponent},
  {path: 'about', component: AboutComponent},
  {path: 'results/:term/:routeNum', component: ResultsComponent},
  {path: 'testdata', component: TestdataComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
