package com.kaistsw.arch.batch.job.sample;

import com.kaistsw.arch.batch.template.JobTaskletTemplate;
import com.kaistsw.arch.domain.batch.common.Term;
import com.kaistsw.arch.domain.batch.jobitem.JobItemType;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

import java.time.LocalDate;
import java.util.stream.Stream;

@EnableBatchProcessing
public class SampleJobTasklet extends JobTaskletTemplate {
    @Override
    protected JobItemType getJobItemType() {
	System.out.println("getjobitemtype");
        return JobItemType.SAMPLE_JOB;
    }

    @Override
    protected Term<LocalDate> getTargetTerm() {
        return null;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
	System.out.println("hell beforeStep");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
	System.out.println("hell afterStep");
        return null;
    }

    @Override
    protected Stream<String> getStringUnitIds() {
        return null;
    }
}
