import { Component, OnInit } from '@angular/core';
import {TweetService} from '../services/tweet.service';
import {Observable} from 'rxjs';
import {Tweet} from '../model/Tweet';

@Component({
  selector: 'app-testdata',
  templateUrl: './testdata.component.html',
  styleUrls: ['./testdata.component.css']
})
export class TestdataComponent implements OnInit {

  tweet: String;
  tweetCheck = 0;
  constructor(private tweetService: TweetService) { }

  ngOnInit() {
    this.tweetService.getTweet()
      .subscribe(tweet => this.tweet = tweet);
  }

  getTweet() {
    this.tweetService.getTweet()
      .subscribe(tweet => this.tweet = tweet);
  }

  refresh() {
    window.location.reload();
  }
}
