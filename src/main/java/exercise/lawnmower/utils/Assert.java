package exercise.lawnmower.utils;

/**
 * 
 * Classe utilitaire permettant de valider les paramètres.
 *
 */
public class Assert {

	private Assert() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Cette méthode génère {@code IllegalArgumentException} si le résultat du test
	 * est {@code false}.
	 * 
	 * @param expression une expression booléenne
	 * @param message    le message en cas d'échec de l'assertion
	 * @throws IllegalArgumentException si expression est {@code false}
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Affirme que object n'est pas {@code null}
	 * 
	 * @param object
	 * @param message le message en cas d'échec de l'assertion
	 * @throws IllegalArgumentException si object est {@code null}
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an array has no null elements. Note: Does not complain if the
	 * array is empty!
	 * 
	 * <pre class="code">
	 * Assert.noNullElements(array, "Le tableau doit avoir des éléments non nuls");
	 * </pre>
	 * 
	 * @param array   le tableau à vérifier
	 * @param message le message en cas d'échec de l'assertion
	 * @throws IllegalArgumentException if le tableau array contient un élément {@code null}
	 */
	public static void noNullElements(Object[] array, String message) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}
}
