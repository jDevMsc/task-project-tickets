package org.tickets.germes.app.model.search.criteria.range;

import org.tickets.germes.app.infra.util.Checks;

/**
 * Pagination parameters for data retrieval operations
 */
public class RangeCriteria {
	/**
	 * Page index
	 */
	private final int page;

	/**
	 * Number of elements per page.
	 */
	private final int rowCount;

	public RangeCriteria(final int page, final int rowCount) {
		Checks.checkParameter(page >= 0, "Incorrect page index:" + page);
		Checks.checkParameter(rowCount >= 0, "Incorrect row count:" + rowCount);

		this.page = page;
		this.rowCount = rowCount;
	}

	public int getPage() {
		return page;
	}

	public int getRowCount() {
		return rowCount;
	}
}
