import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MspGridItemComponent } from './msp-grid-item.component';

describe('ParliamentarianGridItemComponent', () => {
  let component: MspGridItemComponent;
  let fixture: ComponentFixture<MspGridItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MspGridItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MspGridItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
