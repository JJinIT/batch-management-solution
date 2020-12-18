package com.kaistsw.arch.domain.batch.jobitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class JobItemService {

    @Autowired
    private JobItemRepository jobItemRepository;

    @Autowired
    private JobItemDetailRepository jobItemDetailRepository;

    @Transactional
    public JobItem save(JobItem jobItem) {
        return jobItemRepository.save(jobItem);
    }

    @Transactional
    public void updateJobStatusToSentAll(Long jobId) {
        findJob(jobId).updateStatusToSentAll();
    }

    @Transactional
    public void updateJobStatusToStarted(Long jobId) {
        findJob(jobId).updateStatusToStarted();
    }

    public JobItem findJob(Long jobId) {
        return jobItemRepository.findById(jobId).get();
    }
}
