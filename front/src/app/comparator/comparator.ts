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
    size: 100,
    runs: 100,
    order: 3 // 1: sorted, 2: reverse sorted, 3: random, 4: file input
  };

  Data = signal<any[]>([]);

  runBenchmark() {
    console.log("Running benchmark with request:", this.request);
    this.sortService.getBenchmarkData(this.request).subscribe((data) => {
      this.Data.set(data);
      console.log(this.Data());
      
    });
  }

  fileContent: string = '';

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
        reader.onload = (e: any) => {
          this.fileContent = e.target.result;
          console.log("file content:", this.fileContent);
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

}
