import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MspPersonalDetailComponent } from './msp-personal-detail.component';

describe('MspPersonalDetailComponent', () => {
  let component: MspPersonalDetailComponent;
  let fixture: ComponentFixture<MspPersonalDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MspPersonalDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MspPersonalDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
