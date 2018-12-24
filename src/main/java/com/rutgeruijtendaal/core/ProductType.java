package com.rutgeruijtendaal.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="product_type")
@NamedQueries({
        @NamedQuery(
                name ="com.rutgeruijtendaal.core.ProductType.getAll",
                query = "SELECT p FROM ProductType p"
        )
})
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_type_id")
    private int productTypeId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;

    public ProductType() {

    }

    public ProductType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return productTypeId == that.productTypeId &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productTypeId, name, description);
    }
}
