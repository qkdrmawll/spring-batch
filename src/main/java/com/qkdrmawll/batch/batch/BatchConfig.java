package com.qkdrmawll.batch.batch;

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


    @Bean
    public Job simpleJob() {
        return new JobBuilder("simplejob", jobRepository) // 'simplejob' 이라는 배치 작업 실행
                .start(simpleStep()) // simpleStep() 실행
                .build();
    }

    @Bean
    public Step simpleStep() {
        return new StepBuilder("simpleStep", jobRepository)
                .tasklet((contribution, chunkContext) -> { // tasklet 작은 작업을 수행하는 코드
                    System.out.println("Hello, Spring Batch");
                    return RepeatStatus.FINISHED;
                },transactionManager)
                .build();
    }
}
