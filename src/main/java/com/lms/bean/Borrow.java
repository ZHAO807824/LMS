package com.lms.bean;

import java.util.Collection;

public class Borrow<T extends Collection<?>> {
	private String key;
	private T value;

	public Borrow() {
	}

	public Borrow(String key, T value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
