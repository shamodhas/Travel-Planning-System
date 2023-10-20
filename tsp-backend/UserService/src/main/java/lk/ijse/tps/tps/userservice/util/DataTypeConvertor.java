package lk.ijse.tps.tps.userservice.util;

import lk.ijse.tps.tps.userservice.dto.CustomerDTO;
import lk.ijse.tps.tps.userservice.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 1:00 AM
 */

@Component
@RequiredArgsConstructor
public class DataTypeConvertor {
    private final ModelMapper modelMapper;

    public CustomerDTO getCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        customerDTO.setProfile(Base64.getDecoder().decode(customer.getProfile()));
        return customerDTO;
    }

    public Customer getCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setProfile(Base64.getEncoder().encodeToString(customerDTO.getProfile()));
        return customer;
    }
}
