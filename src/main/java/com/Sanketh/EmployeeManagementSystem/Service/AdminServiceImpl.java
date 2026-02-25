package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.*;
import com.Sanketh.EmployeeManagementSystem.Repository.*;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.GenaraateRandomId;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.RandomPasswordGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final  ManagerRepository managerRepository;
    private  final EmailService emailService;
    private  final EmployeeRepository employeeRepository;
    private final LeaveRepository leaveRepository;
    private final DutyRepository dutyRepository;
    public AdminServiceImpl(AdminRepository adminRepository, ManagerRepository managerRepository, EmailService emailService, EmployeeRepository employeeRepository, LeaveRepository leaveRepository, DutyRepository dutyRepository) {
        this.adminRepository = adminRepository;
        this.managerRepository = managerRepository;
        this.emailService = emailService;
        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
        this.dutyRepository = dutyRepository;
    }


    @Override
    public Admin checkAdminlogin(String username,String password)
    {
        Optional<Admin> admin= adminRepository.findAdminByUsernameAndPassword(username,password);
        if(admin.isPresent())
        {
            return admin.get();
        }
        else
        {
            return null;
        }
    }
    @Override
    public Manager addManager(Manager manager)
    {
        int managerId = GenaraateRandomId.generateRandomManagerId();
        manager.setId(managerId);
        String randomPassword = RandomPasswordGenerator.geneateRandomPassword(8);
        manager.setPassword(randomPassword);
        Manager savedmanager =managerRepository.save(manager);
        String subject="Welcome Manager to Employee Management System";
        String body="Hi "+manager.getUsername()+
                    "! Welcome to Employee Management System!\n\n"+
                    "you have been Successfully added to the EMC \n\n Manager Id :"+manager.getId()+
                    "\n\nUsername :"+manager.getUsername()+
                    "\n\nPassword :"+manager.getPassword();
        emailService.sendEmail(manager.getEmail(),subject,body);
        return savedmanager;



    }

    @Override
    public Manager checkManagerlogin(String username, String password) {
        return null;
    }

    @Override
    public List<Manager> viewAllManagers(){
        return managerRepository.findAll();
    }
    @Override
    public String deleteManager(Integer id)
    {
        Optional<Manager> manager=managerRepository.findById(id);
        if (manager.isPresent())
        {
            managerRepository.deleteById(id);
            return "Manager "+manager.get().getUsername()+" has been deleted";
        }
        else
        {
            return "Manager not found";
        }
    }
    @Override
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }
    @Override
    public String deleteEmployee(Integer id)
    {
        Optional<Employee> employee=employeeRepository.findById(id);
        if (employee.isPresent())
        {
            employeeRepository.deleteById(id);
            return "Employee "+employee.get().getUsername()+" has been deleted";
        }
        else
        {
            return "Employee not found";
        }

    }
    @Override
    public long managerCount()
    {
        return managerRepository.count();
    }
    @Override
    public long employeeCount()
    {
        return employeeRepository.count();
    }

    @Override
    public Duty assigndutyToManager(Duty duty, Integer managerId) {
      duty.setManager(managerRepository.findById(managerId).orElse(null));
        return dutyRepository.save(duty);
    }


    @Override
     public List<Leave>getAllLeavesApplication()
     {
         return leaveRepository.findAll();
     }

    @Override
    public Duty assigndutyToEmployee(Employee employee, Integer managerId) {
        return null;
    }


}
