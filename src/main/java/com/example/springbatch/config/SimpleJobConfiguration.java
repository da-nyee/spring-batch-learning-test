package com.example.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
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
        return jobBuilderFactory.get("simpleJob")
            .start(simpleStep(null))
            .build();
    }

    @Bean
    @JobScope
    public Step simpleStep(@Value("#{jobParameters[date]}") String date) {
        return stepBuilderFactory.get("simpleStep")
            .tasklet((contribution, chunkContext) -> {
                log.info("🙌 This is Simple Step");
                log.info("🙌 date = {}", date);

                return RepeatStatus.FINISHED;
            })
            .build();
    }
}
