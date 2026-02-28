import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertionSort } from './insertion-sort';

describe('InsertionSort', () => {
  let component: InsertionSort;
  let fixture: ComponentFixture<InsertionSort>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InsertionSort]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InsertionSort);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
