package com.kaistsw.arch.batch.template;

import com.kaistsw.arch.domain.batch.common.Term;
import com.kaistsw.arch.domain.batch.jobitem.*;
import com.kaistsw.arch.domain.batch.kafka.KafkaProducer;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.springframework.batch.repeat.RepeatStatus.FINISHED;

public abstract class JobTaskletTemplate<T extends JobItemMessageDto> implements Tasklet, StepExecutionListener {

    @Autowired
    private JobItemService jobItemService;

    @Autowired
    private KafkaProducer producer;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        JobItem jobItem = makeJobItem();
        publishJob(jobItem);
        jobItemService.updateJobStatusToSentAll(jobItem.getId());
        return FINISHED;
    }


    private JobItem makeJobItem() {
        JobItem jobItem = JobItem.ofGeneralWait(getJobItemType(), getTargetTerm());
        jobItem.setOptions(getOption());

        jobItem.addAllItems(getStringUnitItems(jobItem));

        return jobItemService.save(jobItem);
    }

    private void publishJob(JobItem jobItem) {
        jobItemService.updateJobStatusToStarted(jobItem.getId());

        for (JobItemDetail item : jobItem.getItems()) {
            producer.send(new JobItemMessageDto(jobItem.getType().getTitle(), jobItem.getId(), item.getId()));
        }
        jobItemService.updateJobStatusToSentAll(jobItem.getId());
    }

    protected abstract JobItemType getJobItemType();
    protected String getOption() {
        return null;
    }
    protected abstract Term<LocalDate> getTargetTerm();

    private List<JobItemDetail> getStringUnitItems(JobItem job) {
        return getStringUnitIds().map(
                id -> JobItemDetail.ofStringUnitWait(job, id)).collect(toList());
    }

    protected Stream<String> getStringUnitIds() {
        return Stream.empty();
    }

}
