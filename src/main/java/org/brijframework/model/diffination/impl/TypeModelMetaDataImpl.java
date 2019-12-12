package org.brijframework.model.diffination.impl;

import org.brijframework.model.diffination.asm.AbstractTypeModelMetaData;

public class TypeModelMetaDataImpl extends AbstractTypeModelMetaData{

	public TypeModelMetaDataImpl(Class<?> targetClass, String id, String name) {
		this.setType(targetClass);
		this.setId(id);
		this.setName(name);
	}

	@Override
	public String toString() {
		return super.toString()+
				" ["
				+ "  id=" + getId()
				+ ", type=" + getType() 
				+ ", name=" + getName() 
				+ ", access=" + getAccess() 
				+ ", properties=" + getProperties()
				+ ", owner=" + getOwner() 
				+ ", constructor=" + getConstructor() 
				+ "]";
	}

	

}