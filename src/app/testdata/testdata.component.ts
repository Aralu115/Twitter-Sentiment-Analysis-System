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
  success: String;
  constructor(private tweetService: TweetService) { }

  ngOnInit() {
    this.tweetService.getTweet()
      .subscribe(tweet => this.tweet = tweet);
  }

  getTweet() {
    this.tweetService.getTweet()
      .subscribe(tweet => this.tweet = tweet);
  }

  //sends the positive sentiment and tweet to back end
  sendPositive() {
    this.tweetService.sendTestData(this.tweet, "1.0")
      .subscribe(success => this.success = success);
    console.log(this.success);
    this.tweetService.getTweet()
      .subscribe(tweet => this.tweet = tweet);
  }

  //sends the neutral sentiment and tweet to back end
  sendNeutral() {
    this.tweetService.sendTestData(this.tweet, "0.5")
      .subscribe(success => this.success = success);
    console.log(this.success);
    this.tweetService.getTweet()
      .subscribe(tweet => this.tweet = tweet);
  }

  //sends the negative sentiment and tweet to back end
  sendNegative() {
    this.tweetService.sendTestData(this.tweet, "0.0")
      .subscribe(success => this.success = success);
    console.log(this.success);
    this.tweetService.getTweet()
      .subscribe(tweet => this.tweet = tweet);
  }

  refresh() {
    window.location.reload();
  }

  //positive = 1.0
  //negative = 0.0
  //neutral = 0.5
}
