import { Component, inject , ChangeDetectorRef, computed } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { SortService } from '../../service/sort.service';
import { CommonModule } from '@angular/common'; // Add this
import { requestBody } from '../../service/sort.service'; // Import the interface
import { signal } from '@angular/core';

@Component({
  selector: 'app-merge-sort',
  imports: [FormsModule, RouterLink, CommonModule],   
  templateUrl: './merge-sort.html',
  styleUrl: '../bubble-sort/bubble-sort.css',
})
export class MergeSort {
  private sortService = inject(SortService);
  private cdr = inject(ChangeDetectorRef);


  speed = 100; // melli seconds
  currentArray = signal<number[]>([]);
  maxVal = 400; // Matches your Java random generator max
  currentStepIndex = signal(-1);
  ptr1 = signal(-1);
  ptr2 = signal(-1);
  isSwapping = signal(false); 
  totalComparisons = 0;
  totalInterchanges = 0;
  request: requestBody = {
    size: 10,
    order: 4, // 1: sorted, 2: reverse sorted, 3: random, 4: file input
    array: [] // Initialize the array for file input
  };

  // store the steps returned from backend 
  steps = signal<any[]>([]);
  
  initalize() {
    this.sortService.getSortSteps_merge(this.request).subscribe((data) => {
      this.steps.set(data);
      console.log(this.steps());
      
      this.currentStepIndex.set(-1);
      this.ptr1.set(-1);
      this.ptr2.set(-1);
      this.totalComparisons = 0;
      this.totalInterchanges = 0;
      this.isSwapping.set(false);
      this.currentArray.set([...this.steps()[0].array]);
      console.log(this.currentArray());
      this.maxVal = 400;
      
      this.stopAutoRun(); 
    });
}
  nextStep() {
  if (this.currentStepIndex() < this.steps().length - 1) {
    
    this.currentStepIndex.set(this.currentStepIndex() + 1);
    const step = this.steps()[this.currentStepIndex()];
    // FORCE A NEW REFERENCE HERE
    this.currentArray.set([...step.array]); 
    
    this.ptr1.set(step.ptr1);
    this.ptr2.set(step.ptr2);
    this.totalComparisons = step.totalComparisons;
    this.totalInterchanges = step.totalInterchanges;
    this.isSwapping.set(step.operation === 'swap');
    console.log(step);
  }
}
  prevStep() {
    if (this.currentStepIndex() > 0) {
      this.currentStepIndex.set(this.currentStepIndex() - 1);
      const step = this.steps()[this.currentStepIndex()];
      this.currentArray.set([...step.array]);
      this.ptr1.set(step.ptr1);
      this.ptr2.set(step.ptr2);
      this.totalComparisons = step.totalComparisons;
      this.totalInterchanges = step.totalInterchanges;
      this.isSwapping.set(step.operation === 'swap');
    }
  }

  stop_running = false; // A flag to control the loop
  async autoRun() {
    this.stop_running = true; // Reset the flag at the start
    while (this.currentStepIndex() + 1 < this.steps().length && this.stop_running) {
      this.nextStep();
      
      this.cdr.detectChanges();

      // This line tells the loop to pause for 'speed' ms
      await new Promise(resolve => setTimeout(resolve, this.speed));

      // console.log("Auto-running step:", this.currentStepIndex() , this.steps().length);
    }
    console.log("Auto-run stopped or completed.");
    this.stopAutoRun();
  }
  stopAutoRun() {
    this.stop_running = false; // Set the flag to false to stop the loop
  }

  onFileSelected(event: Event): void {
    // get the input element and the selected file
    const input = event.target as HTMLInputElement;
    const file: File | null = input.files ? input.files[0] : null;

    if (file) {
      // ensure the file is actually a text file
      if (file.type === 'text/plain') {
        
        // create a FileReader
        const reader = new FileReader();

        // define what happens when the reader finishes reading
        reader.onload = (e: ProgressEvent<FileReader>) => {
          const fileContent = e.target?.result as string;
          
          if (fileContent) {
            // convert the string content into an array of numbers
            this.processFileContent(fileContent);
          }
        };

        // define what happens on error
        reader.onerror = (e) => {
          console.error("Error reading file", e);
        };

        // tell the reader to read the file as text
        reader.readAsText(file);

      } else {
        alert("Please select a valid .txt file.");
      }
    }
  }

  private processFileContent(content: string): void {
    this.request.array = content
      .split(',')                     
      .map(item => item.trim())       
      .filter(item => item !== '')    
      .map(item => Number(item))      
      .filter(item => !isNaN(item));  

    console.log('Successfully converted to array:', this.request.array);
  }
  
  maxAbsVal = computed(() => {
    const arr = this.currentArray();
    if (arr.length === 0) return 1; 
    
    const minVal = Math.min(...arr);
    const maxVal = Math.max(...arr);
    return Math.max(Math.abs(minVal), Math.abs(maxVal));
  });

  getBarHeight(value: number): number {
    if (this.maxAbsVal() === 0) return 0;
    return (Math.abs(value) / this.maxAbsVal()) * 50; 
  }

  getBarBottom(value: number): number {
    const height = this.getBarHeight(value);
    return value >= 0 ? 50 : 50 - height;
  }
}
