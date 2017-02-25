package com.pizzashop.services;

import com.pizzashop.models.Manager;
import com.pizzashop.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by bartek on 2/25/17.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String eMail) throws UsernameNotFoundException {
        Manager manager=managerRepository.findByeMail(eMail);
        if(manager==null)
            throw new UsernameNotFoundException("No customer present with e-mail: "+eMail);
        else
            return new CustomUserDetails(manager);
    }
}
