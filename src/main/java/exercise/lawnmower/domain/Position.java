package exercise.lawnmower.domain;

import java.awt.Point;
import java.util.Objects;

import static java.util.Objects.*;

/**
 * 
 * Classe immuable représentant une position dans le plan
 *
 */
public class Position {
	private final Orientation orientation;
	private final Point coordinate;

	public Position(final Point coordinate, final Orientation orientation) {
		this.coordinate = requireNonNull(coordinate);
		this.orientation = requireNonNull(orientation);
	}

	public Position(int x, int y, Orientation orientation) {
		this(new Point(x, y), orientation);
	}

	public static Position newPosition(Point coordinate, Orientation direction) {
		return new Position(coordinate, direction);
	}

	public static Position newPosition(int x, int y, Orientation direction) {
		return new Position(x, y, direction);
	}

	/**
	 * @return l'orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Renvoie les coordonnées de cette {@link Position}. Il est important de
	 * souligner que cette méthode retourne une copie du {@link Point}, car par
	 * défaut, {@link Point} est une classe mutable.
	 * 
	 * @return les coordonnées
	 */
	public Point getCoordinate() {
		return new Point(coordinate);
	}

	/**
	 * Renvoie l'abscisse X de {@link Position}.
	 * 
	 * @return l'abscisse X de {@link Position}.
	 */
	public int getX() {
		return (int) coordinate.getX();
	}

	/**
	 * Renvoie l'ordonnée Y de {@link Position}.
	 * 
	 * @return l'ordonnée Y de {@link Position}.
	 */
	public int getY() {
		return (int) coordinate.getY();
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", getX(), getY(), orientation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || !this.getClass().equals(obj.getClass())) {
			return false;
		}

		Position other = (Position) obj;
		return Objects.equals(getCoordinate(), other.getCoordinate())
				&& Objects.equals(getOrientation(), other.getOrientation());
	}

	@Override
	public int hashCode() {
		return (orientation.hashCode() + coordinate.hashCode()) * 17;
	}

	public boolean isEqualsTo(int x, int y) {
		return getX() == x && getY() == y;
	}
}
