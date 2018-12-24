package com.rutgeruijtendaal.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tax_bracket")
@NamedQueries({
        @NamedQuery(
                name="com.rutgeruijtendaal.core.TaxBracket.getAll",
                query="SELECT p FROM TaxBracket p"
        )
})
public class TaxBracket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tax_id", nullable = false)
    private int taxId;

    @Column(name="tax_percentage", nullable = false)
    private int taxPercentage;

    @Column(name="description", nullable = false)
    private String description;

    public TaxBracket() {

    }

    public TaxBracket(int taxPercentage, String description) {
        this.taxPercentage = taxPercentage;
        this.description = description;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public int getTax_percentage() {
        return taxPercentage;
    }

    public void setTax_percentage(int tax_percentage) {
        this.taxPercentage = tax_percentage;
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
        TaxBracket that = (TaxBracket) o;
        return taxId == that.taxId &&
                taxPercentage == that.taxPercentage &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxId, taxPercentage, description);
    }
}
