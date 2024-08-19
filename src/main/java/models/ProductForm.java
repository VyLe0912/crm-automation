package models;

public class ProductForm {
    private String name;
    private String price;
    private String discount;

    //Constructor
    public ProductForm() {
    }

    public ProductForm(String name, String price, String discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    //Getter & Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
