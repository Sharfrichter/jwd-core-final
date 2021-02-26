package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {
    private Long id;
    private static Long counter=0L;
    private String name;

    public AbstractBaseEntity() {
        this.id = counter;
        counter++;
    }

    @Override
    public Long getId() {
        // todo
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        // todo
        return name;
    }
}
