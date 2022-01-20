package com.example.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SimpleJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public SimpleJobConfiguration(
        JobBuilderFactory jobBuilderFactory,
        StepBuilderFactory stepBuilderFactory
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")       // `simpleJob`이란 이름의 Batch Job 생성
            .start(simpleStep())
            .build();
    }

    @Bean
    public Step simpleStep() {
        return stepBuilderFactory.get("simpleStep")     // `simpleStep`이란 이름의 Batch Step 생성
            .tasklet((contribution, chunkContext) -> {  // Tasklet 또는 Reader-Processor-Writer 활용
                log.info("🙌 This is Simple Step");

                return RepeatStatus.FINISHED;
            })
            .build();
    }
}
