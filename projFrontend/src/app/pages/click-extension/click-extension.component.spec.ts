import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClickExtensionComponent } from './click-extension.component';

describe('ClickExtensionComponent', () => {
  let component: ClickExtensionComponent;
  let fixture: ComponentFixture<ClickExtensionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClickExtensionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClickExtensionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
