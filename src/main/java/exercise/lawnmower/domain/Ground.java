package exercise.lawnmower.domain;

import static exercise.lawnmower.utils.Assert.*;
import static java.lang.String.*;
import static java.util.Objects.*;

import java.util.concurrent.Semaphore;

/**
 * Chaque tondeuse se d�place de fa�on s�quentielle, ce qui signifie que la
 * seconde tondeuse ne bouge que lorsque la premi�re a ex�cut� int�gralement sa
 * s�rie d'instructions
 */
public class Ground {
	private final Cellule[][] cells;
	private final Dimension dimension;
	private final Semaphore semaphore = new Semaphore(1);

	/**
	 * Cr�e un nouveau {@link Ground} object en fonction du nombre de ligne et de
	 * colonnes (grids).
	 * 
	 * @param rows le nombre de lignes. Il doit �tre sup�rieur � z�ro.
	 * @param cols le nombre de colonnes. Il doit �tre sup�rieur � z�ro.
	 */
	public Ground(int rows, int cols) {
		dimension = new Dimension(rows, cols);
		cells = new Cellule[dimension.getRows()][dimension.getColumns()];

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cellule(i, j);
			}
		}
	}

	/**
	 * Met un {@link Lawnmower} dans la position sp�cifique (x, y).
	 * 
	 * @param x l'abscisse x
	 * @param y l'ordonn�e y
	 * @param l le {@link Lawnmower} � mettre � la position. Ne doit pas �tre
	 *          <code>null</code>
	 */
	public void set(int x, int y, AutomaticLawnmower l) {
		isTrue(isIn(x, y), format("Position invalide [%s,%s]", x, y));
		requireNonNull(l, "La tondeuse � gazon ne doit pas �tre null");

		if (!l.position().isEqualsTo(x, y)) {
			l.setPosition(x, y);
		}

		cells[x][y].add(l);

	}

	/**
	 * Nombre de lignes
	 * 
	 * @return le nombre de lignes
	 */
	public int getRows() {
		return dimension.getRows();
	}

	/**
	 * Nombre de colonnes.
	 * 
	 * @return le nombre de colonnes
	 */
	public int getColumns() {
		return dimension.getColumns();
	}

	/**
	 * Renvoie <code>true</code> le point (x, y) est sur les dimensions du terrain
	 * 
	 * 
	 * @param x l'abscisse x
	 * @param y l'ordonn�e y
	 * @return <code>true</code> si le point appartion au {@link Ground}; sinon
	 *         <code>false</code>.
	 */
	public boolean isIn(int x, int y) {
		return dimension.isIn(x, y);
	}

	/**
	 * Essaye d�obtenir un permis pour s'ex�cuter sur ce {@link Ground}, en bloquant
	 * jusqu�� ce qu�il soit disponible ou que les thread soient interrompues.
	 * Acquiert un permis, s'il n'y a pas d'autre objet en cours d'ex�cution sur ce
	 * terrain.
	 * 
	 * @return la m�me instance
	 * @throws InterruptedException
	 */
	public Ground acquire() throws InterruptedException {
		semaphore.acquire();
		return this;
	}

	/**
	 * Lib�re un permis, permettant � une autre tondeuse � gazon de se d�placer sur le sol.
	 * 
	 * @return la m�me instance.
	 */
	public Ground release() {
		semaphore.release();
		return this;
	}
}
