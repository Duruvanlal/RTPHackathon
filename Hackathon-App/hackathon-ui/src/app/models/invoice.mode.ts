export class InvoiceMaster{
    id:any;
    invoiceRefId:any;
    poNumber:any;
    inventryTerm : any
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
