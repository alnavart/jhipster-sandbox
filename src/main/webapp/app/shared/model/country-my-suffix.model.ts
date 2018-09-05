export interface ICountryMySuffix {
    id?: number;
    countryName?: string;
    isTargetOfCorea?: boolean;
    regionId?: number;
}

export class CountryMySuffix implements ICountryMySuffix {
    constructor(public id?: number, public countryName?: string, public isTargetOfCorea?: boolean, public regionId?: number) {
        this.isTargetOfCorea = this.isTargetOfCorea || false;
    }
}
