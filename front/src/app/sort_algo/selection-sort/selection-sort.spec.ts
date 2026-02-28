import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectionSort } from './selection-sort';

describe('SelectionSort', () => {
  let component: SelectionSort;
  let fixture: ComponentFixture<SelectionSort>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelectionSort]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectionSort);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
