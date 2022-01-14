import { Page } from './page';
import { Observable } from 'rxjs';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})

export class DataTableComponent implements OnInit {

  @Input() id: string = ""
  @Input() columns: DataTables.ColumnSettings[] = []
  @Input() getData: ((parameters: Object) => Observable<Page<any>>) | null = null
  
  dtOptions: DataTables.Settings = {}

  constructor() { }

  ngOnInit(): void {
    this.dtOptions = {
      responsive: true,
      ordering: true,

      columns: this.columns,
      
      serverSide: true,
      ajax: (parameters: any, callback: any) => {
        parameters = {
          page: parameters.start/parameters.length,
          size: parameters.length,
          sort: parameters.columns[parameters.order[0].column].data,
          order: parameters.order[0].dir
        }

        if (this.getData != null) {
          this.getData(parameters).subscribe(result => {
            callback({
              data: result.content,
              recordsTotal: result.totalElements,
              recordsFiltered: result.totalElements
            })
          })
        }
      },

      dom: "t<<'d-flex'<'flex-grow-1'i><p>><l>>",
      searching: false
    }
  }

}
