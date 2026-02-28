import { Routes } from '@angular/router';
import { BubbleSort } from './sort_algo/bubble-sort/bubble-sort';
import { SelectionSort } from './sort_algo/selection-sort/selection-sort';
import { InsertionSort } from './sort_algo/insertion-sort/insertion-sort';
import { MergeSort } from './sort_algo/merge-sort/merge-sort';
import { QuickSort } from './sort_algo/quick-sort/quick-sort'; 
import { HeapSort } from './sort_algo/heap-sort/heap-sort';
import { HomeComponent } from './home-component/home-component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'bubble-sort', component: BubbleSort },
  { path: 'selection-sort', component: SelectionSort },
  { path: 'insertion-sort', component: InsertionSort },
  { path: 'merge-sort', component: MergeSort },
  { path: 'quick-sort', component: QuickSort },
  { path: 'heap-sort', component: HeapSort }
  
];