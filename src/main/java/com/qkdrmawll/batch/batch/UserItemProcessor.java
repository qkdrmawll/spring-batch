package com.qkdrmawll.batch.batch;

import com.qkdrmawll.batch.user.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserItemProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(User user) throws Exception {
        user.setName(user.getName().toUpperCase());
        return user;
    }
}
