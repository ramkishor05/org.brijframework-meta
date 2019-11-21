package org.brijframework.model.container.asm;

import org.brijframework.container.impl.AbstractModuleContainer;
import org.brijframework.group.Group;
import org.brijframework.model.container.ModelContainer;
import org.brijframework.model.factories.ClassMetaDataFactory;
import org.brijframework.model.group.MetaInfoGroup;
import org.brijframework.support.config.Assignable;
import org.brijframework.support.config.DepandOn;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

@DepandOn(depand=MetaResourceContainer.class)
public class MetaDataContainer extends AbstractModuleContainer implements ModelContainer{

	private static MetaDataContainer container;

	@Assignable
	public static MetaDataContainer getContainer() {
		if (container == null) {
			container = InstanceUtil.getSingletonInstance(MetaDataContainer.class);
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls -> {
				if (ClassMetaDataFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ClassMetaDataFactory>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionUtils.getClassListFromInternal().forEach(cls -> {
				if (ClassMetaDataFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ClassMetaDataFactory>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Group load(Object groupKey) {
		Group group = getCache().get(groupKey);
		if (group == null) {
			group = new MetaInfoGroup(groupKey);
			getCache().put(groupKey, group);
		}
		return group;
	}
}