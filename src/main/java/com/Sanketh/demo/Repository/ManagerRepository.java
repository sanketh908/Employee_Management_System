package com.Sanketh.demo.Repository;

import com.Sanketh.demo.Entity.Admin;
import com.Sanketh.demo.Entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
}
