import { ExtensionService } from './../../services/extension.service';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Extension } from 'src/app/interfaces/extension';
import { Page } from 'src/app/components/data-table/page';

@Component({
  selector: 'app-yourextensions',
  templateUrl: './yourextensions.component.html',
  styleUrls: ['./yourextensions.component.css']
})
export class YourextensionsComponent implements OnInit {

  private userId: number;
  public extensions!: Page<Extension>

  //public extnsns!: Observable<Page<Extension>>

  columns: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Name', data: 'name' },
    { title: 'Description', data: 'description' },
    { title: 'Path', data: 'path' },
    { render: (a, b, row) => `<button (click)="deleteExtension(${row.id})"} type="button" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i></button><a>`, orderable: false }
    //{ render: (a, b, row) => `<button type="button" class="btn btn-danger btn-sm">Delete <i class="fas fa-trash"></i></button><a>`, orderable: false }
  ]
  getData = (parameters: object) => this.extensionService.getPage(this.userId, parameters)

  constructor(private extensionService: ExtensionService, private authService: AuthenticationService) {

    this.userId = parseInt(this.authService.curentUserId!)
    
    //this.extnsns=this.extensionService.getUserExtensions(this.userId)
    //this.getData =  this.extensionService.getUserExtensions(this.userId)


    // this.extensionService.createExtension(this.userId, "gamz", "hehexd", "blablablalbla").subscribe((extension) => {
    //   console.log("extension created")
    //   console.log(extension)
    // })
    
    // listar todas as extensões criadas pelo user
    this.extensionService.getUserExtensions(this.userId).subscribe((extensions) => {
      this.extensions = extensions
      console.log("user extensions:")
      console.log(this.extensions);
    })


  
    
  }

  ngOnInit(): void {
  }

  public deleteExtension(id: number) : void {
    console.log("_>>>", id);
    this.extensionService.deleteExtension(id);
    window.location.reload();
  }

  public createExtenstion(){
    this.extensionService.createExtension(this.userId, "ervvxxxx", "zzzzz", "1234567").subscribe((extensions) => {})
    window.location.reload();
  }

  myfunc(){
    console.log("ehheheheheehehehxd");
  }

}