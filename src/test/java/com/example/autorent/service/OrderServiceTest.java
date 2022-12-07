package com.example.autorent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.autorent.entity.Car;
import com.example.autorent.entity.CategoryType;
import com.example.autorent.entity.City;
import com.example.autorent.entity.Mark;
import com.example.autorent.entity.Order;
import com.example.autorent.entity.PaymentType;
import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.repository.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles({"GULA-YEZ8-9WAK-7FCO"})
@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    /**
     * Method under test: {@link OrderService#save(Order)}
     */
    @Test
    void testSave() {
        Car car = new Car();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        car.setCarYear(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        car.setCategoryType(CategoryType.SEDAN);
        car.setCity(City.EREVAN);
        car.setDescription("The characteristics of someone or something");
        car.setId(1);
        car.setMark(Mark.BMW);
        car.setModel("Model");
        car.setPrice(10.0d);

        User user = new User();
        user.setCard("Cart");
        user.setDriverLicense("Driver Licence");
        user.setPhoneNumber("19872656");
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setPicUrl("https://example.org/example");
        user.setRole(Role.USER);
        user.setSurname("Doe");
        user.setVerifyToken("ABC123");

        Order order = new Order();
        order.setCar(car);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setCarEndDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setCarStartDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        order.setId(1);
        order.setPaymentType(PaymentType.ONLINE);
        order.setUser(user);
        when(orderRepository.save((Order) any())).thenReturn(order);

        Car car1 = new Car();
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        car1.setCarYear(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        car1.setCategoryType(CategoryType.SEDAN);
        car1.setCity(City.EREVAN);
        car1.setDescription("The characteristics of someone or something");
        car1.setId(1);
        car1.setMark(Mark.BMW);
        car1.setModel("Model");
        car1.setPrice(10.0d);

        User user1 = new User();
        user1.setCard("Cart");
        user1.setDriverLicense("Driver Licence");
        user1.setEmail("jane.doe@example.org");
        user1.setEnable(true);
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setPicUrl("https://example.org/example");
        user1.setRole(Role.USER);
        user1.setSurname("Doe");
        user1.setVerifyToken("ABC123");

        Order order1 = new Order();
        order1.setCar(car1);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        order1.setCarEndDate(fromResult);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant());
        order1.setCarStartDate(fromResult1);
        order1.setId(1);
        order1.setPaymentType(PaymentType.ONLINE);
        order1.setUser(user1);
        orderService.save(order1);
        verify(orderRepository).save((Order) any());
        assertEquals(car, order1.getCar());
        assertEquals(user, order1.getUser());
        assertSame(fromResult, order1.getCarEndDate());
        assertEquals(1, order1.getId());
        assertEquals(PaymentType.ONLINE, order1.getPaymentType());
        assertSame(fromResult1, order1.getCarStartDate());
    }

    /**
     * Method under test: {@link OrderService#findAll()}
     */
    @Test
    void testFindAll() {
        assertTrue(orderService.findAll().isEmpty());
    }

    /**
     * Method under test: {@link OrderService#findAllByUser(User)}
     */
    @Test
    void testFindAllByUser() {
        User user = new User();
        user.setCard("Cart");
        user.setDriverLicense("Driver Licence");
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setPicUrl("https://example.org/example");
        user.setRole(Role.USER);
        user.setSurname("Doe");
        user.setVerifyToken("ABC123");
        List<Order> actualFindAllByUserResult = orderService.findAllByUser(user);
        assertTrue(actualFindAllByUserResult.isEmpty());
        assertEquals(actualFindAllByUserResult, orderService.findAll());
    }

    /**
     * Method under test: {@link OrderService#findAllByUser(User)}
     */
    @Test
    void testFindAllByUser2() {
        User user = new User(1, "l.UlUlUlUlUl.U", "Doe", "jane.doe@example.org", "4105551212", "iloveyou",
                "l.UlUlUlUlUl.U", "l.UlUlUlUlUl.U", true, Role.USER, "https://example.org/example", "ABC123");
        user.setCard("Cart");
        user.setDriverLicense("Driver Licence");
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setPicUrl("https://example.org/example");
        user.setRole(Role.USER);
        user.setSurname("Doe");
        user.setVerifyToken("ABC123");
        List<Order> actualFindAllByUserResult = orderService.findAllByUser(user);
        assertTrue(actualFindAllByUserResult.isEmpty());
        assertEquals(actualFindAllByUserResult, orderService.findAll());
    }

}

