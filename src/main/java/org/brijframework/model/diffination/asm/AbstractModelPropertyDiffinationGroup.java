package org.brijframework.model.diffination.asm;

import org.brijframework.model.diffination.ModelPropertyDiffination;
import org.brijframework.model.diffination.ModelPropertyDiffinationGroup;
import org.brijframework.model.diffination.ModelTypeDeffination;

public abstract class AbstractModelPropertyDiffinationGroup extends AbstractModelDiffination<ModelPropertyDiffination>  implements ModelPropertyDiffinationGroup {
	private ModelPropertyDiffination fieldMeta;
	private ModelPropertyDiffination setterMeta;
	private ModelPropertyDiffination getterMeta;
	private ModelTypeDeffination model;
	
	public AbstractModelPropertyDiffinationGroup(ModelTypeDeffination model) {
		this.model=model;
	}
	
	@Override
	public ModelTypeDeffination getModel() {
		return model;
	}

	@Override
	public ModelPropertyDiffination getFieldMeta() {
		return fieldMeta;
	}
	
	public void setFieldMeta(ModelPropertyDiffination fieldMeta) {
		this.fieldMeta = fieldMeta;
	}

	@Override
	public ModelPropertyDiffination getSetterMeta() {
		if(setterMeta==null) {
			return getFieldMeta();
		}
		return setterMeta;
	}
	
	public void setSetterMeta(ModelPropertyDiffination setterMeta) {
		this.setterMeta = setterMeta;
	}

	@Override
	public ModelPropertyDiffination getGetterMeta() {
		if(getterMeta==null) {
			return getFieldMeta();
		}
		return getterMeta;
	}
	
	public void setGetterMeta(ModelPropertyDiffination getterMeta) {
		this.getterMeta = getterMeta;
	}

	@Override
	public String toString() {
		return "ModelPropertyDiffinationGroup [fieldMeta=" + fieldMeta + ", setterMeta=" + setterMeta
				+ ", getterMeta=" + getterMeta + ", model=" + model.getId() + "]";
	}

	
}
