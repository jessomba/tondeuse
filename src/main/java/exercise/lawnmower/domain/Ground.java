package exercise.lawnmower.domain;

import static exercise.lawnmower.utils.Assert.*;
import static java.lang.String.*;
import static java.util.Objects.*;

import java.util.concurrent.Semaphore;

/**
 * Chaque tondeuse se déplace de façon séquentielle, ce qui signifie que la
 * seconde tondeuse ne bouge que lorsque la première a exécuté intégralement sa
 * série d'instructions
 */
public class Ground {
	private final Cellule[][] cells;
	private final Dimension dimension;
	private final Semaphore semaphore = new Semaphore(1);

	/**
	 * Crée un nouveau {@link Ground} object en fonction du nombre de ligne et de
	 * colonnes (grids).
	 * 
	 * @param rows le nombre de lignes. Il doit être supérieur à zéro.
	 * @param cols le nombre de colonnes. Il doit être supérieur à zéro.
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
	 * Met un {@link Lawnmower} dans la position spécifique (x, y).
	 * 
	 * @param x l'abscisse x
	 * @param y l'ordonnée y
	 * @param l le {@link Lawnmower} à mettre à la position. Ne doit pas être
	 *          <code>null</code>
	 */
	public void set(int x, int y, AutomaticLawnmower l) {
		isTrue(isIn(x, y), format("Position invalide [%s,%s]", x, y));
		requireNonNull(l, "La tondeuse à gazon ne doit pas être null");

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
	 * @param y l'ordonnée y
	 * @return <code>true</code> si le point appartion au {@link Ground}; sinon
	 *         <code>false</code>.
	 */
	public boolean isIn(int x, int y) {
		return dimension.isIn(x, y);
	}

	/**
	 * Essaye d’obtenir un permis pour s'exécuter sur ce {@link Ground}, en bloquant
	 * jusqu’à ce qu’il soit disponible ou que les thread soient interrompues.
	 * Acquiert un permis, s'il n'y a pas d'autre objet en cours d'exécution sur ce
	 * terrain.
	 * 
	 * @return la même instance
	 * @throws InterruptedException
	 */
	public Ground acquire() throws InterruptedException {
		semaphore.acquire();
		return this;
	}

	/**
	 * Libère un permis, permettant à une autre tondeuse à gazon de se déplacer sur le sol.
	 * 
	 * @return la même instance.
	 */
	public Ground release() {
		semaphore.release();
		return this;
	}
}
