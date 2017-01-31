package com.lq.demo.job;

import com.lq.demo.entity.Team;
import com.lq.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user81 on 2017-01-31.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
