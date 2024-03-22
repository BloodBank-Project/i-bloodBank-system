export class PatientRequest {
    constructor(
        public patientRequestId: number,
        public firstName: string,
        public bloodType: string,
        public patientRequestUnits: number,
        public patientRequestOnDate: Date,
        public patientMedicalCondition: string,
        public status: string,
        public buttonDisabled: boolean,
        public disabled:boolean
    ) { }
}