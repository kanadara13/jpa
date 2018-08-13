package com.lq.demo.service;

import com.lq.demo.entity.Order;
import com.lq.demo.entity.User;
import com.lq.demo.job.OrderRepository;
import com.lq.demo.job.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IntegrationService {

    private OrderRepository repository;
    private UserRepository userRepository;

    @Autowired
    public IntegrationService(OrderRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void generateOrder() {
        for (long i=2; i < 5; i++){
            Order  order =  new Order();
            order.setId(i);
            order.setName("주문"+i);
            repository.save(order);
            if (i == 4){ throw new RuntimeException();}
        }
    }

    @Transactional
    public void generateUser(){
        for (long i=2; i < 5; i++){
            User user = new User();
            user.setName("사용자"+i);
            userRepository.save(user);
            if (i == 4){ throw new RuntimeException();}
        }
    }

    @Transactional
    public void generateOrderAndOrder() {
        for (long i=2; i < 5; i++){
            Order  order =  new Order();
            order.setId(i);
            order.setName("주문"+i);
            repository.save(order);
            //update 변경 감지로 flush 쿼리가 날라가는지?
            order.setName("주문update");
            repository.save(order);
            //if (i == 4){ throw new RuntimeException();}
        }

        for (long i=5; i < 10; i++){
            Order  order =  new Order();
            order.setId(i);
            order.setName("주문"+i);
            repository.save(order);
            //update 변경 감지로 flush 쿼리가 날라가는지?
            if (i == 9){ throw new RuntimeException();}
        }
       /* for (long i=2; i < 5; i++){
            User user = new User();
            user.setName("사용자"+i);
            userRepository.save(user);
        }*/
    }

    @Transactional
    public void generateOrderAndUser() {
        for (long i=2; i < 5; i++){
            Order  order =  new Order();
            order.setId(i);
            order.setName("주문"+i);
            repository.save(order);
            //update 변경 감지로 flush 쿼리가 날라가는지?
            order.setName("주문update");
            repository.save(order);
            //if (i == 4){ throw new RuntimeException();}
        }

        for (long i=2; i < 5; i++){
            User user = new User();
            user.setName("사용자"+i);
            userRepository.save(user);
            if (i == 4){ throw new RuntimeException();}
        }
    }
}
