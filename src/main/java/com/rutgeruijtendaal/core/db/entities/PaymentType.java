package com.rutgeruijtendaal.core.db.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="payment_type")
@NamedQueries({
        @NamedQuery(
                name ="com.rutgeruijtendaal.core.db.entities.PaymentType.getAll",
                query = "SELECT p FROM PaymentType p"
        )
})
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_type_id")
    private int paymentTypeId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="img_path", nullable = false)
    private String imgPath;

    public PaymentType() {

    }

    public PaymentType(String name, String description, String imgPath) {
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentType that = (PaymentType) o;
        return paymentTypeId == that.paymentTypeId &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                Objects.equals(imgPath, that.imgPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentTypeId, name, description, imgPath);
    }
}
