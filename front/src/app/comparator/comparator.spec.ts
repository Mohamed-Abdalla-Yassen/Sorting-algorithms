import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Comparator } from './comparator';

describe('Comparator', () => {
  let component: Comparator;
  let fixture: ComponentFixture<Comparator>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Comparator]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Comparator);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
