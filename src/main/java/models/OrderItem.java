package models;

public class OrderItem {
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;
    private double price;
    private String created_at;
    private String updated_at;

    public OrderItem(int id, int order_id, int product_id, int quantity, double price, String created_at, String updated_at) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getters và setters
    public int getId() { return id; }
    public int getOrder_id() { return order_id; }
    public int getProduct_id() { return product_id; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getCreated_at() { return created_at; }
    public String getUpdated_at() { return updated_at; }

    public void setId(int id) { this.id = id; }
    public void setOrder_id(int order_id) { this.order_id = order_id; }
    public void setProduct_id(int product_id) { this.product_id = product_id; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }
    public void setUpdated_at(String updated_at) { this.updated_at = updated_at; }
}
