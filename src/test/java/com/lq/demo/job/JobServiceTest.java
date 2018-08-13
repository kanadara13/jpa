package com.lq.demo.job;

import com.lq.demo.DemoApplication;
import com.lq.demo.entity.Order;
import com.lq.demo.entity.Team;
import com.lq.demo.entity.User;
import com.lq.demo.service.IntegrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by user81 on 2017-01-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JobServiceTest {

    @Autowired
    UserRepository  repository;

    @Autowired
    TeamRepository  repository2;

    @Autowired
    OrderRepository  repository3;

    @Autowired
    IntegrationService service;

    @Test
    public void test() {
        User user = new User();
        user.setName("rafael");
        user.setAge(33);

        User saveUser = repository.save(user);

        assertEquals("gemini", saveUser.getName());
        assertEquals(26, saveUser.getAge());
    }

    @Test
    @Transactional
    public void teamAdd() {
        Team team = new Team();
        team.setName("개발3팀");
        team.setNickName("B2C팀");
        try {
            Team saveTeam = repository2.save(team);
            assertEquals("개발3팀", saveTeam.getName());
            assertEquals("B2C팀", saveTeam.getNickName());
            throw new RuntimeException();
        } catch (RuntimeException e){
            System.out.println("run time Exception");
        }
    }


    @Test
    public void selectTest() {
        User user = repository.findOne(1L);
        System.out.println(user.getName());
        //assertEquals("sjh", user.getName());
        //assertEquals(26, user.getAge());
    }

    @Test
    public void selectJoinTest() {
        User user = repository.findOne(1L);
        /*assertEquals("sjh", user.getName());
        assertEquals(26, user.getAge());*/
        System.out.println(user.getTeam().getNickName());
    }

    @Test
    public void 테스트ID수동생성(){
        try {
            Order order =  new Order();
            order.setId(12L);
            repository3.save(order);
        } catch (RuntimeException e){
            System.out.println("run time Exception");
        }

    }

    @Test
    public void 인서트쿼리테스트ID직접생성(){
        service.generateOrder();
    }

    @Test
    public void 인서트쿼리테스트ID생성DB의존전략(){
        service.generateUser();
    }

    @Test
    public void 인서트쿼리직접ID생성전략시selectID쿼리는플러쉬인가(){
        service.generateOrderAndOrder();
    }

    @Test
    public void 인서트쿼리직접ID생성전략후자동생성전략쿼리는플러쉬인가(){
        service.generateOrderAndUser();
    }
}