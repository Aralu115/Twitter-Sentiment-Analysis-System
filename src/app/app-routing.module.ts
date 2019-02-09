import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SearchComponent} from './search/search.component';
import {AboutComponent} from './about/about.component';
import {ResultsComponent} from './results/results.component';

const routes: Routes = [
  {path: '', component: SearchComponent},
  {path: 'about', component: AboutComponent},
  {path: 'results/:term', component: ResultsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
