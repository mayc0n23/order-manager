package io.github.mayc0n23.ordermanager.facade;

import io.github.mayc0n23.ordermanager.converter.TxtLineToLineObjectConverter;
import io.github.mayc0n23.ordermanager.model.domain.LineObject;
import io.github.mayc0n23.ordermanager.model.domain.Order;
import io.github.mayc0n23.ordermanager.model.domain.Product;
import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.service.OrderService;
import io.github.mayc0n23.ordermanager.service.ProductService;
import io.github.mayc0n23.ordermanager.service.UserService;
import io.github.mayc0n23.ordermanager.utils.file.ReadFile;
import io.github.mayc0n23.ordermanager.utils.file.ReadTxtFile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataFacade {

    private final ProductService productService;

    private final OrderService orderService;

    private final UserService userService;

    public DataFacade(ProductService productService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    public void upload(InputStream file) {
        final List<String> lines = readLines(file);
        final List<LineObject> lineObjects = convertToLineObject(lines);

        saveProducts(lineObjects);
        saveUsers(lineObjects);
        saveOrders(lineObjects);
    }

    private List<String> readLines(InputStream file) {
        final ReadFile readFile = new ReadTxtFile();
        return readFile.extractLinesFrom(file);
    }

    private List<LineObject> convertToLineObject(List<String> lines) {
        final var lineObjectConverter = new TxtLineToLineObjectConverter();
        return lineObjectConverter.convert(lines);
    }

    Set<Product> extractProducts(List<LineObject> lineObjects) {
        return lineObjects.stream()
                .map(line -> new Product(line.getProductId(), line.getProductPrice()))
                .collect(Collectors.toSet());
    }

    Set<Order> extractOrders(List<LineObject> lineObjects) {
        Set<Order> orders = new HashSet<>();

        final Map<Long, List<LineObject>> lineOrdersMap = lineObjects.stream()
                .collect(Collectors.groupingBy(LineObject::getOrderId));

        for (Map.Entry<Long, List<LineObject>> entry: lineOrdersMap.entrySet()) {
            Set<Product> orderProducts = extractProducts(entry.getValue());
            lineObjects.stream()
                    .filter(line -> line.getOrderId().equals(entry.getKey()))
                    .findFirst()
                    .ifPresent(lineOrder -> {
                        final Order order = new Order(
                                lineOrder.getOrderId(),
                                lineOrder.getOrderDate(),
                                new User(lineOrder.getUserId(), lineOrder.getUserName()),
                                orderProducts.stream().toList());
                        orders.add(order);
                    });
        }

        return orders;
    }

    Set<User> extractUsers(List<LineObject> lineObjects) {
        Set<User> users = new HashSet<>();

        final Map<Long, List<LineObject>> lineUsersMap = lineObjects.stream()
                .collect(Collectors.groupingBy(LineObject::getUserId));

        for (Map.Entry<Long, List<LineObject>> entry: lineUsersMap.entrySet()) {
            lineObjects.stream()
                    .filter(line -> line.getUserId().equals(entry.getKey()))
                    .findFirst()
                    .ifPresent(lineOrder -> {
                        User user = new User(lineOrder.getUserId(), lineOrder.getUserName());
                        users.add(user);
                    });
        }

        return users;
    }

    private void saveProducts(List<LineObject> lineObjects) {
        final var products = extractProducts(lineObjects);
        productService.saveProducts(products.stream().toList());
    }

    private void saveOrders(List<LineObject> lineObjects) {
        final var orders = extractOrders(lineObjects);
        orderService.saveOrders(orders.stream().toList());
    }

    private void saveUsers(List<LineObject> lineObjects) {
        final var users = extractUsers(lineObjects);
        userService.saveUsers(users.stream().toList());
    }

}