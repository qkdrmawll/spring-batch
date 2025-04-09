package com.qkdrmawll.batch.batch;

import com.qkdrmawll.batch.elastic.ElasticsearchService;
import com.qkdrmawll.batch.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserItemWriter implements ItemWriter<User> {
    private final ElasticsearchService elasticsearchService;
    @Override
    public void write(Chunk<? extends User> users) throws Exception {
        elasticsearchService.sendData(users);
    }
}
