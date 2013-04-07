package org.jtaf.website.server.app.domain.repository;

import java.math.BigInteger;

import org.jtaf.website.server.app.domain.entities.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, BigInteger> {

}
