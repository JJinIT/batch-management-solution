package com.kaistsw.arch.domain.batch.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Term<T> {

	private final T from;
	private final T to;

	protected Term(T from, T to) {
		this.from = from;
		this.to = to;
	}

	public T from() {
		return from;
	}

	public T to() {
		return to;
	}

	public static <T> Term<T> newTerm(T from, T to) {
		return new Term<>(from, to);
	}

	public boolean hasNullValue() {
		return from == null || to == null;
	}

	public boolean valid() {
		return !(hasNullValue());
	}
}
