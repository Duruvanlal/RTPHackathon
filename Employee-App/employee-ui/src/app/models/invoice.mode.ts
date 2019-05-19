


export class User {
    id:Number;
    userId:String;
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
    address:any = new Address();
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

export class Invoice{
    id:Number;
    invoiceNumber:String;
    sender:any = new User();
    receiver:any = new User();
}

