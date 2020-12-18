package com.kaistsw.arch.domain.batch.jobitem;

import lombok.Getter;

@Getter
public enum JobItemType {
	SAMPLE_JOB("SampleJob");


	private String title;

	private JobItemType(String title) {
		this.title = title;
	}
}
