package io.github.mayc0n23.ordermanager.controller;

import io.github.mayc0n23.ordermanager.exception.ValidationFailedException;
import io.github.mayc0n23.ordermanager.facade.OrderDataFacade;
import io.github.mayc0n23.ordermanager.model.response.OrderDataResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.END_DATE_REQUIRED_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_DATE_FORMAT_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_DATE_RANGE_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.START_DATE_REQUIRED_MESSAGE;

@RestController
@RequestMapping("/orders")
public class OrderDataController implements OrderDataControllerOpenApi {

    private final OrderDataFacade facade;

    public OrderDataController(OrderDataFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/{id}")
    @Override
    public OrderDataResponse findOrderById(@PathVariable Long id) {
        return facade.findOrderById(id);
    }

    @GetMapping
    @Override
    public List<OrderDataResponse> findOrders(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end) {

        validateDateParams(start, end);
        return facade.findOrders(start, end);
    }

    private void validateDateParams(String start, String end) {
       if (start == null && end == null) {
           return ;
       }

       if (start != null && end == null) {
           throw new ValidationFailedException(END_DATE_REQUIRED_MESSAGE);
       }

       if (start == null && end != null) {
           throw new ValidationFailedException(START_DATE_REQUIRED_MESSAGE);
       }

       if (isInvalidDateFormat(start) || isInvalidDateFormat(end)) {
              throw new ValidationFailedException(INVALID_DATE_FORMAT_MESSAGE);
       }

       if (start.compareTo(end) > 0) {
           throw new ValidationFailedException(INVALID_DATE_RANGE_MESSAGE);
       }
    }

    private boolean isInvalidDateFormat(String date) {
        return !date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

}