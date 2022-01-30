package com.example.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DeciderJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public DeciderJobConfiguration(
        JobBuilderFactory jobBuilderFactory,
        StepBuilderFactory stepBuilderFactory
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job deciderJob() {
        return jobBuilderFactory.get("deciderJob")
            // `Batch Job`을 시작한다.
            .start(startStep())
            // 짝수 or 홀수를 구분한다.
            .next(decider())
            // `decider`의 상태가
            .from(decider())
                // 짝수(EVEN)이면
                .on("EVEN")
                // `evenStep`으로 간다.
                .to(evenStep())
            // `decider`의 상태가
            .from(decider())
                // 홀수(ODD)이면
                .on("ODD")
                // `oddStep`으로 간다.
                .to(oddStep())
            // `Batch Job`을 종료한다.
            .end()
            .build();
    }

    @Bean
    @JobScope
    public Step startStep() {
        return stepBuilderFactory.get("startStep")
            .tasklet((contribution, chunkContext) -> {
                log.info("🙌 시작합니다.");

                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    @JobScope
    public Step evenStep() {
        return stepBuilderFactory.get("evenStep")
            .tasklet((contribution, chunkContext) -> {
                log.info("🙌 짝수입니다.");

                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    @JobScope
    public Step oddStep() {
        return stepBuilderFactory.get("oddStep")
            .tasklet((contribution, chunkContext) -> {
                log.info("🙌 홀수입니다.");

                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    @JobScope
    public JobExecutionDecider decider() {
        return new EvenOrOddDecider();
    }
}
