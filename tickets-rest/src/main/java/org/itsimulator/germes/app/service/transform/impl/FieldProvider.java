package org.tickets.germes.app.service.transform.impl;

import java.util.List;
import org.tickets.germes.app.infra.util.ReflectionUtil;

/**
 * Base functionality of the field preparation
 */
public class FieldProvider {
	public List<String> getFieldNames(Class<?> source, Class<?> dest) {
		return ReflectionUtil.findSimilarFields(source, dest);
	}
}
