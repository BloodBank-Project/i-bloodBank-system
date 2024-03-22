export class donor_form {
    constructor(
        public donationId: number,
        public firstName: string,
        public bloodType: string,
        public bloodQuantity: number,
        public dateOfDonation: Date,
        public status: string = "pending",
        public donorId: number,
        public buttonDisabled: boolean,
        public disabled:boolean
    ) { }
}  