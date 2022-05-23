export default class Invoice {
    constructor(userId, amount, type, status,reference, dueDate, account) {
        this.userId = userId;
        this.status = status;
        this.type = type;
        this.amount = amount;
        this.reference = reference;
        this.dueDate = dueDate;
        this.account = account;
    }
}