package exercise.lawnmower.domain;

import static exercise.lawnmower.domain.Position.*;
import static java.util.Objects.*;

public class AutomaticLawnmower {
	private volatile Position position;
	private volatile Control control;

	public AutomaticLawnmower(Position position) {
		setPosition(requireNonNull(position));
	}

	public AutomaticLawnmower(int x, int y, Orientation orientation) {
		this(new Position(x, y, orientation));
	}

	public AutomaticLawnmower(int x, int y, Orientation orientation, Control control) {
		this(x, y, orientation);
		this.control = control;
	}

	public AutomaticLawnmower setControl(Control control) {
		synchronized (this) {
			this.control = control;
		}

		return this;
	}

	protected AutomaticLawnmower move() {
		int x, y = x = 0;

		switch (position.getOrientation()) {
		case E:
			x = 1;
			break;
		case W:
			x = -1;
			break;
		case N:
			y = 1;
			break;
		case S:
			y = -1;
			break;
		}

		x = position.getX() + x;
		y = position.getY() + y;

		if (control.isIn(x, y)) {
			position = newPosition(x, y, position.getOrientation());
		}

		return this;
	}

	protected AutomaticLawnmower turnRight() {
		position = newPosition(position.getCoordinate(), position.getOrientation().turnRight());
		return this;
	}

	protected AutomaticLawnmower turnLeft() {
		position = newPosition(position.getCoordinate(), position.getOrientation().turnLeft());
		return this;
	}

	/**
	 * @return la position actuelle
	 */
	public Position position() {
		return position;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * @return le controle
	 */
	public Control getControl() {
		return control;
	}

	public AutomaticLawnmower setPosition(int x, int y) {
		setPosition(newPosition(x, y, position.getOrientation()));
		return this;
	}

	public AutomaticLawnmower setPosition(Position newPosition) {
		position = newPosition;

		if (control != null) {
			control.putAt(newPosition);
		}

		return this;
	}
}
