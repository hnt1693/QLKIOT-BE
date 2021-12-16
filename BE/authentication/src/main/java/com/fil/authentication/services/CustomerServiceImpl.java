package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.constants.Messages;
import com.fil.authentication.enums.ROLE_TYPE;
import com.fil.authentication.models.Account;
import com.fil.authentication.models.Customer;
import com.fil.authentication.payload.dto.CustomerPayload;
import com.fil.authentication.repository.AccountRepository;
import com.fil.authentication.repository.CustomerRepository;
import com.fil.authentication.repository.GroupRepository;
import com.fil.authentication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AccountService accountService;

    @Override
    public ResponseAPI getAll(String searchData, String sortData, String fields, Integer page, Integer pageSize) throws Exception {
        return new ResponseAPI(Messages.getSuccess("khách hàng"), customerRepository.findAll());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ResponseAPI create(CustomerPayload postData) throws Exception, AccessDeniedException {
        try {
            Customer customer = new Customer();
            customer.setAddress(postData.getAddress());
            customer.setName(postData.getCustomerName());
            customer.setPhoneNumber(postData.getPhone());
            customer.setActivated(true);
            customer.setExpiredTime(LocalDateTime.now().plusDays(15));
            customer = customerRepository.save(customer);
//            Role role = roleRepository.findByRole("ROLE_ADMIN");
//            if (null == role) role = Utils.getRoleAdmin();
//            roleRepository.save(role);
//            Group group = Utils.getGroupAdmin(customer, role);
//            group = groupRepository.save(group);
            Account account = new Account();
            account.setPassword(passwordEncoder.encode(postData.getPassword()));
            account.setEmail(postData.getEmail());
            account.setUsername(postData.getUsername());
//            account.getGroups().add(group);
            account.setCustomer(customer);
            account.setRoleType(ROLE_TYPE.ADMIN);
            account.setActived(true);
            accountRepository.save(account);
            return new ResponseAPI(Messages.updateSuccess("khách hàng"), customer);
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public ResponseAPI create(Customer postData) throws Exception {
        return null;
    }

    @Override
    public ResponseAPI put(Customer putData) throws Exception, AccessDeniedException {
        customerRepository.save(putData);
        return new ResponseAPI(Messages.updateSuccess("khách hàng"), putData);
    }

    @Override
    public ResponseAPI findById(Long id) throws Exception, AccessDeniedException {
        Customer company = customerRepository.findById(id).orElseThrow(() -> new Exception(Messages.notFound("khách hàng")));
        return new ResponseAPI(Messages.getSuccess("khách hàng"), company);
    }

    @Override
    public ResponseAPI deletes(List<Long> ids) throws Exception, AccessDeniedException {
        try {
            customerRepository.deletes(ids);
            return new ResponseAPI(Messages.deleteSuccess("khách hàng"), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseAPI(Messages.deleteFailed("khách hàng"), null);
        }
    }


}
