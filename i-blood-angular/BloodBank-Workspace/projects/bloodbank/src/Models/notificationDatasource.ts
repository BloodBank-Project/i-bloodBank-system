import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private notificationsSubject: BehaviorSubject<string[]> = new BehaviorSubject<string[]>([]);
  notifications$: Observable<string[]> = this.notificationsSubject.asObservable();

  constructor() { }

  addNotification(notification: string): void {
    const currentNotifications = this.notificationsSubject.getValue();
    const updatedNotifications = [...currentNotifications, notification];
    this.notificationsSubject.next(updatedNotifications);
  }

  clearNotifications(): void {
    this.notificationsSubject.next([]);
  }
}
