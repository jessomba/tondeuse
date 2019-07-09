package exercise.lawnmower.domain;

import org.junit.Before;
import org.junit.Test;

import exercise.lawnmower.domain.Control;
import exercise.lawnmower.domain.Ground;
import exercise.lawnmower.domain.AutomaticLawnmower;
import exercise.lawnmower.domain.Position;
import static exercise.lawnmower.domain.Control.Command.*;
import static exercise.lawnmower.domain.Orientation.*;
import static org.assertj.core.api.Assertions.*;

public class AutomaticLawnMowerControlTest {
	private Ground ground;

	@Before
	public void setUp() {
		ground = new Ground(5, 5);
	}

	@Test
	public void must_be_at_the_right_top_position() throws InterruptedException {
		Control c = new Control(new AutomaticLawnmower(5, 5, N), ground);

		c.schedule(A).play();
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 5, N));

		c.schedule(D, A).play();
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 5, E));

		c.schedule(G, A).play();
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 5, N));
	}

	@Test
	public void must_be_at_the_left_top_position() throws InterruptedException {
		Control c = new Control(new AutomaticLawnmower(0, 5, N), ground);

		c.schedule(A).play();
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 5, N));

		c.schedule(G, A).play();
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 5, W));

		c.schedule(G).play();
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 5, S));
	}

	@Test
	public void must_be_at_the_left_bottom_position() throws InterruptedException {
		Control c = new Control(new AutomaticLawnmower(0, 0, W), ground);

		c.scheduleAndPlay(A);
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 0, W));

		c.scheduleAndPlay(G);
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 0, S));

		c.scheduleAndPlay(A);
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 0, S));

		c.scheduleAndPlay(G);
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 0, E));
	}

	@Test
	public void must_be_at_the_right_bottom_position() throws InterruptedException {
		Control c = new Control(new AutomaticLawnmower(5, 0, S), ground);

		c.scheduleAndPlay(A);
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 0, S));

		c.scheduleAndPlay(G);
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 0, E));

		c.scheduleAndPlay(G);
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 0, N));

		c.scheduleAndPlay(G);
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 0, W));
	}

	@Test
	public void must_validate_the_provided_examples() throws InterruptedException {
		Control c = new Control(new AutomaticLawnmower(1, 2, N), ground);

		c.schedule(G, A, G, A, G, A, G, A, A).play();
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(1, 3, N));

		c = new Control(new AutomaticLawnmower(3, 3, E), ground);

		c.schedule(A, A, D, A, A, D, A, D, D, A).play();
		assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 1, E));
	}
}
