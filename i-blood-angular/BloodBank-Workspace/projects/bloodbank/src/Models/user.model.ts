export class User {
    constructor(
        public userId: number,
        public firstName: string,
        public lastName: string,
        public email: string,
        public password: string,
        public dateOfBirth: Date,
        public gender: string,
        public address: string,
        public contactNumber: number,
        public alternateContactNumber: number,
        public type: string,
        public bloodGroupId: number,
        public bloodBankId: number
    ) { }
} 