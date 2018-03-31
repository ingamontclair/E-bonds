import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddmoneyComponent } from './addmoney.component';

describe('AddmoneyComponent', () => {
  let component: AddmoneyComponent;
  let fixture: ComponentFixture<AddmoneyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddmoneyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddmoneyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
