export class YodleeAccount{
    account :Account[];
}

export class Account{
    accountName: any;
    accountType: any;
    isManual: any;
    displayedName: any;
    accountNumber: any;
    rewardBalance : RewardBalance[];
    accountStatus: any;
    lastUpdated: any;
    isAsset: any;
    createdDate: any;
    aggregationSource: any;
    providerId: any;
    providerAccountId: any;
    CONTAINER: any;
    primaryRewardUnit: any;
    id: any;
    userClassification: any;
    dataset : Dataset[];
    providerName : any;
} 

export class RewardBalance{
    expiryDate: any;
    balanceType: any;
    balance: any;
    description: any;
    units: any;
    balanceToLevel: any;
}

export class Dataset{
    updateEligibility : any;
    lastUpdated: any;
    additionalStatus : any;
    nextUpdateScheduled : any;
    name :any;
    lastUpdateAttempt : any;
}