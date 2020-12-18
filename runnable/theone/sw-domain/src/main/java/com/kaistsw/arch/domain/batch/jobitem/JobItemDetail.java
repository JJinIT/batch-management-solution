package com.kaistsw.arch.domain.batch.jobitem;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static com.kaistsw.arch.domain.batch.jobitem.JobItemDetailStatus.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(catalog = "simplepay", name = "job_item_detail")
@Where(clause = "deleted=0")
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = "jobItem")
@SuppressWarnings("PMD.UnusedPrivateField")
public class JobItemDetail {

	@Id
	@Column(name = "jobItemDetailId", nullable = false)
	@GeneratedValue(strategy = AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "jobItemId")
	private JobItem jobItem;

	private String stringUnitId;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private JobItemDetailStatus status;

	private JobItemDetail(JobItem jobItem, JobItemDetailStatus status) {
		this.jobItem = jobItem;
		this.status = status;
	}

	public static JobItemDetail ofStringUnitWait(JobItem job, String unitId) {
		JobItemDetail item = new JobItemDetail(job, WAIT);
		item.stringUnitId = unitId;
		return item;
	}

	public void updateStatusToSent() {
		status = SENT;
	}

	public void updateStatusToSendError() {
		status = SEND_ERROR;
	}

	public void updateStatusToOk() {
		status = OK;
	}

	public void updateStatusToFail() {
		status = FAIL;
	}

	public boolean processed() {
		return status == OK || status == FAIL;
	}

	public boolean success() {
		return status == OK;
	}
}
