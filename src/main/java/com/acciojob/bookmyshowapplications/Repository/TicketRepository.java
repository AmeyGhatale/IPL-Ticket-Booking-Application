package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,String> {
    @Query(value = "select * from tickets where  user_info_user_id = :userId", nativeQuery = true)
    List<Ticket> findTicketByUserId(Integer userId);
}
