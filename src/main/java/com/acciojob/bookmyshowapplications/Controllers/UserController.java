package com.acciojob.bookmyshowapplications.Controllers;

import com.acciojob.bookmyshowapplications.Models.UserInfo;
import com.acciojob.bookmyshowapplications.Requests.AuthRequest;
import com.acciojob.bookmyshowapplications.Service.JwtService;
import com.acciojob.bookmyshowapplications.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserInfo userInfo){

        String response = userService.addUser(userInfo);
        return response;

    }

    @GetMapping("/getUser/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getUser(@PathVariable("userId") Integer userId)
    {
        return userService.getUser(userId);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }

}
