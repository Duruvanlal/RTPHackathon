
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
    address = new Address();
    entity = new Entity();
}

export class Address{
    id:any;
    addressId:any;
    address1:String;
    address2:String;
    city:String;
    state:String;
    country:String;
    postalCode:String;
}

export class Entity{
    id:Number;
    entityId:any;
    entityName:any;
    entityDesc:any;
    entityAddress = new Address();
}

export class UserEntity{
    entityId:any;
    userId:any;
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

export class Token{
    jwtToken : any;
}

