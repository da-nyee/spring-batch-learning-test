package com.example.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@StepScope
public class SimpleScopeTasklet implements Tasklet {

    /**
     * `JobParameters`는 🌟 꼭 🌟 `JobScope` 또는 `StepScope`에서 사용해야 한다.
     * 1. `SingletonScope`에서는 `Late Binding`을 할 수 X
     * 애플리케이션을 실행할 때 `Bean`을 생성하기 때문이다.
     * 2. `SingletonScope`에서는 N개의 `Step`, 1개의 `Tasklet`이기 때문에 병렬 처리를 할 때 문제가 발생할 수 있다.
     * 하지만 `StepScope`이면, 각 `Step`마다 각 `Tasklet`을 생성하여 동시 처리를 할 때 문제가 발생하지 X
     */
    @Value("#{jobParameters[date]}")
    private String date;

    public SimpleScopeTasklet() {
        log.info("🙌 Simple Scope Tasklet 생성");
    }

    @Override
    public RepeatStatus execute(
        StepContribution contribution,
        ChunkContext chunkContext
    ) {
        log.info("🙌 This is Simple Scope Step");
        log.info("🙌 date = {}", date);

        return RepeatStatus.FINISHED;
    }
}
