import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MspDetailsComponent } from './msp-details.component';

describe('ParliamentarianDetailsComponent', () => {
  let component: MspDetailsComponent;
  let fixture: ComponentFixture<MspDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MspDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MspDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
