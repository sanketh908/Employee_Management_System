package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.*;
import com.Sanketh.EmployeeManagementSystem.Repository.AdminRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.EmailRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ManagerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AdminService {
    private final AdminRepository adminRepository;
    private final  ManagerRepository managerRepository;
    private  final EmailService emailService;
    private  final EmployeeRepository employeeRepository;
    public AdminService(AdminRepository adminRepository, ManagerRepository managerRepository, EmailService emailService, EmployeeRepository employeeRepository) {
        this.adminRepository = adminRepository;
        this.managerRepository = managerRepository;
        this.emailService = emailService;
        this.employeeRepository = employeeRepository;
    }


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
    public List<Manager> getAllManagers(){
        return managerRepository.findAll();
    }
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
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }
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
    public long managerCount()
    {
        return managerRepository.count();
    }
    public long employeeCount()
    {
        return employeeRepository.count();
    }

     public String assigndutyToManager(Duty duty,int managerId)
     {

     }
//    public String assigndutyToEmployee(Employee employee,int managerId);
//     public List<Leave>   getAllLeavesApplication();
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
