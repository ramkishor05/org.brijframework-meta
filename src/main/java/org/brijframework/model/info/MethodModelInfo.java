package org.brijframework.model.info;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.brijframework.model.MetaData;
import org.brijframework.model.setup.ParamMetaSetup;

public interface MethodModelInfo extends MetaData<Method>{
	
	@Override
	public default void init() {
		setReturnType(getTarget().getReturnType());
	}
	
	public void setReturnType(Class<?> type);
	
	public Class<?> getReturnType();
	
	public Method getTarget();

	public void setArguments(Type[] arguments);
	
	public Type[] getArguments();
	
	public Set<ParamMetaSetup> getParametersInfo();

	public void papulate(Map<String, Object> annotationData);

	public String getParentID();
	
	public default Object[] getParamValues() {
		Set<ParamMetaSetup> params= getParametersInfo();
		if(params==null) {
			return null;
		}
		Object[] values=new Object[params.size()];
		AtomicInteger count=new AtomicInteger(0);
		params.stream().sorted().forEach(paramInfo -> {
			values[count.getAndIncrement()]=paramInfo.getValue();
		});
		return values;
	}

	OwnerModelInfo getClassMetaInfo();
}
