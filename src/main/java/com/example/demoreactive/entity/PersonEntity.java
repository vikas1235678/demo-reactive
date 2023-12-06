package com.example.demoreactive.entity;

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
public class PersonEntity implements Persistable<Integer> {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer age;

    @Transient
    private boolean newPerson;

    public PersonEntity(int testPersonId, String testPersonName, int testPersonAge) {
        this.setId(testPersonId);
        this.setName(testPersonName);
        this.setAge(testPersonAge);
    }

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
