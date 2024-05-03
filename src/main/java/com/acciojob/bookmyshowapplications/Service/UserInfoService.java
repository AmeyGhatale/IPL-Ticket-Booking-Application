package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Models.UserInfo;
import com.acciojob.bookmyshowapplications.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByEmail(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }


    public String addUser(UserInfo userInfo){

        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfo = repository.save(userInfo);


        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Welcome to IPL Ticket Booking Application");
        message.setFrom("springacciojob@gmail.com");
        message.setTo(userInfo.getEmailId());

        String body = "Hi "+ userInfo.getName()+"! "+"\n"+
                "Welcome to IPL Ticket Booking Application !! , Feel free " +
                "to browse the teams and matches and use Coupon START100 for an instant discount";

        message.setText(body);

        javaMailSender.send(message);


        return "The user has been saved to the DB with userId : "+ userInfo.getUserId();
    }


    public String getUser(Integer id)
    {
        UserInfo userInfo = repository.findById(id).get();
        return userInfo.toString();
    }

}
