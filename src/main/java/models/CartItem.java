package models;

public class CartItem {
    private int id;          // ID giỏ hàng
    private int productId;   // ID sản phẩm
    private String productName;
    private double price;
    private int quantity;
    private String image;

    public double getTotal() {
        return price * quantity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
