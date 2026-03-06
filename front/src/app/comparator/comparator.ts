import { Component , inject } from '@angular/core';
import { signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SortService } from '../service/sort.service';
import { requestBody } from '../service/sort.service';
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-comparator',
  imports: [FormsModule, CommonModule],
  templateUrl: './comparator.html',
  styleUrl: './comparator.css',
})
export class Comparator {
  private sortService = inject(SortService);
 

  request: requestBody = {
    size: 100 ,
    runs: 100,
    order: 4, // 1: sorted, 2: reverse sorted, 3: random, 4: file input
    array: [] // Initialize the array for file input
  };

  Data = signal<any[]>([]);

  runBenchmark() {
    console.log("Running benchmark with request:", this.request);
    this.sortService.getBenchmarkData(this.request).subscribe((data) => {
      this.Data.set(data);
      console.log(this.Data());
      
    });
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
      .split(',')                     // Break the string into an array at every comma
      .map(item => item.trim())       // Remove any accidental spaces (e.g., " 5 " becomes "5")
      .filter(item => item !== '')    // Remove empty strings (in case the file ends with a comma)
      .map(item => Number(item))      // Convert the cleaned strings into Numbers
      .filter(item => !isNaN(item));  // Discard anything that couldn't be converted to a valid number

    console.log('Successfully converted to array:', this.request.array);
  }

}
