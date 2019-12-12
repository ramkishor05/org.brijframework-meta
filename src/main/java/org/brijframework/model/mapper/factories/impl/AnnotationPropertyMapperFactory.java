package org.brijframework.model.mapper.factories.impl;

import java.lang.reflect.Field;
import java.util.Map;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.model.factories.metadata.impl.TypeModelMetaDataFactoryImpl;
import org.brijframework.model.mapper.factories.ModelMapperFactory;
import org.brijframework.model.mapper.model.PropertyModelMapperResource;
import org.brijframework.support.config.DepandOn;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.support.mapper.Mapper;
import org.brijframework.support.mapper.Mappers;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.reflect.AnnotationUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.support.Access;

@DepandOn(depand=AnnotationTypeModelMapperResourceFactory.class)
public class AnnotationPropertyMapperFactory extends AbstractModuleFactory<String,PropertyModelMapperResource> implements ModelMapperFactory<String,PropertyModelMapperResource> {

	protected AnnotationPropertyMapperFactory() {
	}

	protected static AnnotationPropertyMapperFactory factory;

	@SingletonFactory
	public static AnnotationPropertyMapperFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationPropertyMapperFactory();
		}
		return factory;
	}

	@Override
	public AnnotationPropertyMapperFactory loadFactory() {
		ReflectionFactory.getFactory().getInternalClassList().forEach(target -> {
			this.register(target);
		});
		return this;
	}

	public void register(Class<?> target) {
		for (Field field : FieldUtil.getAllField(target, Access.PRIVATE)) {
			if (field.isAnnotationPresent(Mappers.class)) {
				Mappers mappers=field.getAnnotation(Mappers.class);
				for(Mapper mapper:mappers.value()) {
					register(target, field, mapper);
				}
			} else if (field.isAnnotationPresent(Mapper.class)) {
				register(target, field, field.getAnnotation(Mapper.class));
			}
		}
	}

	public PropertyModelMapperResource register(Class<?> target, Field field, Mapper mapper) {
		Map<String, Object> properties = AnnotationUtil.getAnnotationData(mapper);
		PropertyModelMapperResource modelMap = new PropertyModelMapperResource();
		PropertyAccessorUtil.setProperties(modelMap, properties);
		modelMap.setId(target.getSimpleName() + "_" + mapper.source());
		TypeModelDiffination owner = TypeModelMetaDataFactoryImpl.getFactory().load(target);
		modelMap.setName(field.getName());
		modelMap.setOwner(owner);
		modelMap.setTarget(field);
		register(modelMap.getId(),modelMap);
		return modelMap;
	}

	@Override
	protected void preregister(String key, PropertyModelMapperResource value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postregister(String key, PropertyModelMapperResource value) {
		// TODO Auto-generated method stub
		
	}
}