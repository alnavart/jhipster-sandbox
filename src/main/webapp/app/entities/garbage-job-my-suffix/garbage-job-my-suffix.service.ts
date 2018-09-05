import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGarbageJobMySuffix } from 'app/shared/model/garbage-job-my-suffix.model';

type EntityResponseType = HttpResponse<IGarbageJobMySuffix>;
type EntityArrayResponseType = HttpResponse<IGarbageJobMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class GarbageJobMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/garbage-jobs';

    constructor(private http: HttpClient) {}

    create(garbageJob: IGarbageJobMySuffix): Observable<EntityResponseType> {
        return this.http.post<IGarbageJobMySuffix>(this.resourceUrl, garbageJob, { observe: 'response' });
    }

    update(garbageJob: IGarbageJobMySuffix): Observable<EntityResponseType> {
        return this.http.put<IGarbageJobMySuffix>(this.resourceUrl, garbageJob, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGarbageJobMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGarbageJobMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
