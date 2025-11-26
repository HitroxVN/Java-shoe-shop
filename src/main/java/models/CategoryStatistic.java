package models;

public class CategoryStatistic {
    private int id;
    private String name;
    private int totalSold;

    public CategoryStatistic() {
    }

    public CategoryStatistic(int id, String name, int totalSold) {
        this.id = id;
        this.name = name;
        this.totalSold = totalSold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    @Override
    public String toString() {
        return "CategoryStatistic [id=" + id + ", name=" + name + ", totalSold=" + totalSold + "]";
    }
}
