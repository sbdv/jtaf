package org.jtaf.website.security;

import java.util.Collection;
import java.util.List;

import org.jtaf.website.security.client.AuthenticationRole;
import org.jtaf.website.server.domain.entities.UserProfile;
import org.jtaf.website.server.services.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

@Component
public class JtafAuthenticationManager implements AuthenticationManager {

    @Autowired
    private ProfileServices profileServices;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserProfile userProfile = null;
        try {
            userProfile = profileServices.userProfileInformation(authentication.getPrincipal().toString());
        }
        catch (ClientHandlerException e) {
            throw new JtafAuthenticationException(e.getMessage(), e);
        }
        catch (UniformInterfaceException ue) {
            throw new JtafAuthenticationException(ue.getMessage(), ue);
        }

        userProfile = profileServices.createOrUpdateUserProfile(userProfile);

        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userProfile.getPseudo(),
                userProfile.getPseudo(), getAuthorities());
        user.setDetails(userProfile);
        return user;
    }

    /**
     * La collection des autorisations d'un utilisateur.
     * 
     * @param Integer
     *            représentant le niveau d'accréditation d'un utilisateur
     * 
     * @return Collection représentant la liste des droits d'un utilisateur
     **/
    public Collection<GrantedAuthority> getAuthorities() {
        // pour le moment un utilisateur connecté à le rôle ROLE_USER
        List<GrantedAuthority> authList = Lists.newArrayList();
        authList.add(new SimpleGrantedAuthority(AuthenticationRole.USER.getRole()));
        return authList;
    }

    protected void setProfileServices(ProfileServices profileServices) {
        this.profileServices = profileServices;
    }

}
