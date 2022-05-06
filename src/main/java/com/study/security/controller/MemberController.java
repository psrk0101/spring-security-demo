package com.study.security.controller;


import com.study.security.db.TemporaryDatabase;
import com.study.security.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final TemporaryDatabase temporaryDatabase;

    @PostConstruct
    private void initUser(){
        temporaryDatabase.saveMembers(new Member("user1", passwordEncoder.encode("user1")));
        temporaryDatabase.saveMembers(new Member("user2", passwordEncoder.encode("user2")));
        temporaryDatabase.saveMembers(new Member("user3", passwordEncoder.encode("user3")));
    }

    @GetMapping("/api/member")
    public ResponseEntity<Member> getMember(@RequestParam String id){
        return new ResponseEntity<>(temporaryDatabase.findByMemberId(id), HttpStatus.OK);
    }

    @PostMapping("/api/member")
    public String saveMember(Map<String, Object> model){
        return "Success";
    }

    @GetMapping("/main")
    public String mainPage(Map<String, Object> model) {
        List<Member> members = temporaryDatabase.getMembers();
        model.put("members", members);
        return "homepage";
    }

    @GetMapping("/admin")
    public String adminPage(Map<String, Object> model) {
        return "adminpage";
    }

    @GetMapping("/member/new")
    public String memberJoinForm(Member memberForm) {
        return "/memberJoinForm.html";
    }

    @PostMapping("/member/new")
    public String memberJoin(Member memberForm) {
        // password를 암호화해서 저장
        memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        temporaryDatabase.saveMembers(memberForm);
        return "redirect:/main";
    }
}