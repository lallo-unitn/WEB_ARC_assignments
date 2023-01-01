import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParliamentarianGridItemComponent } from './parliamentarian-grid-item.component';

describe('ParliamentarianGridItemComponent', () => {
  let component: ParliamentarianGridItemComponent;
  let fixture: ComponentFixture<ParliamentarianGridItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParliamentarianGridItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParliamentarianGridItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
