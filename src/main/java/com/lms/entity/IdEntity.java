package com.lms.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用实体类
 * 
 * @author zhao
 *
 */
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = 1940699071518625049L;
	public Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static <T extends IdEntity> Map<Integer, T> idEntityMap(Collection<T> list) {
		Map<Integer, T> map = new HashMap<Integer, T>();
		if (null == list || 0 == list.size()) {
			return map;
		}
		for (T entity : list) {
			map.put(entity.getId(), entity);
		}
		return null;
	}
}
