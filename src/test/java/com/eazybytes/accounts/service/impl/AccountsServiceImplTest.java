package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountsServiceImplTest {

    @Mock
    private AccountsRepository accountsRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private AccountsServiceImpl accountsService;

    Customer customer;
    Accounts accounts;
    CustomerDto customerDto;

    @Test
    void AccountsService_createAccount_CreatesNewAccount() {

        // Arrange
        when(customerRepository.findByMobileNumber(customerDto.getMobileNumber()))
                .thenReturn(Optional.empty());

        Customer savedCustomer = Customer.builder()
                .customerId(1L)
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber())
                .build();

        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(savedCustomer);

        ArgumentCaptor<Accounts> accountsCaptor = ArgumentCaptor.forClass(Accounts.class);
        when(accountsRepository.save(accountsCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        accountsService.createAccount(customerDto);

        // Assert
        verify(customerRepository).findByMobileNumber(customerDto.getMobileNumber());
        verify(customerRepository).save(any(Customer.class));
        verify(accountsRepository).save(any(Accounts.class));

        Accounts savedAccount = accountsCaptor.getValue();
        assertNotNull(savedAccount, "Saved account should not be null");
        assertEquals(savedCustomer.getCustomerId(), savedAccount.getCustomerId(), "CustomerId should be set on the account");
        assertEquals("Savings", savedAccount.getAccountType(), "Account type should be Savings");
        assertEquals("123 Main Street, New York", savedAccount.getBranchAddress(), "Branch address should match constant");
        assertNotNull(savedAccount.getAccountNumber(), "Account number should be generated");
    }

    @Test
    void fetchAccount() {
    }

    @Test
    void updateAccount() {
    }

    @Test
    void deleteAccount() {
    }

    @BeforeEach
    void setUp() {
        System.out.println("Initial setup");
        customerDto = new CustomerDto();
        customerDto.setName("Customer1");
        customerDto.setEmail("cus1@customer");
        customerDto.setMobileNumber("9178105897");

        customer = Customer.builder()
                .customerId(1L)
                .email("cus1@customer")
                .mobileNumber("9178105897")
                .name("Customer1")
                .build();

        accounts = Accounts.builder()
                .accountType("Savings")
                .accountNumber(1236759875L)
                .branchAddress("Bangalore")
                .customerId(1L)
                .build();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After test");
    }

    @Test
    void testCreateAccount() {
    }

    @Test
    void testFetchAccount() {
    }

    @Test
    void testUpdateAccount() {
    }

    @Test
    void testDeleteAccount() {
    }
}