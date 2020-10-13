package com.ziro.todo.services;

import com.ziro.todo.persistence.entities.User;
import com.ziro.todo.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName)
       .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
         getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {

        String[] userRoles = {user.getRole()};
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
