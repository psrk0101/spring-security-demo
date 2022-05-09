package com.study.security.config;

import com.study.security.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;

    /*@PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final Member member = userDetailService.authenticateByEmailAndPassword(authenticationRequest.getId(), authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(member.getId());
        return ResponseEntity.ok(new JwtResponse(token));
    }*/

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final Member member = userDetailService.authenticateByEmailAndPassword(authenticationRequest.getId(), authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(member.getId());


        return ResponseEntity.ok().header("Authorization", token).body(new JwtResponse(token));
    }
}

@Data
class JwtRequest {
    private String id;
    private String password;
}

@Data
@AllArgsConstructor
class JwtResponse {
    private String token;
}