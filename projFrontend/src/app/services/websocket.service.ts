import { Injectable } from '@angular/core';

declare var SockJS: any;
declare var Stomp: any;

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  constructor() { }

  startUpdates(channel: string, callback: (data: any) => void): void {
    var socket = new SockJS('http://localhost:8080/ws');
    let stompClient = Stomp.over(socket);
    stompClient.connect({}, () => stompClient.subscribe(channel, callback));
  }
  
}
