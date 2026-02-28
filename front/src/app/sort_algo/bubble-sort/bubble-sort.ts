import { Component, inject , ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { SortService } from '../../service/sort.service';
import { CommonModule } from '@angular/common'; // Add this

@Component({
  selector: 'app-bubble-sort',
  imports: [FormsModule, RouterLink, CommonModule],   
  templateUrl: './bubble-sort.html',
  styleUrl: './bubble-sort.css',
})
export class BubbleSort {
  private sortService = inject(SortService);
  private cdr = inject(ChangeDetectorRef);

  arraySize = 10;
  speed = 100; // melli seconds
  currentArray: number[] = [];
  maxVal = 400; // Matches your Java random generator max
  ptr1 = -1;
  ptr2 = -1;
  totalComparisons = 0;
  totalInterchanges = 0;
  isSwapping = false;

  // store the steps returned from backend 
  steps: any[] = [];
  currentStepIndex = 0;
  
  initalize() {
    this.sortService.getSortSteps(this.arraySize).subscribe((data) => {
      this.steps = data;
      console.log(this.steps);
      
      this.currentStepIndex = 0;
      this.ptr1 = -1;
      this.ptr2 = -1;
      this.totalComparisons = 0;
      this.totalInterchanges = 0;
      this.isSwapping = false;
      this.currentArray = [...this.steps[0].array];
      console.log(this.currentArray);
      this.maxVal = 400;
      
    });
}
  nextStep() {
  if (this.currentStepIndex < this.steps.length) {
    const step = this.steps[this.currentStepIndex];
    
    // FORCE A NEW REFERENCE HERE
    this.currentArray = [...step.array]; 
    
    this.ptr1 = step.ptr1;
    this.ptr2 = step.ptr2;
    this.totalComparisons = step.totalComparisons;
    this.totalInterchanges = step.totalInterchanges;
    this.isSwapping = step.operation === 'swap';
    this.currentStepIndex++;
    console.log(step);
  }
}
  prevStep() {
    if (this.currentStepIndex > 0) {
      this.currentStepIndex--;
      const step = this.steps[this.currentStepIndex];
      this.currentArray = step.array;
      this.ptr1 = step.ptr1;
      this.ptr2 = step.ptr2;
      this.totalComparisons = step.totalComparisons;
      this.totalInterchanges = step.totalInterchanges;
      this.isSwapping = step.operation === 'swap';
    }
  }
  start() {
    this.currentArray = [...this.steps[0].array];
    this.ptr1 = -1;
    this.ptr2 = -1;
    this.totalComparisons = 0;
    this.totalInterchanges = 0;
    this.isSwapping = false;
    this.currentStepIndex = 0;
  }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  async autoRun() {

  // 2. The loop now "awaits" the delay
  while (this.currentStepIndex < this.steps.length) {
    this.nextStep();
    
    this.cdr.detectChanges();

    // This line tells the loop to pause for 'speed' ms
    await new Promise(resolve => setTimeout(resolve, this.speed));
  }
}
}
