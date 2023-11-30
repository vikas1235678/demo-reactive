package com.example.demoreactive.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("person2")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity implements Persistable<Integer> {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer age;

    @Transient
    private boolean newPerson;

    @Override
    @Transient
    public boolean isNew() {
        return this.newPerson || id == null;
    }

    public PersonEntity setAsNew(){
        this.newPerson = true;
        return this;
    }
}
