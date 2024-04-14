package io.github.mayc0n23.ordermanager.controller;

import io.github.mayc0n23.ordermanager.model.entity.OrderEntity;
import io.github.mayc0n23.ordermanager.model.entity.ProductEntity;
import io.github.mayc0n23.ordermanager.model.entity.UserEntity;
import io.github.mayc0n23.ordermanager.repository.OrderRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.END_DATE_REQUIRED_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_DATE_FORMAT_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_DATE_RANGE_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.START_DATE_REQUIRED_MESSAGE;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OrderDataControllerTest {

    private static final String PATH = "/orders";

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindOrderByIdWhenOrderExist() throws Exception {
        final UserEntity userEntity = new UserEntity(1L, "John Doe");
        final ProductEntity productEntity = new ProductEntity(1L, BigDecimal.TEN);
        final OrderEntity orderEntity = new OrderEntity(1L, LocalDate.of(2023, 10, 10), userEntity,
                Collections.singletonList(productEntity));
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(orderEntity));

        mockMvc.perform(get(PATH + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindOrderByIdWhenOrderNotExist() throws Exception {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get(PATH + "/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testFindOrdersWithoutDateRange() throws Exception {
        mockMvc.perform(get(PATH))
                .andExpect(status().isOk());
    }

    @Test
    void testFindOrdersWithDateRange() throws Exception {
        mockMvc.perform(get(PATH)
                        .param("start", "2021-01-01")
                        .param("end", "2021-01-31"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindOrdersWithOnlyStartDate() throws Exception {
        mockMvc.perform(get(PATH)
                        .param("start", "2021-01-01"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value(END_DATE_REQUIRED_MESSAGE));
    }

    @Test
    void testFindOrdersWithOnlyEndDate() throws Exception {
        mockMvc.perform(get(PATH)
                        .param("end", "2021-01-01"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value(START_DATE_REQUIRED_MESSAGE));
    }

    @Test
    void testFindOrdersWithInvalidDateRange() throws Exception {
        mockMvc.perform(get(PATH)
                        .param("start", "2021-01-31")
                        .param("end", "2021-01-01"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value(INVALID_DATE_RANGE_MESSAGE));
    }

    @Test
    void testFindOrdersWithStartDateInvalidFormat() throws Exception {
        mockMvc.perform(get(PATH)
                        .param("start", "2021-01-01T00:00:00")
                        .param("end", "2021-01-31"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value(INVALID_DATE_FORMAT_MESSAGE));
    }

    @Test
    void testFindOrdersWithEndDateInvalidFormat() throws Exception {
        mockMvc.perform(get(PATH)
                        .param("start", "2021-01-01")
                        .param("end", "2021-01-01T00:00:00"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value(INVALID_DATE_FORMAT_MESSAGE));
    }

}