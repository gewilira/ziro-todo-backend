package com.ziro.todo.configurations;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

 	    if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().toString().equals("anonymousUser")) {
 	    	return Optional.ofNullable("System");
 	    }

 	    return Optional.ofNullable(((User)authentication.getPrincipal()).getUsername());
    }

}