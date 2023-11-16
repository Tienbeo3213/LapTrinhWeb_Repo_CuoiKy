package Entity;

import jakarta.persistence.*;

@Entity
public class CartLine {
    @Id
    private Long id;
    @ManyToOne(fetch=FetchType.EAGER)
    private Product product;
    private int quantity;

    public CartLine() {
    }
    public CartLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Double getTotal() {
        return quantity * product.getProductCost();
    }



    public CartLine(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
