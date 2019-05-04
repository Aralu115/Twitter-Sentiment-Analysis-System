import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {Tweet} from '../model/Tweet';
import {TS} from '../model/TS';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  constructor(private http: HttpClient) { }

  getTweets(term: String, num: String): Observable<TS[]> {
    return this.http.get<TS[]>(`/api/tweets/${term}/${num}`);
  }

  getTweet(): Observable<String> {
    return this.http.get<String>(`/api/tweet`, {responseType: 'text' as 'json'});
  }

  sendTestData(tweet: String, sentiment: String): Observable<String> {
    return this.http.get<String>(`/api/test/${tweet}/${sentiment}`, {responseType: 'text' as 'json'});
  }
}
