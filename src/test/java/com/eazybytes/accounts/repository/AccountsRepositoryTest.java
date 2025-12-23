package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.audit.AuditAwareImpl;
import com.eazybytes.accounts.entity.Accounts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(AuditAwareImpl.class)
class AccountsRepositoryTest {

    @Autowired
    private AccountsRepository accountsRepository;

    @Test
    void AccountsRepository_save_ReturnsSavedAccount(){

        Accounts account = new Accounts();
        account.setCustomerId(1L);
        account.setAccountNumber(3456123456L);
        account.setAccountType("Savings");
        account.setBranchAddress("Main Branch");

        Accounts saved = accountsRepository.save(account);

        assertNotNull(saved);
        assertNotNull(saved.getCustomerId(), "Saved account should have an id");

        Optional<Accounts> fetched = accountsRepository.findByCustomerId(1L);
        assertTrue(fetched.isPresent(), "Account should be found by customerId");
        assertEquals(1L, fetched.get().getCustomerId());
    }

    @Test
    void findByCustomerId() {
    }

    @Test
    void deleteByCustomerId() {
    }
}