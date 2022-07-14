package com.example.auto_catalog.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "autos")
public class AutoEntity {
    @Id
    @Column(unique = true, name = "id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(unique = true, name = "plate_number", nullable = false)
    private String plateNumber;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "manufacturing_year", nullable = false)
    private Integer manufacturingYear;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AutoEntity that = (AutoEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
