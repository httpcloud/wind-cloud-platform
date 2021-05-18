package com.windacc.wind.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.windacc.wind.toolkit.entity.LoginUser;
import com.windacc.wind.toolkit.constants.SecurityConstant;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 20:18
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoginUserDetails extends LoginUser implements SocialUserDetails {

    private static final long serialVersionUID = 2660158478125641667L;

    @JsonIgnore
    @Override
    public String getUserId() {
        return getOpenId();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new HashSet<>();
        if (!CollectionUtils.isEmpty(super.getRoleCodes())) {
            super.getRoleCodes().parallelStream()
                .forEach(roleCode -> collections.add(new SimpleGrantedAuthority("ROLE_" + roleCode)));
        }
        return collections;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return !SecurityConstant.USER_STATUS_EXPIRED.equals(getStatus());
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !SecurityConstant.USER_STATUS_LOCK.equals(getStatus());
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return !SecurityConstant.USER_STATUS_ENABLED.equals(getStatus());
    }
}
