package edu.dwlx.services;

import edu.dwlx.entity.User;
import edu.dwlx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("自定义登陆控制");
        User user = userMapper.searchByName(username);
        if(user != null) {
            List<GrantedAuthority> authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            return new org.springframework.security.core.userdetails
                    .User(user.getUsername(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("" +
                "User "+ username+ "not found");
    }
}
