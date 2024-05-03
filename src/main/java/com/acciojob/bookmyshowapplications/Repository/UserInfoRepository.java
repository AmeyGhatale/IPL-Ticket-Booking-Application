package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    Optional<UserInfo> findByName(String username);

    @Query(value = "select * from user where email_id = :emailId", nativeQuery = true)
    Optional<UserInfo> findByEmail(String emailId);

    public UserInfo findUserByMobNo(String mobNo);
}
