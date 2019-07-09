package exercise.lawnmower.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static exercise.lawnmower.utils.Assert.*;
import static java.util.Objects.*;

public class Control {
	public enum Command {
		/**
		 * Tournez à droite
		 */
		D,

		/**
		 * Tournez à gauche
		 */
		G,

		/**
		 * Avancer
		 */
		A;
	}

	private final AutomaticLawnmower lawnmower;
	private final Ground ground;

	private final AtomicBoolean isRunning = new AtomicBoolean(false);

	private final List<Command> scheduled = new ArrayList<Command>();
	private final List<Command> history = new ArrayList<Command>();

	public Control(AutomaticLawnmower lawnmower, Ground ground) {
		this.lawnmower = requireNonNull(lawnmower);
		this.lawnmower.setControl(this);
		this.ground = requireNonNull(ground);
	}

	public Control schedule(Command... commands) {
		noNullElements(commands, "Le tableau doit avoir des éléments non nuls");

		if (!isRunning.get()) {
			scheduled.addAll(Arrays.asList(commands));
		}

		return this;
	}

	public Control scheduleAndPlay(Command... commands) throws InterruptedException {
		noNullElements(commands, "The array must have non-null elements");

		if (!isRunning.get()) {
			scheduled.addAll(Arrays.asList(commands));

			play();
		}

		return this;
	}

	public Control play() throws InterruptedException {
		ground.acquire();

		if (isRunning.compareAndSet(false, true)) {
			try {
				scheduled.forEach(this::execute);
				history.addAll(scheduled);
				scheduled.clear();
			} finally {
				isRunning.compareAndSet(true, false);
				ground.release();
			}
		}

		return this;
	}

	private void execute(Command command) {
		switch (command) {
		case A:
			lawnmower.move();
			break;
		case D:
			lawnmower.turnRight();
			break;
		case G:
			lawnmower.turnLeft();
			break;
		default:
			throw new IllegalStateException();
		}
	}

	public void putAt(Position position) {
		ground.set(position.getX(), position.getY(), lawnmower);
	}

	public boolean isIn(int x, int y) {
		return ground.isIn(x, y);
	}

	/**
	 * @return la tondeuse à gazon
	 */
	public AutomaticLawnmower getLawnmower() {
		return lawnmower;
	}

	/**
	 * @return le sol
	 */
	public Ground getGround() {
		return ground;
	}
}
