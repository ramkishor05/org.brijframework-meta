package org.brijframework.model.factories.asm;

import java.util.Map.Entry;

import org.brijframework.factories.impl.AbstractFactory;
import org.brijframework.group.Group;
import org.brijframework.model.ModelResource;
import org.brijframework.model.factories.ModelResourceFactory;
import org.brijframework.util.printer.LoggerConsole;

public abstract class AbstractModelResourceFactory<K, T extends ModelResource<?>> extends AbstractFactory<K, T> implements ModelResourceFactory<K, T> {
	
	@Override
	public AbstractModelResourceFactory<K, T> clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public void loadContainer(K key,T metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(metaInfo.getName());
		if(!group.containsKey(key)) {
			group.add(key, metaInfo);
		}else {
			group.update(key, metaInfo);
		}
	}

	public T getContainer(K modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return getContainer().find(modelKey);
	}

	public T getMetaInfo(K id) {
		for(Entry<K, T> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}
	
	@SuppressWarnings("unchecked")
	public void register(T value) {
		K key=(K)value.getId();
		register(key, value);
	}

	@Override
	protected void preregister(K key, T value) {
		LoggerConsole.screen("Resource", "Registering for meta data with id : "+key);
	}
	
	@Override
	protected void postregister(K key, T value) {
		LoggerConsole.screen("Resource", "Registered for meta data with id : "+key);
	}
}
