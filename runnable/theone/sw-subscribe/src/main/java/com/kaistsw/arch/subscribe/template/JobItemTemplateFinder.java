package com.kaistsw.arch.subscribe.template;

import com.kaistsw.arch.domain.batch.jobitem.JobItemMessageDto;
import com.kaistsw.arch.domain.batch.jobitem.JobItemType;

public interface JobItemTemplateFinder {
    boolean supports(JobItemType type);
    void process(JobItemMessageDto dto);
}
