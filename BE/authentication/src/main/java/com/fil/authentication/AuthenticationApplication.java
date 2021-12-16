package com.fil.authentication;

import com.fil.authentication.repository.AccountRepository;
import com.fil.authentication.repository.CustomerRepository;
import com.fil.authentication.repository.GroupRepository;
import com.fil.authentication.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableFeignClients
@EnableAuthorizationServer
@EnableEurekaClient
@SpringBootApplication
public class AuthenticationApplication implements CommandLineRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationApplication.class);
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository companyRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        LOGGER.info("KHỞI TẠO DỮ LIỆU");
//        LOGGER.info("TẠO COMPANY QuẢN TRỊ");
//        Customer company = initCompany();
//        LOGGER.info("TẠO ROLE QuẢN TRỊ");
//        Role role = initRole();
//        LOGGER.info("TẠO QuẢN TRỊ VIÊN");
//        Account account = initAccount(company);
//        LOGGER.info("TẠO NHÓM QuẢN TRỊ");
//        Group group = initGroup(company, role, account);

    }

//    public Customer initCompany() {
//        try {
//            Customer company = new Customer();
//            company.setId(0L);
//            company.setName("QUẢN TRỊ VIÊN");
//            company.setPhoneNumber("0915.455.131");
//            company.setAddress("");
//            company = companyRepository.save(company);
//            return company;
//        } catch (Exception e) {
//
//            return companyRepository.findByNameAndNguoiTao("QUẢN TRỊ VIÊN", "SUPER_ADMIN");
//        }
//
//    }

//    public Role initRole() {
//        try {
//            Role role = new Role();
//            role.setId(0L);
//            role.setRole("ROLE_SUPER_ADMIN");
//            role = roleRepository.save(role);
//            return role;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return roleRepository.findByRole("ROLE_SUPER_ADMIN");
//        }
//    }

//    public Group initGroup(Customer company, Role role, Account account) {
//        try {
//            Group group = new Group();
//            group.setId(0L);
//            group.setName("QUẢN TRỊ VIÊN");
//            group.setCustomer(company);
//            group.getRoles().add(role);
//            group.getAccounts().add(account);
//            group = groupRepository.save(group);
//            return group;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return groupRepository.findByNameAndNguoiTao("QUẢN TRỊ VIÊN", "SUPER_ADMIN");
//
//        }
//    }
//
//    public Account initAccount(Customer company) {
//        try {
//            Account ac = new Account();
//            ac.setId(0L);
//            ac.setUsername("SUPER_ADMIN");
//            ac.setPassword(encoder.encode("123123123"));
//            ac.setEmail("admin-sp@gmail.com");
//            ac.setActived(true);
//            ac.setCustomer(company);
//            return accountRepository.save(ac);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return accountRepository.findByUsername("SUPER_ADMIN").isPresent()?accountRepository.findByUsername("SUPER_ADMIN").get():null;
//        }
//    }


}
