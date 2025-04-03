package com.qkdrmawll.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BatchApplication implements CommandLineRunner {

	private final JobLauncher launcher;
	private final Job simpleJob;

    public BatchApplication(JobLauncher launcher, Job simpleJob) {
        this.launcher = launcher;
        this.simpleJob = simpleJob;
    }

    public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("배치 실행 시작 !");

		JobParameters params = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();

		launcher.run(simpleJob, params);
		System.out.println("배치 실행 완료 !");
	}
}
