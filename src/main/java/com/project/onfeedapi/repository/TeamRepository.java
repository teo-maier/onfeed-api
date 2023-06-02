package com.project.onfeedapi.repository;

import com.project.onfeedapi.model.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT t.* " +
            "FROM teams t " +
            "JOIN team_members m ON t.id = m.team_id " +
            "WHERE employee_id = :managerId AND is_manager = 1",
            nativeQuery = true)
    List<Team> findTeamsByManager(@Param("managerId") Long managerId, Pageable pageable);

}
