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
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
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
        user1.setCart("Cart");
        user1.setDriverLicence("Driver Licence");
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
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
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
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
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
    @Disabled("TODO: Complete this test")
    void testFindAllByUser3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.dao.InvalidDataAccessApiUsageException: org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing: com.example.autorent.entity.User; nested exception is java.lang.IllegalStateException: org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing: com.example.autorent.entity.User
        //       at jdk.proxy4.$Proxy151.findAllByUser(null)
        //       at com.example.autorent.service.OrderService.findAllByUser(OrderService.java:26)
        //   java.lang.IllegalStateException: org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing: com.example.autorent.entity.User
        //       at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:151)
        //       at org.hibernate.query.internal.AbstractProducedQuery.list(AbstractProducedQuery.java:1626)
        //       at org.hibernate.query.Query.getResultList(Query.java:165)
        //       at org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter.getResultList(CriteriaQueryTypeQueryAdapter.java:76)
        //       at jdk.proxy4.$Proxy160.getResultList(null)
        //       at jdk.proxy4.$Proxy151.findAllByUser(null)
        //       at com.example.autorent.service.OrderService.findAllByUser(OrderService.java:26)
        //   org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing: com.example.autorent.entity.User
        //       at org.hibernate.engine.internal.ForeignKeys.getEntityIdentifierIfNotUnsaved(ForeignKeys.java:347)
        //       at org.hibernate.type.EntityType.getIdentifier(EntityType.java:508)
        //       at org.hibernate.type.EntityType.nullSafeSet(EntityType.java:289)
        //       at org.hibernate.param.NamedParameterSpecification.bind(NamedParameterSpecification.java:53)
        //       at org.hibernate.loader.hql.QueryLoader.bindParameterValues(QueryLoader.java:682)
        //       at org.hibernate.loader.Loader.bindPreparedStatement(Loader.java:2150)
        //       at org.hibernate.loader.Loader.prepareQueryStatement(Loader.java:2127)
        //       at org.hibernate.loader.Loader.executeQueryStatement(Loader.java:2059)
        //       at org.hibernate.loader.Loader.executeQueryStatement(Loader.java:2037)
        //       at org.hibernate.loader.Loader.doQuery(Loader.java:956)
        //       at org.hibernate.loader.Loader.doQueryAndInitializeNonLazyCollections(Loader.java:357)
        //       at org.hibernate.loader.Loader.doList(Loader.java:2868)
        //       at org.hibernate.loader.Loader.doList(Loader.java:2850)
        //       at org.hibernate.loader.Loader.listIgnoreQueryCache(Loader.java:2682)
        //       at org.hibernate.loader.Loader.list(Loader.java:2677)
        //       at org.hibernate.loader.hql.QueryLoader.list(QueryLoader.java:540)
        //       at org.hibernate.hql.internal.ast.QueryTranslatorImpl.list(QueryTranslatorImpl.java:400)
        //       at org.hibernate.engine.query.spi.HQLQueryPlan.performList(HQLQueryPlan.java:219)
        //       at org.hibernate.internal.SessionImpl.list(SessionImpl.java:1459)
        //       at org.hibernate.query.internal.AbstractProducedQuery.doList(AbstractProducedQuery.java:1649)
        //       at org.hibernate.query.internal.AbstractProducedQuery.list(AbstractProducedQuery.java:1617)
        //       at org.hibernate.query.Query.getResultList(Query.java:165)
        //       at org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter.getResultList(CriteriaQueryTypeQueryAdapter.java:76)
        //       at jdk.proxy4.$Proxy160.getResultList(null)
        //       at jdk.proxy4.$Proxy151.findAllByUser(null)
        //       at com.example.autorent.service.OrderService.findAllByUser(OrderService.java:26)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setId(0);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setPicUrl("https://example.org/example");
        user.setRole(Role.USER);
        user.setSurname("Doe");
        user.setVerifyToken("ABC123");
        orderService.findAllByUser(user);
    }
}

