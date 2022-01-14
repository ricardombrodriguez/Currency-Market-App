import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourextensionComponent } from './yourextension.component';

describe('YourextensionComponent', () => {
  let component: YourextensionComponent;
  let fixture: ComponentFixture<YourextensionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YourextensionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(YourextensionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
