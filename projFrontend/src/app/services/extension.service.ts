import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';
import { Extension } from './../interfaces/extension';
import { Page } from './../components/data-table/page';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ExtensionService {

  constructor(private http: HttpClient) { }

  getAllExtensions(): Observable<Page<Extension>> {
    return this.http.get<Page<Extension>>(environment.API_URL + '/extension')
  }

  getUserExtensions(id: number): Observable<Page<Extension>> {
    return this.http.get<Page<Extension>>(environment.API_URL + '/extension/' + id, { params: { id } })
  }

  installExtension(portfolioId: number, extensionId: number) { }

  uninstallExtension(portfolioId: number, extensionId: number) { }

  createExtension(userId: number, path: string) {
    return this.http.post<Extension>(environment.API_URL + '/extension', {}, { params: { userId, path } })
  }

  deleteExtension(extensionId: number) {
    return this.http.delete(environment.API_URL + '/extension/' + extensionId)
  }
}
