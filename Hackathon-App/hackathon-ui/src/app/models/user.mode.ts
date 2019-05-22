

export class User {
    id:Number;
    userId:String;
    userName : any;
    firstName:String;
    lastName:String;
    password:String;
    rePassword:String;
    type:String;
    phone:String;
    email:String;
    status:String;
    createdBy:String;
    updatedBy:String;
    address1:String;
    address2:String;
    city:String;
    state:String;
    country:String;
    postalCode:String;
    name:String;
    entityType:String;
}

export class Address{
    id:Number;
    address1:String;
    address2:String;
    city:String;
    state:String;
    country:String;
    postalCode:String;
}

export class Company{
    id:Number;
    name:String;
    type:String;
    address:any = new Address();
}

export class UserPmtAccount{
    id: any;
    userId: any;
    upaCD: any;
    accountType: any;
    shortName: any;
    accountName: any;
    bankName: any;
    accountNumber: any;
    routingNumber: any;
    createdBy: any;
    createdDateTime: any;
    updatedBy: any;
    updatedDateTime: any;
}

