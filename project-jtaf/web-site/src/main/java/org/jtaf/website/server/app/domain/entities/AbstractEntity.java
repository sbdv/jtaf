package org.jtaf.website.server.app.domain.entities;

public abstract class AbstractEntity {


    public abstract String getId();

    public abstract void setId(String id);

    public abstract int getVersion();

    public abstract void setVersion(int version);
}
