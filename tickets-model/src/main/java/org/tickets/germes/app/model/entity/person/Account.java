package org.tickets.germes.app.model.entity.person;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.tickets.germes.app.model.entity.base.AbstractEntity;

/**
 * Entity that encapsulates user of the application
 */
@Table(name = "ACCOUNT")
@Entity
public class Account extends AbstractEntity{

}
