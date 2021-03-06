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
     * `JobParameters`λ π κΌ­ π `JobScope` λλ `StepScope`μμ μ¬μ©ν΄μΌ νλ€.
     * 1. `SingletonScope`μμλ `Late Binding`μ ν  μ X
     * μ νλ¦¬μΌμ΄μμ μ€νν  λ `Bean`μ μμ±νκΈ° λλ¬Έμ΄λ€.
     * 2. `SingletonScope`μμλ Nκ°μ `Step`, 1κ°μ `Tasklet`μ΄κΈ° λλ¬Έμ λ³λ ¬ μ²λ¦¬λ₯Ό ν  λ λ¬Έμ κ° λ°μν  μ μλ€.
     * νμ§λ§ `StepScope`μ΄λ©΄, κ° `Step`λ§λ€ κ° `Tasklet`μ μμ±νμ¬ λμ μ²λ¦¬λ₯Ό ν  λ λ¬Έμ κ° λ°μνμ§ X
     */
    @Value("#{jobParameters[date]}")
    private String date;

    public SimpleScopeTasklet() {
        log.info("π Simple Scope Tasklet μμ±");
    }

    @Override
    public RepeatStatus execute(
        StepContribution contribution,
        ChunkContext chunkContext
    ) {
        log.info("π This is Simple Scope Step");
        log.info("π date = {}", date);

        return RepeatStatus.FINISHED;
    }
}
