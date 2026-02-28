import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MergeSort } from './merge-sort';

describe('MergeSort', () => {
  let component: MergeSort;
  let fixture: ComponentFixture<MergeSort>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MergeSort]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MergeSort);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
