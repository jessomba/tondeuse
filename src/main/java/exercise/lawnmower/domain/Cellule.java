package exercise.lawnmower.domain;

import static exercise.lawnmower.utils.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Cellule {
	private final int row;
	private final int col;

	private final List<AutomaticLawnmower> lawnmowers = new ArrayList<AutomaticLawnmower>();

	/**
	 * Crée une nouvelle cellule (abscisse, ordonnée).
	 * 
	 * @param row abscisse. doit être supérieures à zéro
	 * @param col ordonnée. doit être supérieures à zéro
	 */
	public Cellule(int row, int col) {
		this.row = row;
		this.col = col;

		isTrue(row >= 0 && col >= 0, "abscisses et ordonnées doivent être supérieures à zéro");
	}

	public void add(AutomaticLawnmower lawnmower) {
		lawnmowers.add(lawnmower);
	}

	/**
	 * Retourne {@link Lawnmower} à la position spécifique dans {@link Cellule}.
	 * 
	 * @param index index de la {@link Lawnmower}
	 * @return {@link Lawnmower} à la position spécifiée dans cette cellule
	 * @throws IndexOutOfBoundsException si l'index est hors limites
	 */
	public AutomaticLawnmower get(int index) {
		return lawnmowers.get(index);
	}

	/**
	 * @return l'abscisse
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return l'ordonnée
	 */
	public int getCol() {
		return col;
	}

	@Override
	public String toString() {
		return String.format("[%s,%s]", getRow(), getCol());
	}

}
