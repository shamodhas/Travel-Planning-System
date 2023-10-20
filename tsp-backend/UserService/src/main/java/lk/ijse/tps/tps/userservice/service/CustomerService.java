package lk.ijse.tps.tps.userservice.service;

import lk.ijse.tps.tps.userservice.dto.CustomerDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/20/2023
 * Time : 4:47 PM
 */
public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO getSelectedCustomer(String customerId);
    CustomerDTO getSelectedCustomerByUserName(String userName);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(String customerId);
    List<CustomerDTO> getAllCustomer();
}
