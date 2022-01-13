import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoinsComponent } from '../coins/coins.component';
import { MarketsComponent } from '../markets/markets.component';


describe('CoinsComponent', () => {
  let component: CoinsComponent;
  let fixture: ComponentFixture<CoinsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoinsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

describe('MarketsComponent', () => {
  let component: MarketsComponent;
  let fixture: ComponentFixture<MarketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MarketsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

