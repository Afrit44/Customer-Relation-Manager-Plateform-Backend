package com.example.crmbackend.Controller;

import com.example.crmbackend.DTO.auth.AuthenticationRequest;
import com.example.crmbackend.DTO.auth.AuthenticationResponse;
import com.example.crmbackend.Model.UserG;
import com.example.crmbackend.Repository.UserGRepository;
import com.example.crmbackend.Service.Authentication.ApplicationUserDetailsService;
import com.example.crmbackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    private UserGRepository userGRepository;


    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate( AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(request.getEmail());
        UserG userG = userGRepository.findUserGByEmail(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails) ;

        return ResponseEntity.ok(AuthenticationResponse.builder().jwtToken(jwt).role(userG.getUserRole().getRoleName()).build());
    }
}
