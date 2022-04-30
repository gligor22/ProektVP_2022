package com.example.proektvp2022.config;

import com.example.proektvp2022.model.*;
import com.example.proektvp2022.repository.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInit {

    public final EmployeeRepository employeeRepository;;
    public final JpaCategoryRepo categoryRepo;;
    public final JpaIncomeRepo incomeRepo;
    public final JpaManufacturerRepo jpaManufacturerRepo;;
    public final JpaMonthlyBillsRepo monthlyBillsRepo;;
    public final JpaPatientRepo patientRepo;;
    public final JpaProductRepo productRepo;;
    public final JpaServicesRepo servicesRepo;;
    public final SkillRepository skillRepository;;

    public DataInit(EmployeeRepository employeeRepository, JpaCategoryRepo categoryRepo,
                    JpaIncomeRepo incomeRepo, JpaManufacturerRepo jpaManufacturerRepo,
                    JpaMonthlyBillsRepo monthlyBillsRepo, JpaPatientRepo patientRepo,
                    JpaProductRepo productRepo, JpaServicesRepo servicesRepo, SkillRepository skillRepository) {
        this.employeeRepository = employeeRepository;
        this.categoryRepo = categoryRepo;
        this.incomeRepo = incomeRepo;
        this.jpaManufacturerRepo = jpaManufacturerRepo;
        this.monthlyBillsRepo = monthlyBillsRepo;
        this.patientRepo = patientRepo;
        this.productRepo = productRepo;
        this.servicesRepo = servicesRepo;
        this.skillRepository = skillRepository;
    }

    @PostConstruct
    public void init()
    {
//        Skill s = new Skill("Degree1");
//        List<Skill> skills = new ArrayList<>();
//        skills.add(s);
//        Employee e1 = new Employee("Gligor","gligor.petkov22@gmail.com",EmployeeType.DOCTOR,skills,LocalDate.now(),(float)1200.00);
//        Employee e2 = new Employee("David","david.petkov22@gmail.com",EmployeeType.DOCTOR,skills,LocalDate.now(),(float)1200.00);
//        skillRepository.save(s);
//        employeeRepository.save(e1);
//        employeeRepository.save(e2);
//        Category c = new Category("Injection","Intra-muscular");
//        categoryRepo.save(c);
//        Manufacturer m = new Manufacturer("Alkaloid","Bul Aleksandar Makedosnki 25 3/3");
//        jpaManufacturerRepo.save(m);
//        MonthlyBills bill = new MonthlyBills(LocalDate.now(),(float)1200,(float)1200,(float)1200,(float)1200,(float)1200);
//        monthlyBillsRepo.save(bill);
//        Random random = new Random();
//        Patient patient = new Patient("Zoran","Petkov","zoran@gmail.com",55,"22020014500"+random.nextInt(99));
//        patientRepo.save(patient);
//        Product product = new Product("Biobil",(double)5,4,c,m);
//        productRepo.save(product);
//        Services service = new Services(ServicesTypes.Infusions,(float)6,20);
//        servicesRepo.save(service);
//        List<Services> services = new ArrayList<>();
//        services.add(service);
//        List<Product> products = new ArrayList<>();
//        products.add(product);
//        Income income = new Income(services,products,patient);
//        incomeRepo.save(income);
    }
}
