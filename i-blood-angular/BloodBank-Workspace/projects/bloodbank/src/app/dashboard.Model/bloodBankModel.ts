export class BloodBank {
    constructor(
        public bloodBankId: number,
        public bloodBankName: string,
        public bloodBankLocation: string,
        public availableBloodGroup: string,
        public bloodQuantity: string
    ) { }
}