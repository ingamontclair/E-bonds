import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellbondComponent } from './sellbond.component';

describe('SellbondComponent', () => {
  let component: SellbondComponent;
  let fixture: ComponentFixture<SellbondComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellbondComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellbondComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
