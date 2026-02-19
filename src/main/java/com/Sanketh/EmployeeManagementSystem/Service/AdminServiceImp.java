package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.*;
import com.Sanketh.EmployeeManagementSystem.Repository.AdminRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.LeaveRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
@Service
public class AdminServiceImp implements AdminService {
    private final AdminRepository adminRepository;
    private final  ManagerRepository managerRepository;
    private  final EmailService emailService;
    private  final EmployeeRepository employeeRepository;
    private final LeaveRepository leaveRepository;
    public AdminServiceImp(AdminRepository adminRepository, ManagerRepository managerRepository, EmailService emailService, EmployeeRepository employeeRepository, LeaveRepository leaveRepository) {
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
        int managerId = generateRandomManagerId();
        manager.setId(managerId);
        String randomPassword = geneateRandomPassword(8);
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
    public List<Manager> getAllManagers(){
        return managerRepository.findAll();
    }
    @Override
    public String deleteManager(int id)
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
    public String deleteEmployee(int id)
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
    public String assigndutyToManager(Duty duty, int managerId) {
        return "";
    }




    @Override
     public List<Leave>getAllLeavesApplication()
     {
         return leaveRepository.findAll();
     }

    @Override
    public String assigndutyToEmployee(Employee employee, int managerId) {
        return "";
    }

    private int generateRandomManagerId()
    {
        Random random = new Random();
        return random.nextInt(1000,9999);
    }
    public String geneateRandomPassword(int lenght)
    {
      String upper ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String lower="abcdefghijklmnopqrstuvwxyz";
      String number="0123456789";
      String spchar="!@#$%^&*()_+";
      String combin=upper+lower+number+spchar;
      StringBuilder stringBuilder=new StringBuilder();
      Random random=new Random();
      stringBuilder.append(upper.charAt(random.nextInt(upper.length())));
      stringBuilder.append(lower.charAt(random.nextInt(lower.length())));
      stringBuilder.append(number.charAt(random.nextInt(number.length())));
      stringBuilder.append(spchar.charAt(random.nextInt(spchar.length())));
      for(int i=4;i<lenght;i++)
      {
          stringBuilder.append(combin.charAt(random.nextInt(combin.length())));
      }
      return stringBuilder.toString();
    }
}
