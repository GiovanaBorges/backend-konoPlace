package com.konoPlace.konoplace.security;

import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> optional = userRepository.findByEmail(email);
        optional.orElseThrow(() -> new UsernameNotFoundException(email + " not found."));

        return optional.map(UserDetailsImpl::new).get();
    }
}
