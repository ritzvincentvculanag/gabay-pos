package me.jhayzonalbay.rmmcgabay.models;

public class PurchasedItem {

    public static final String PRODUCT_ID = "product_id";
    public static final String TRANSACTION_ID = "transaction_id";

    private int productId;
    private int transactionId;

    public PurchasedItem() {
    }

    public PurchasedItem(int productId, int transactionId) {
        this.productId = productId;
        this.transactionId = transactionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

}
