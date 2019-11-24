package org.brijframework.model.factories;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.model.info.OwnerModelInfo;

public interface ClassMetaDataFactory extends MetaFactory<String, OwnerModelInfo>{

	List<OwnerModelInfo> getClassInfoList(Class<?> cls);

	List<OwnerModelInfo> getClassInfoList(Class<?> target, String parentID);

	OwnerModelInfo getClassInfo(String parentID);
	
	ConcurrentHashMap<String, OwnerModelInfo> getCache();
}
