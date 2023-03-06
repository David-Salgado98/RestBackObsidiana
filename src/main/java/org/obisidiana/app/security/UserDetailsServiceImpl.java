package org.obisidiana.app.security;

import org.obisidiana.app.entity.Clientes;
import org.obisidiana.app.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientesRepository clientesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Clientes clientes =  clientesRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("USer not found"+username));
        return new UserDetailsImpl(clientes);
    }
}
