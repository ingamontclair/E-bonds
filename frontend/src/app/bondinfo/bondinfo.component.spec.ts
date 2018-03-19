import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BondinfoComponent } from './bondinfo.component';

describe('BondinfoComponent', () => {
  let component: BondinfoComponent;
  let fixture: ComponentFixture<BondinfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BondinfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BondinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
