package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.audit.AuditAwareImpl;
import com.eazybytes.accounts.entity.Accounts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(AuditAwareImpl.class)
class AccountsRepositoryTest {

    @Autowired
    private AccountsRepository accountsRepository;

    @Test
    void AccountsRepository_save_ReturnsSavedAccount(){

        Accounts account = Accounts.builder()
                    .customerId(4L)
                    .accountNumber(9177810598L)
                    .branchAddress("Bangalore")
                    .accountType("Current")
                    .build();
//        setCustomerId(1L);
//        account.setAccountNumber(3456123456L);
//        account.setAccountType("Savings");
//        account.setBranchAddress("Main Branch");

        Accounts saved = accountsRepository.save(account);

        Assertions.assertThat(saved.getCustomerId()).isGreaterThan(1L);
    }

    @Test
    public void AccountsRepository_findAll_ReturnsAllAccounts(){
        Accounts account1 = Accounts.builder()
                .customerId(5L)
                .accountNumber(9177810599L)
                .branchAddress("Mumbai")
                .accountType("Savings")
                .build();

        Accounts account2 = Accounts.builder()
                .customerId(6L)
                .accountNumber(9177810600L)
                .branchAddress("Delhi")
                .accountType("Current")
                .build();

        accountsRepository.save(account1);
        accountsRepository.save(account2);

        var accountsList = accountsRepository.findAll();

        Assertions.assertThat(accountsList).isNotNull();
        Assertions.assertThat(accountsList.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    void AccountsRepository_findByCustomerId_ReturnsAccountsWithIdGiven() {

        Accounts account1 = Accounts.builder()
                .customerId(5L)
                .accountNumber(9177810599L)
                .branchAddress("Mumbai")
                .accountType("Savings")
                .build();

        Accounts saved = accountsRepository.save(account1);

        Optional<Accounts> accounts = accountsRepository.findByCustomerId(5L);

//        accounts.ifPresent(value -> Assertions.assertThat(value.getCustomerId()).isEqualTo(5L));
        Assertions.assertThat(accounts).isNotNull();
        Assertions.assertThat(accounts).isPresent();

    }

    @Test
    public void findAllByAccountType_FindsAllAccountsWithType(){

        //Arrange
        Accounts account1 = Accounts.builder()
                .customerId(1L)
                .accountNumber(9177810599L)
                .branchAddress("Mumbai")
                .accountType("Savings")
                .build();

        Accounts account2 = Accounts.builder()
                .customerId(2L)
                .accountNumber(9177810594L)
                .branchAddress("Mumbai")
                .accountType("Savings")
                .build();

        Accounts account3 = Accounts.builder()
                .customerId(3L)
                .accountNumber(9177810597L)
                .branchAddress("Mumbai")
                .accountType("Current")
                .build();

        accountsRepository.saveAll(List.of(account1, account2, account3));

        //Act
        List<Accounts> accounts = accountsRepository.findAllByAccountType("Savings");

        //Assert
        Assertions.assertThat(accounts).isNotNull();
        for(Accounts acc : accounts.stream().toList()){
            Assertions.assertThat(acc.getAccountType()).isEqualTo("Savings");
        }
    }

    @Test
    void AccountsRepository_deleteByCustomerId_DeletesAccounts() {

        //Arrange
        Accounts account1 = Accounts.builder()
                .customerId(1L)
                .accountNumber(9177810599L)
                .branchAddress("Mumbai")
                .accountType("Savings")
                .build();

        accountsRepository.save(account1);

        //Act
        accountsRepository.deleteByCustomerId(1L);
        Optional<Accounts> accounts = accountsRepository.findByCustomerId(1L);

        //Assert
        Assertions.assertThat(accounts).isEmpty();
    }
}