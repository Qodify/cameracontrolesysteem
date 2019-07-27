package kdg.be.processor.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Property {
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Id
    private String property;
    @Column
    private String value;
    public Property() {
    }

    public Property(String s, String s2) {
        property= s;
        value=s2;
    }
}
