import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TweetService} from '../services/tweet.service';
import {Observable} from 'rxjs';
import {Tweet} from '../model/Tweet';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {
  term: String;
  num: String;
  tweets: Tweet[];

  constructor(private router: Router, private activatedRoute: ActivatedRoute,
              private tweetService: TweetService) { }

  ngOnInit() {
    this.term = this.activatedRoute.snapshot.url[1].path;
    this.num = this.activatedRoute.snapshot.url[2].path;
    console.log(this.term, this.num);
    this.getTweets();
  }

  getTweets(): void {
    this.tweetService.getTweets(this.term, this.num)
      .subscribe(tweets => this.tweets = tweets);
  }

}
