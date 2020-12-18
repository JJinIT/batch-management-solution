package com.kaistsw.arch.domain.batch.jobitem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobItemMessageDto {
    private String jobName;
    private Long jobItemId;
    private Long jobItemDetailId;
}
