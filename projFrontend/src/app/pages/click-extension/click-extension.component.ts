import { Component, OnInit } from '@angular/core';
import { ExtensionService } from 'src/app/services/extension.service';

@Component({
  selector: 'app-click-extension',
  templateUrl: './click-extension.component.html',
  styleUrls: ['./click-extension.component.css']
})
export class ClickExtensionComponent implements OnInit {

  constructor(private extensionService: ExtensionService) { }

  ngOnInit(): void {
  }

  buy() {
    this.extensionService.clickBuy().subscribe();
  }

  sell() {
    this.extensionService.clickSell().subscribe();
  }

}
