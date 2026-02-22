package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.*;
import com.Sanketh.EmployeeManagementSystem.Repository.AdminRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.LeaveRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ManagerRepository;
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
    public AdminServiceImpl(AdminRepository adminRepository, ManagerRepository managerRepository, EmailService emailService, EmployeeRepository employeeRepository, LeaveRepository leaveRepository) {
        this.adminRepository = adminRepository;
        this.managerRepository = managerRepository;
        this.emailService = emailService;
        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
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
    public String assigndutyToManager(Duty duty,Integer managerId) {
        return "";
    }




    @Override
     public List<Leave>getAllLeavesApplication()
     {
         return leaveRepository.findAll();
     }

    @Override
    public String assigndutyToEmployee(Employee employee,Integer managerId) {
        return "";
    }


}
