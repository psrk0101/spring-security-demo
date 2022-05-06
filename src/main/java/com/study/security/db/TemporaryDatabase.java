package com.study.security.db;

import com.study.security.model.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TemporaryDatabase {
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers(){
        return members;
    }

    public void saveMembers(Member member){
        members.add(member);
    }

    public Member findByMemberId(String memberId){
        for(Member m : members){
            m.getId().equals(memberId);
            return m;
        }
        return null;
    }

}
