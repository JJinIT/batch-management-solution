package com.kaistsw.arch.subscribe.template;

import com.google.gson.Gson;
import com.kaistsw.arch.domain.batch.jobitem.JobItemMessageDto;
import com.kaistsw.arch.domain.batch.jobitem.JobItemType;
import com.kaistsw.arch.subscribe.config.SpringCloudStreamBindings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobItemSubscriber {

    private List<JobItemTemplateFinder> finders;

    @Autowired
    private JobItemSubscriber(List<JobItemTemplateFinder> finders) {
        this.finders = finders;
    }

    @StreamListener(SpringCloudStreamBindings.BINDING_JOB_ITEM_TOPIC)
    protected void consumer(String message) {

        Gson gson = new Gson();

        JobItemMessageDto dto = gson.fromJson(message, JobItemMessageDto.class);

        finders.stream()
                .filter(finder->finder.supports(JobItemType.valueOf(dto.getJobName())))
                .findFirst()
                .get()
                .process(dto);
    }
}
