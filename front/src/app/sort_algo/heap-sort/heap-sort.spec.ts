import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeapSort } from './heap-sort';

describe('HeapSort', () => {
  let component: HeapSort;
  let fixture: ComponentFixture<HeapSort>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeapSort]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeapSort);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
