    package com.Sanketh.EmployeeManagementSystem.Repository;

    import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
    import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
    import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface DutyRepository extends JpaRepository<Duty,Integer> {
        List<Duty> findByEmployee(Employee employee);
        List<Duty> findByManagerId(Integer managerId);
        List<Duty> findByEmployeeId(Integer employeeId);
        List<Duty> findByAssignedByAdminId(Integer adminId);
        List<Duty> findByAssignedByManagerId(Integer managerId);
    }
