import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuybondComponent } from './buybond.component';

describe('BuybondComponent', () => {
  let component: BuybondComponent;
  let fixture: ComponentFixture<BuybondComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuybondComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuybondComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
