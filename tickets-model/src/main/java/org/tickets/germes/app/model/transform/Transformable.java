package org.tickets.germes.app.model.transform;

/**
 * Any object that supports direct/back-ward transformation
 * into object kind of objects
 */
public interface Transformable<P> {
	/**
	 * Transforms given object into current one
	 */
	void transform(P p);
	
	/**
	 * Transforms current object into given one
	 */
	P untransform(P p);

}
