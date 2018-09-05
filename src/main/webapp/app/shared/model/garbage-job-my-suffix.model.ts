export interface IGarbageJobMySuffix {
    id?: number;
    departmentName?: string;
    realJobName?: string;
    realJobDescription?: string;
    realSalary?: number;
    dismissAfterMaxMonths?: number;
    allowRenew?: boolean;
    jobId?: number;
}

export class GarbageJobMySuffix implements IGarbageJobMySuffix {
    constructor(
        public id?: number,
        public departmentName?: string,
        public realJobName?: string,
        public realJobDescription?: string,
        public realSalary?: number,
        public dismissAfterMaxMonths?: number,
        public allowRenew?: boolean,
        public jobId?: number
    ) {
        this.allowRenew = this.allowRenew || false;
    }
}
