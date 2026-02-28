import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class SortService {
    // Note: If your Controller is @RequestMapping("/api"), this is correct.
    // If not, use 'http://localhost:8080'
    private baseUrl = 'http://localhost:8080'; 

    constructor(private http: HttpClient) {}

    /**
     * Fetches sorting steps from the Spring Boot backend.
     * We return an Observable so the Component can subscribe and handle the data.
     */
    getSortSteps(arraySize: number): Observable<any[]> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        
        // Your Java Controller @RequestBody int size expects just the number
        // or a JSON object depending on how you finalized the backend.
        return this.http.post<any[]>(this.baseUrl + '/bubble_sort', arraySize, { headers });
    }
}