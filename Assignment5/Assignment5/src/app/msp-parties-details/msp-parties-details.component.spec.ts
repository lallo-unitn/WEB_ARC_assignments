import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MspPartiesDetailsComponent } from './msp-parties-details.component';

describe('MspPartiesComponent', () => {
  let component: MspPartiesDetailsComponent;
  let fixture: ComponentFixture<MspPartiesDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MspPartiesDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MspPartiesDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
