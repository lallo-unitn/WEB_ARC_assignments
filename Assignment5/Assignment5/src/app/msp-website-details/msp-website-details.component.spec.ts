import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MspWebsiteDetailsComponent } from './msp-website-details.component';

describe('MspWebsiteDetailsComponent', () => {
  let component: MspWebsiteDetailsComponent;
  let fixture: ComponentFixture<MspWebsiteDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MspWebsiteDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MspWebsiteDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
