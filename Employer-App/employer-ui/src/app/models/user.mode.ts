

export class User {
    id:Number;
    userName:String;
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
    company:any = new Company();
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

