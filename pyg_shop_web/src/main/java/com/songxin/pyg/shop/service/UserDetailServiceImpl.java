package com.songxin.pyg.shop.service;

import com.songxin.pyg.pojo.TbSeller;
import com.songxin.pyg.seller.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * @author fishsx
 * @date 2018/12/10 下午3:11
 */
public class UserDetailServiceImpl implements UserDetailsService {

    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String sellerId) throws UsernameNotFoundException {
        if (!StringUtils.isEmpty(sellerId)) {
            ArrayList<GrantedAuthority> auths = new ArrayList<>();
            auths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            TbSeller seller = sellerService.findOneById(sellerId);
            return new User(sellerId,seller.getPassword(),auths);
        }
        return null;

    }
}
