import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuickSort } from './quick-sort';

describe('QuickSort', () => {
  let component: QuickSort;
  let fixture: ComponentFixture<QuickSort>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [QuickSort]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuickSort);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
