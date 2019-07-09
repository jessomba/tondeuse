package exercise.lawnmower.domain;

import static exercise.lawnmower.utils.Assert.*;
import static java.lang.String.*;

public class Dimension {
	private final int rows;
	private final int columns;

	public Dimension(int rows, int cols, boolean isZeroBased) {
		isTrue(rows > 0 && cols > 0, format("dimensions invalides [%s, %s]", rows, cols));

		this.rows = isZeroBased ? rows : rows + 1;
		columns = isZeroBased ? cols : cols + 1;
	}

	public Dimension(int rows, int cols) {
		this(rows, cols, false);
	}

	/**
	 * @return nombre de lignes
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return nombre de colonnes
	 */
	public int getColumns() {
		return columns;
	}

	@Override
	public String toString() {
		return String.format("[%s,%s]", rows, columns);
	}

	@Override
	public int hashCode() {
		return (rows + columns) * 13;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || !this.getClass().equals(obj.getClass())) {
			return false;
		}

		Dimension other = (Dimension) obj;

		return rows == other.rows && columns == other.columns;
	}

	public boolean isIn(int x, int y) {
		return (x >= 0 && x < rows) && (y >= 0 && y < columns);
	}
}
