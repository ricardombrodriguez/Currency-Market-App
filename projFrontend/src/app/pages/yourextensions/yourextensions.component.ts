import { ExtensionService } from './../../services/extension.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-yourextensions',
  templateUrl: './yourextensions.component.html',
  styleUrls: ['./yourextensions.component.css']
})
export class YourextensionsComponent implements OnInit {

  constructor(private extensionService: ExtensionService) { }

  ngOnInit(): void {
  }

}
