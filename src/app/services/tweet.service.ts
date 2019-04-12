import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {Tweet} from '../model/Tweet';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  constructor(private http: HttpClient) { }

  getTweets(term: String, num: String): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(`/api/tweets/${term}/${num}`);
  }

  getTweet(): Observable<String> {
    return this.http.get<String>(`/api/tweet`, {responseType: 'text' as 'json'});
  }
}
