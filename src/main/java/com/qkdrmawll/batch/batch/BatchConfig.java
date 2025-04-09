package com.qkdrmawll.batch.batch;

import com.qkdrmawll.batch.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository; // 배치 실행 정보를 저장하는 역할
    private final PlatformTransactionManager transactionManager; // 트랜잭션 관리 ( 실패 시 롤백 가능)
    private final UserItemReader userItemReader;
    private final UserItemProcessor userItemProcessor;
    private final UserItemWriter userItemWriter;

    @Bean
    public Job userChunkJob() {
        return new JobBuilder("userChunkJob", jobRepository) // 'userChunkJob' 이라는 배치 작업 실행
                .start(userChunkStep()) // simpleStep() 실행
                .build();
    }

    @Bean
    public Step userChunkStep() {
        return new StepBuilder("userChunkStep", jobRepository)
                .<User, User>chunk(2, transactionManager)
                .reader(userItemReader)
                .processor(userItemProcessor)
                .writer(userItemWriter)
                .build();
    }
}
