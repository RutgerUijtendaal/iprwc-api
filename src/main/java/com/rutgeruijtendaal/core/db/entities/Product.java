package com.rutgeruijtendaal.core.db.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="product")
@SecondaryTables(
        @SecondaryTable(
                name="product_info",
                pkJoinColumns = {@PrimaryKeyJoinColumn(name="product_id")}
        )
)
@NamedQueries({
        @NamedQuery(
                name="com.rutgeruijtendaal.core.db.entities.Product.getAll",
                query = "SELECT p FROM Product p"
        )
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int productId;

    @Column(name="supply", nullable = false)
    private int supply;

    @Column(name="is_archived", nullable = false)
    private boolean isArchived;

    @Column(name="tax_id", nullable = false)
    private int taxId;

    @Column(name="product_type_id", nullable = false)
    private int productTypeId;

    @Column(table="product_info", name="name", nullable = false)
    private String name;

    @Column(table="product_info", name="description", nullable = false)
    private String description;

    @Column(table="product_info", name="img_path", nullable = false)
    private String imagePath;

    @Column(table="product_info", name="price", nullable = false)
    private int price;

    private Product() {

    }

    public Product(int supply, boolean isArchived, int taxId, int productTypeId, String name, String description, String imagePath, int price) {
        this.supply = supply;
        this.isArchived = isArchived;
        this.taxId = taxId;
        this.productTypeId = productTypeId;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                supply == product.supply &&
                isArchived == product.isArchived &&
                taxId == product.taxId &&
                productTypeId == product.productTypeId &&
                price == product.price &&
                name.equals(product.name) &&
                description.equals(product.description) &&
                imagePath.equals(product.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, supply, isArchived, taxId, productTypeId, name, description, imagePath, price);
    }
}
