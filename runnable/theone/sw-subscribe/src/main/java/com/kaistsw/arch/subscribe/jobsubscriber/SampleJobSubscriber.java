package com.kaistsw.arch.subscribe.jobsubscriber;

import com.kaistsw.arch.domain.batch.jobitem.JobItemMessageDto;
import com.kaistsw.arch.domain.batch.jobitem.JobItemType;
import com.kaistsw.arch.subscribe.template.JobItemTemplateFinder;
import org.springframework.stereotype.Service;

@Service
public class SampleJobSubscriber implements JobItemTemplateFinder {

    private static final JobItemType type = JobItemType.SAMPLE_JOB;

    @Override
    public boolean supports(JobItemType jobItemType) {
        return type.equals(jobItemType);
    }

    @Override
    public void process(JobItemMessageDto dto) {
        // 처리로직.
    }
}
