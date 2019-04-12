import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  term = '';
  value = 0;
  routeNum = '';

  constructor() { }

  ngOnInit() {
  }

  convert() {
    this.routeNum = this.value.toString();
    console.log(this.routeNum);
  }
}
