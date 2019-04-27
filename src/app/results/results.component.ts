import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TweetService} from '../services/tweet.service';
import {Observable} from 'rxjs';
import {Tweet} from '../model/Tweet';
import {TS} from '../model/TS';
import {MatPaginator, MatSort} from '@angular/material';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {
  term: String;
  num: String;
  results: TS[] = [];
  displayedColumns: string[] = ['id','tweet','sentiment'];

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  constructor(private router: Router, private activatedRoute: ActivatedRoute,
              private tweetService: TweetService) { }

  ngOnInit() {
    this.term = this.activatedRoute.snapshot.url[1].path;
    this.num = this.activatedRoute.snapshot.url[2].path;
    console.log(this.term, this.num);
    this.getTweets();
    this.isLoadingResults = false;
    this.isRateLimitReached = false;
    this.resultsLength = this.results.length;
  }

  getTweets(): void {
    this.tweetService.getTweets(this.term, this.num)
      .subscribe(results => this.results = results);
  }

}
