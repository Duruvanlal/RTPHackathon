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
    company : any;
    status:any;
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

export class AccountBalance{
    demandDepositAccountDTO = new DemandDepositAccountDTO();
}
export class DemandDepositAccountDTO{
    currentBalance = new CurrentBalance();
    availableBalance = new AvailableBalance();
}

export class CurrentBalance{
    currency : any;
    amount : any;
}
export class AvailableBalance{
    currency : any;
    amount : any;
}
// {
//     "status": {
//       "result": "SUCCESSFUL"
//     },
//     "demandDepositAccountDTO": {
//       "id": {
//         "displayValue": "xxxxxxxxxxxx0001",
//         "value": "NYCTEAM100001"
//       },
//       "partyId": {
//         "displayValue": "***TEAM10",
//         "value": "NYCTEAM10"
//       },
//       "status": "ACTIVE",
//       "ddaAccountType": "Current Account",
//       "availableBalance": {
//         "currency": "USD",
//         "amount": 20018172.21
//       },
//       "currentBalance": {
//         "currency": "USD",
//         "amount": 10018172.21
//       }
//     }
//   }