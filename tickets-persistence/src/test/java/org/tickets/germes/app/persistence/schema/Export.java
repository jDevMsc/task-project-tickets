package org.tickets.germes.app.persistence.schema;

import java.util.EnumSet;
import java.util.Set;
import javax.persistence.Entity;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.reflections.Reflections;

/**
 * Dynamically generates SQL schema
 */
public class Export {
	/**
	 * Creates file with DDL statements to create project database from scratch
	 * using specified dialect
	 */
	public static void exportDatabase(String folder, Class<? extends Dialect> dialect) {
		MetadataSources metadata = new MetadataSources(
				new StandardServiceRegistryBuilder().applySetting("hibernate.dialect", dialect.getName()).build());

		//define the base package for search and set search criteria
		Reflections reflections = new Reflections("org.tickets.germes.app.model.entity");

		Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
		entityClasses.forEach(metadata::addAnnotatedClass);

		SchemaExport schema = new SchemaExport();
		schema.setDelimiter(";");
		schema.setOutputFile(folder + "schema_" + dialect.getSimpleName() + ".sql");

		schema.create(EnumSet.of(TargetType.SCRIPT), metadata.buildMetadata());
	}

	public static void main(String[] args) {
		exportDatabase("", MySQL5Dialect.class);
	}

}