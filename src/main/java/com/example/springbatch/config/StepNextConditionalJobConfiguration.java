package com.example.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StepNextConditionalJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public StepNextConditionalJobConfiguration(
        JobBuilderFactory jobBuilderFactory,
        StepBuilderFactory stepBuilderFactory
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job stepNextConditionalJob() {
        return jobBuilderFactory.get("stepNextConditionalJob")
            // `Batch Job`을 시작한다.
            // `step1`이
            .start(conditionalStep1())
                // `FAILED`인 경우
                .on("FAILED")
                // `step3`로 이동한다.
                .to(conditionalStep3())
                // `step3`의 결과와 관계없이
                .on("*")
                // `step3`로 이동하면, 흐름을 종료한다.
                .end()
            .from(conditionalStep1())
                // `FAILED` 외의 모든 경우
                .on("*")
                // `step2`로 이동한다.
                .to(conditionalStep2())
                // `step2`가 정상적으로 종료되면, `step3`로 이동한다.
                .next(conditionalStep3())
                // `step3`의 결과와 관계없이
                .on("*")
                // `step3`로 이동하면, 흐름을 종료한다.
                .end()
            // `Batch Job`을 종료한다.
            .end()
            .build();
    }

    @Bean
    @JobScope
    public Step conditionalStep1() {
        return stepBuilderFactory.get("conditionalStep1")
            .tasklet((contribution, chunkContext) -> {
                log.info("🙌 This is Conditional Step1");

//                // `ExitStatus`를 기준으로 흐름이 진행된다.
//                contribution.setExitStatus(ExitStatus.FAILED);

                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    @JobScope
    public Step conditionalStep2() {
        return stepBuilderFactory.get("conditionalStep2")
            .tasklet((contribution, chunkContext) -> {
                log.info("🙌 This is Conditional Step2");

                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    @JobScope
    public Step conditionalStep3() {
        return stepBuilderFactory.get("conditionalStep3")
            .tasklet((contribution, chunkContext) -> {
                log.info("🙌 This is Conditional Step3");

                return RepeatStatus.FINISHED;
            })
            .build();
    }
}
