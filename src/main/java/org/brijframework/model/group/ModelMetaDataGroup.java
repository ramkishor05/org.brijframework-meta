package org.brijframework.model.group;

import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.group.impl.DefaultGroup;
import org.brijframework.model.ModelDiffination;
import org.brijframework.util.asserts.Assertion;

public class ModelMetaDataGroup implements DefaultGroup{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object groupKey;
	
	private ConcurrentHashMap<String,ModelDiffination<?>> cache=new ConcurrentHashMap<>();
	
	public ModelMetaDataGroup(Object groupKey) {
		this.groupKey=groupKey;
		Assertion.notNull(this.groupKey, "Group key is required.");
	}

	@Override
	public Object getGroupKey() {
		return groupKey;
	}

	@Override
	public ConcurrentHashMap<String,ModelDiffination<?>> getCache() {
		return cache;
	}

	@Override
	public <T> T find(String parentID, Class<?> type) {
		return null;
	}

}
