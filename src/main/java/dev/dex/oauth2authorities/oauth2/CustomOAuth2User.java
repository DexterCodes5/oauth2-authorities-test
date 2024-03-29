package dev.dex.oauth2authorities.oauth2;

import org.springframework.security.core.*;
import org.springframework.security.oauth2.core.user.*;

import java.util.*;

public class CustomOAuth2User implements OAuth2User {
    private OAuth2User oauth2User;

    public CustomOAuth2User(OAuth2User oauth2User) {
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }
}
