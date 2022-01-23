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
    { render: (a, b, row) => `<button type="button" id="${row.id}" class="btn btn-danger btn-sm del-extension"><i class="fas fa-trash"></i></button><a>`, orderable: false }
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


    // this.extensionService.deleteExtension(3).subscribe((extension) => {
    //   console.log("extension deleted")
    //   console.log(extension)
    // })

  }

  ngOnInit(): void {
    var exts = this.extensionService;
    
    $(document).on('click', '.del-extension', function() {
      console.log(parseInt(this.id));
      exts.deleteExtension(parseInt(this.id)).subscribe((extensions) => {});
      window.location.reload();
    });

  }
  

  n_extension = { ename: "", edescription: "", epath: ""}
  public error = false;


  public createExtension(){
    this.error = false;

    if ((<HTMLInputElement>document.getElementById("name")).value == "" || (<HTMLInputElement>document.getElementById("description")).value == "" || (<HTMLInputElement>document.getElementById("path")).value == "") {
      this.error = true;
      return
    }

    this.extensionService.createExtension(this.userId, (<HTMLInputElement>document.getElementById("name")).value, (<HTMLInputElement>document.getElementById("description")).value, (<HTMLInputElement>document.getElementById("path")).value).subscribe((extensions) => {})
    window.location.reload();
  }

}