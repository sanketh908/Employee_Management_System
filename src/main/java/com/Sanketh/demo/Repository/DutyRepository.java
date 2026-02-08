package com.Sanketh.demo.Repository;

import com.Sanketh.demo.Entity.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepository extends JpaRepository<Duty,Long> {
}
