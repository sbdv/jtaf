package org.jtaf.website.server.app.domain.entities;

import java.io.Serializable;
import java.math.BigInteger;

public abstract class AbstractEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7139459850841636177L;

    public abstract BigInteger getId();

    public abstract void setId(BigInteger id);

    public abstract int getVersion();

    public abstract void setVersion(int version);
}
