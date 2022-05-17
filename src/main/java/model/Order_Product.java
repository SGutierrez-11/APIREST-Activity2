package model;

public class Order_Product {

    private int orderId;
    private int productId;
    private int amountProduct;

    public Order_Product() {
    }

    public Order_Product(int orderId, int productId, int amountProduct) {
        this.orderId = orderId;
        this.productId = productId;
        this.amountProduct = amountProduct;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }
}
