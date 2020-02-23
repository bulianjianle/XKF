package com.credit.departmentweb.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.pojo.Admin2;
import com.credit.service.IAdmin2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserService implements UserDetailsService {

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAdmin2 iAdmin2;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //构建角色列表
        List<GrantedAuthority> grantAuths = new ArrayList();
        grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        //得到对象
        Admin2 admin2=iAdmin2.findOneByUsername(username);
        if (admin2 != null) {//有这个用户则继续判断用户是否给通过了
            if(admin2.getStatus().equals(1)){
                return new User(username, admin2.getPassword(), grantAuths);
            }else{
                return null;
            }
        } else {
            return null;
        }
    }
}
