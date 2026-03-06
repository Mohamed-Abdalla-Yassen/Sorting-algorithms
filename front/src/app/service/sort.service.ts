import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class SortService {
    
    private baseUrl = 'http://localhost:8080'; 

    constructor(private http: HttpClient) {}

    /* return an Observable so the Component can subscribe and handle the data */
    
    getSortSteps_bubble(request: requestBody): Observable<any[]> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<any[]>(this.baseUrl + '/bubble_sort', request, { headers });
    }
    getSortSteps_selection(request: requestBody): Observable<any[]> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<any[]>(this.baseUrl + '/selection_sort', request, { headers });
    }
    getSortSteps_insertion(request: requestBody): Observable<any[]> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<any[]>(this.baseUrl + '/insertion_sort', request, { headers });
    }
    getSortSteps_quick(request: requestBody): Observable<any[]> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<any[]>(this.baseUrl + '/quick_sort', request, { headers });
    }
    getSortSteps_heap(request: requestBody): Observable<any[]> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<any[]>(this.baseUrl + '/heap_sort', request, { headers });
    }
    getSortSteps_merge(request: requestBody): Observable<any[]> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<any[]>(this.baseUrl + '/merge_sort', request, { headers });
    }

    //! Benchmarking endpoint
    getBenchmarkData(request: requestBody): Observable<any[]> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<any[]>(this.baseUrl + '/comparison/benchMark', request, { headers });
    }
}
export interface requestBody {
    size: number;
    order: number;
    runs?: number ; // number of runs for benchmarking
    array?: number[]; // optional array for file input
}