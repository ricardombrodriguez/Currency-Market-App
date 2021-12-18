import { Page } from './page';
import { Observable } from 'rxjs';

export interface PageableService {
  getPage: (parameters: Object) => Observable<Page<any>>
}
