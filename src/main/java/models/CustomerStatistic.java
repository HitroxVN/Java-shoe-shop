package models;



public class CustomerStatistic {
    private int id;
    private String fullName;
    private int totalOrders;
    private double totalSpent;


    public CustomerStatistic() {
    }

   
    public CustomerStatistic(int id, String fullName, int totalOrders, double totalSpent) {
        this.id = id;
        this.fullName = fullName;
        this.totalOrders = totalOrders;
        this.totalSpent = totalSpent;
    }

   
    
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public double getTotalSpent() {
        return totalSpent;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }
    
   
    
    @Override
    public String toString() {
        return String.format("StatisticKhachHang [id=%d, name=%s, total_orders=%d, total_spent=%.2f]",
                id, fullName, totalOrders, totalSpent);
    }
}