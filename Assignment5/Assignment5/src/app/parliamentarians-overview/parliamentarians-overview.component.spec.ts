import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParliamentariansOverviewComponent } from './parliamentarians-overview.component';

describe('ParliamentariansOverviewComponent', () => {
  let component: ParliamentariansOverviewComponent;
  let fixture: ComponentFixture<ParliamentariansOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParliamentariansOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParliamentariansOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
