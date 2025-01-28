package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Utils.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByEmail(String email);

    @Query("select a from AppUser a where a.role = 'AGENCY_MANAGER' ")
    List<AppUser> getAllUsersForAgencies();
}
