package com.study.security.config;

import com.study.security.db.TemporaryDatabase;
import com.study.security.model.entity.Member;
import com.study.security.model.entity.enm.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private TemporaryDatabase temporaryDatabase;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = temporaryDatabase.findByMemberId(memberId);
        if(member == null) throw new UsernameNotFoundException(memberId);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        if (memberId.equals("user1")) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }

        return new User(member.getId(), member.getPassword(), grantedAuthorities);
    }

    public Member authenticateByEmailAndPassword(String id, String password){
        Member member = temporaryDatabase.findByMemberId(id);
        if(member == null) throw new UsernameNotFoundException(id);

        // password check
/*        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException("Password not matched");
        }*/

        return member;
    }
}
