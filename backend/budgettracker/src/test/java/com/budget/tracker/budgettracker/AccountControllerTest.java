package com.budget.tracker.budgettracker;

import com.budget.tracker.budgettracker.controllers.AccountController;
import com.budget.tracker.budgettracker.dto.CreateAccountRequest;
import com.budget.tracker.budgettracker.persistance.model.Account;
import com.budget.tracker.budgettracker.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AccountService accountService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testCreateAccount() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest(1L, "Checking", new BigDecimal("500.00"));
        Account createdAccount = new Account(1L, "Checking", new BigDecimal("500.00"));
        createdAccount.setAccountId(1L);
        createdAccount.setCreatedAt(LocalDateTime.now());
        
        when(accountService.createAccount(any(CreateAccountRequest.class))).thenReturn(createdAccount);

        mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountId").value(1))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Checking"))
                .andExpect(jsonPath("$.balance").value(500.00));
    }
    
    @Test
    void testCreateAccountWithInvalidData() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest(null, "", new BigDecimal("-100.00"));
        
        mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}