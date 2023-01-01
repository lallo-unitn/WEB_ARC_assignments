import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MspListComponent } from './msp-list.component';

describe('ParliamentariansOverviewComponent', () => {
  let component: MspListComponent;
  let fixture: ComponentFixture<MspListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MspListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MspListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
