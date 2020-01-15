package org.tickets.germes.app.rest.dto.base;

import org.tickets.germes.app.model.entity.base.AbstractEntity;

/**
 * Base class for all DTO classes
 */
public abstract class BaseDTO<T extends AbstractEntity> implements Transformable<T>{
	/**
	 * Unique entity identifier
	 */
	private int id;

	/**
	 * logic domain model -> DTO is needed.
	 */
	public void transform(T t) {
		id = t.getId();
	}

	/**
	 * logic DTO -> domain model is needed
	 */
	public T untransform(T t) {
		t.setId(getId());
		return t;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
