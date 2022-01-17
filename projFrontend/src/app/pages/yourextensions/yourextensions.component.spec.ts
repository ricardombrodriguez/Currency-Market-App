import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourextensionsComponent } from './yourextensions.component';

describe('YourextensionsComponent', () => {
  let component: YourextensionsComponent;
  let fixture: ComponentFixture<YourextensionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YourextensionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(YourextensionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
