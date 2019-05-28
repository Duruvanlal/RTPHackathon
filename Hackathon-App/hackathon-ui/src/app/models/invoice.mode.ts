export class InvoiceMaster{
    id:any;
    invoiceRefId:any;
    poNumber:any;
    inventryTerm : any;
    totalAmount : any;
    buyerUserId : any;
    sellerUserId : any;
    buyerUpaId : any;
    sellerUpaId : any;
    inventoryDueDateTime : any;
    inventorySubDateTime : any;
    createdBy : any;
    createdDateTime : any;
    invoiceDetail : InvoiceDetail[];
}

export class InvoiceDetail{
    id:any;
    invoiceRefId:any;
    productName:any;
    productDesc : any
    productQty : any;
    unitPrice : any;
    totalAmount : any;
   
}

export class RTPRequest{
    createdBy :any;
    payerUpaCd : any;
    paymentAmount:any;
    paymentReasonRefId:any;
    paymentReasonType ='INVOICE';
    requestorUpaCd : any;

}

export class RfpRequest{
    action : any;
    referenceId : any;
    rejectReason : any;
}