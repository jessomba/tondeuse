package exercise.lawnmower.domain;

import static exercise.lawnmower.utils.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Cellule {
	private final int row;
	private final int col;

	private final List<AutomaticLawnmower> lawnmowers = new ArrayList<AutomaticLawnmower>();

	/**
	 * Cr�e une nouvelle cellule (abscisse, ordonn�e).
	 * 
	 * @param row abscisse. doit �tre sup�rieures � z�ro
	 * @param col ordonn�e. doit �tre sup�rieures � z�ro
	 */
	public Cellule(int row, int col) {
		this.row = row;
		this.col = col;

		isTrue(row >= 0 && col >= 0, "abscisses et ordonn�es doivent �tre sup�rieures � z�ro");
	}

	public void add(AutomaticLawnmower lawnmower) {
		lawnmowers.add(lawnmower);
	}

	/**
	 * Retourne {@link Lawnmower} � la position sp�cifique dans {@link Cellule}.
	 * 
	 * @param index index de la {@link Lawnmower}
	 * @return {@link Lawnmower} � la position sp�cifi�e dans cette cellule
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
	 * @return l'ordonn�e
	 */
	public int getCol() {
		return col;
	}

	@Override
	public String toString() {
		return String.format("[%s,%s]", getRow(), getCol());
	}

}
