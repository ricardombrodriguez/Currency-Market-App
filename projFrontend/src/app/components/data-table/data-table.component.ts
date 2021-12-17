import { PageableService } from './pageable-service';
import { Page } from './page';
import { Observable, of } from 'rxjs';
import { ColumnInterface } from './column-interface';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit {

  @Input() columns: ColumnInterface[] = []
  @Input() getPage: (parameters: Object) => Observable<Page<any>> = () => of({ content: [], numberOfElements: 0 })
  @Input() service: PageableService | null = null
  
  public dtOptions: DataTables.Settings = {
    responsive: true,
    
    serverSide: true,
    ajax: (parameters: Object, callback) => {
      if (this.service != null) {
        this.service.getPage(parameters).subscribe(result => {
          callback({
            data: result.content,
            recordsTotal: result.numberOfElements,
            recordsFiltered: result.numberOfElements
          })
        })
      }
    },

    dom: "t<<'d-flex'<'flex-grow-1'i><p>><l>>",
    searching: false
  }

  constructor() { }

  ngOnInit(): void {
  }

}
