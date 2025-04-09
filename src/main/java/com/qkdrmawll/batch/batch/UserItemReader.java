package com.qkdrmawll.batch.batch;

import com.qkdrmawll.batch.user.User;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class UserItemReader implements ItemReader<User> {

    private Iterator<User> userIterator;

    public UserItemReader() {
        List<User> users = List.of(
                new User("Alice", "alice@email.com"),
                new User("Bob", "bob@email.com"),
                new User("Charlie", "charlie@email.com")
        );
        this.userIterator = users.iterator();
    }

    @Override
    public User read() {
        return userIterator.hasNext() ? userIterator.next() : null;
    }
}
