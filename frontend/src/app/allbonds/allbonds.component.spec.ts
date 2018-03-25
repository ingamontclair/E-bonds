import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllbondsComponent } from './allbonds.component';

describe('AllbondsComponent', () => {
  let component: AllbondsComponent;
  let fixture: ComponentFixture<AllbondsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllbondsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllbondsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
