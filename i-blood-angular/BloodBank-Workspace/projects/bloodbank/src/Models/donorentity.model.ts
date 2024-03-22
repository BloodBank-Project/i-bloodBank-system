import { donor_form } from "./donor.model";

export class donorEntity {
    constructor(
        public id: number,
        public userId: number,
        public donarDetails: donor_form[]
    ) { }
} 