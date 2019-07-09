package exercise.lawnmower.domain;

public enum Orientation {
	/**
	 * Nord
	 */
	N {
		@Override
		Orientation turnLeft() {
			return W;
		}

		@Override
		Orientation turnRight() {
			return E;
		}
	},

	/**
	 * Est
	 */
	E {
		@Override
		Orientation turnLeft() {
			return N;
		}

		@Override
		Orientation turnRight() {
			return S;
		}
	},

	/**
	 * Ouest
	 */
	W {
		@Override
		Orientation turnLeft() {
			return S;
		}

		@Override
		Orientation turnRight() {
			return N;
		}
	},

	/**
	 * Sud
	 */
	S {
		@Override
		Orientation turnLeft() {
			return E;
		}

		@Override
		Orientation turnRight() {
			return W;
		}
	};

	/**
	 * Renvoie une nouvelle direction lorsque, à partir de celle-ci, il tourne de 90
	 * degrés dans le sens inverse des aiguilles d'une montre.
	 * 
	 * <p>
	 * Est et Ouest sont perpendiculaires au Nord et au Sud, Est étant dans le sens
	 * des aiguilles d'une montre Nord et Ouest tournant directement vers Est.
	 * </p>
	 * 
	 * @return une nouvelle direction qui, à partir de la position actuelle, est
	 *         tournée à 90 degrés dans le sens inverse des aiguilles d’une montre.
	 */
	abstract Orientation turnLeft();

	/**
	 * Renvoie une nouvelle direction qui, à partir de la position actuelle, est
	 * tournée à 90 degrés dans le sens des aiguilles d’une montre.
	 * 
	 * 
	 * <p>
	 * <strong>Est</strong> et <strong>Ouest</strong> sont perpendiculaires au
	 * <strong>Nord</strong> et <strong>Sud</strong>, avec <strong>Ouest</strong>
	 * étant dans le sens des aiguilles d'une montre à partir du
	 * <strong>Sud</strong> et <strong>Est</strong> directement opposé.
	 * </p>
	 * 
	 * @return une nouvelle direction qui, à partir de la position actuelle, est
	 *         tournée à 90 degrés dans le sens des aiguilles d’une montre.
	 *
	 */
	abstract Orientation turnRight();
}
