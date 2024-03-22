export class DonorDetails {
    constructor(
        public donationId: number,
        public firstName: string,
        public bloodType: string,
        public bloodQuantity: Date,
        public dateOfDonation: number,
        public status: string,
        public buttonDisabled: boolean
    ) { }
}