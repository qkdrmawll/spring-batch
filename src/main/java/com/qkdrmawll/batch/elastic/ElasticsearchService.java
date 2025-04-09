package com.qkdrmawll.batch.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.qkdrmawll.batch.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ElasticsearchService {

    private final ElasticsearchClient elasticsearchClient;

    public void sendData(Chunk<? extends User> users) throws IOException {
        for (User user : users) {
            elasticsearchClient.index(i -> i
                    .index("user-index")
                    .document(user)
            );
        }
    }
}
