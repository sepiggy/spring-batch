package cn.sepiggy.chapter07listener.listener;

import lombok.val;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * 通过注解来实现监听器
 */
public class MyChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext) {
        val stepName = chunkContext.getStepContext().getStepName();
        System.out.println(stepName + "before...");
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext) {
        val stepName = chunkContext.getStepContext().getStepName();
        System.out.println(stepName + "after...");
    }
}
