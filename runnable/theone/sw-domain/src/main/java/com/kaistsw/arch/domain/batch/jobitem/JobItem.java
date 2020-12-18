package com.kaistsw.arch.domain.batch.jobitem;

import com.kaistsw.arch.domain.batch.common.Term;
import lombok.*;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.kaistsw.arch.domain.batch.jobitem.JobItemStatus.*;

@Entity
@Table(catalog = "simplepay", name = "job_item")
@Where(clause = "deleted=0")
@Getter
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
public class JobItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	@Column(name = "jobItemId")
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private JobItemType type;

	@Setter
	private LocalDate targetStartDate;
	@Setter
	private LocalDate targetEndDate;

	@Setter
	private String options;

	private Integer totalCount;
	private Integer successCount;
	private Integer failCount;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private JobItemStatus status;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "scatchJob")
	private List<JobItemDetail> items = newArrayList();

	public static JobItem ofGeneralWait(JobItemType type, Term<LocalDate> term) {
		JobItem job = new JobItem();
		job.type = type;
		job.targetStartDate = term.from();
		job.targetEndDate = term.to();
		job.status = WAIT;
		return job;
	}

	public void addAllItems(List<JobItemDetail> items) {
		this.items.addAll(items);
		this.totalCount = this.items.size();
	}

	public void updateStatusToStarted() {
		status = STARTED;
	}

	public void updateStatusToSentAll() {
		status = SENT_ALL;
	}

	public boolean isFinish() {
		if (isEmpty()) {
			return false;
		}
		return this.totalCount == items.stream().filter(i -> i.success()).count();
	}

	public boolean isFail() {
		if (isEmpty()) {
			return false;
		}
		return this.totalCount != items.stream().filter(i -> i.success()).count();
	}

	private boolean isEmpty() {
		return this.totalCount == 0;
	}
}
