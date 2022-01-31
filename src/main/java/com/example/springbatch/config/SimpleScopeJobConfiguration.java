package com.example.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SimpleScopeJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SimpleScopeTasklet simpleScopeTasklet;

    public SimpleScopeJobConfiguration(
        JobBuilderFactory jobBuilderFactory,
        StepBuilderFactory stepBuilderFactory,
        SimpleScopeTasklet simpleScopeTasklet
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.simpleScopeTasklet = simpleScopeTasklet;
    }

    @Bean
    public Job simpleScopeJob() {
        log.info("🙌 Simple Scope Job 생성");

        return jobBuilderFactory.get("simpleScopeJob")
            .start(simpleScopeStep())
            .build();
    }

    @Bean
    public Step simpleScopeStep() {
        log.info("🙌 Simple Scope Step 생성");

        return stepBuilderFactory.get("simpleScopeStep")
            .tasklet(simpleScopeTasklet)
            .build();
    }
}
