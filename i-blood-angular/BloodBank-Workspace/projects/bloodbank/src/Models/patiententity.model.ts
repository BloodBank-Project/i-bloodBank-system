import { PatientRequest } from "./patientRequest.model";

export class Patient {
    constructor(
        public patientId: number,
        public userId: number,
        public patientRequestDetails: PatientRequest[]
    ) { }
} 