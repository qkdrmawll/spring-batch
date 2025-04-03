package com.qkdrmawll.batch.batch;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchInfraConfig { // Spring Batch가 실행될 때 필요한 인프라(기본환경)를 설정

    /**
     * Spring Batch에서 Job을 실행하려면 'JobRepository'와 트랜잭션 매니저가 필요함
     * JobRepository -> 배치 실행 정보를 저장하는 저장소 (어떤 Job이 언제 실행됐는지 등)
     * PlatformTransactionManager -> 트랜잭션을 관리하는 역할
     */

    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(dataSource); // 데이터 저장소 설정 -> h2
        factoryBean.setTransactionManager(transactionManager); // 트랜잭션 설정
        factoryBean.afterPropertiesSet(); // 설정 완료
        return factoryBean.getObject(); // JobRepository 반환
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
