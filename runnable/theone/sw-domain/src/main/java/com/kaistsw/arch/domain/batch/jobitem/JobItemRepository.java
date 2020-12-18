package com.kaistsw.arch.domain.batch.jobitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobItemRepository extends JpaRepository<JobItem, Long> {
}
