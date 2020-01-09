package org.tickets.germes.app.service.transform.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.tickets.germes.app.infra.util.ReflectionUtil;

/**
 * Verifies functionality of the  SimpleDTOTransformer
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ReflectionUtil.class)
public class CachedFieldProviderTest {
	private FieldProvider provider;
	
	@Before
	public void setup() {
		provider = new CachedFieldProvider();
	}
	
	@Test
	public void testGetFieldNamesSuccess() {
		List<String> fields = provider.getFieldNames(Source.class, Destination.class);
		assertFalse(fields.isEmpty());
		assertTrue(fields.contains("value"));		
	}

	@Test
	public void testGetFieldNamesCachedSuccess() {
		List<String> fields = provider.getFieldNames(Source.class, Destination.class);
		List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
		assertFalse(fields.isEmpty());
		assertEquals(fields, fields2);		
	}

	@Test
	public void testGetFieldNamesAreCached() {
		List<String> fields = provider.getFieldNames(Source.class, Destination.class);

		PowerMockito.mockStatic(ReflectionUtil.class);
		when(ReflectionUtil.findSimilarFields(Source.class, Destination.class)).thenReturn(Collections.emptyList());

		assertTrue(ReflectionUtil.findSimilarFields(Source.class, Destination.class).isEmpty());
		List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
		assertFalse(fields.isEmpty());
		assertEquals(fields, fields2);
	}

}
	


class Source {
	int value;
}

class Destination {
	int value;
}
