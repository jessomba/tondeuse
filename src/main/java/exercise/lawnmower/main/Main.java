package exercise.lawnmower.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.BooleanConverter;
import com.beust.jcommander.converters.FileConverter;

import exercise.lawnmower.domain.Control;
import exercise.lawnmower.io.ControlInputFileParser;
import exercise.lawnmower.jcommander.validators.FileExistsValidator;

public class Main {
	private static final transient Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

	public static class Args {
		@Parameter(names = { "--input",
				"--i" }, required = true, converter = FileConverter.class, validateWith = FileExistsValidator.class, description = "Le fichier d entree")
		private File input;

		@Parameter(names = {
				"--parallel" }, required = false, converter = BooleanConverter.class, description = "Si on essaye d'exécuter les tondeuses à gazon en parallèle. Cela ne garantit pas la commande.")
		private boolean parallel;
	}

	private Main() {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) throws IOException {
		Optional<Args> pArgs = parseArgs(args);

		if (pArgs.isPresent()) {
			List<Control> controls = ControlInputFileParser.parse(pArgs.get().input);

			if (pArgs.get().parallel) {
				controls.parallelStream().forEach(Main::execute);
			} else {
				controls.forEach(Main::execute);
			}
		}
	}

	private static void execute(Control control) {
		try {
			control.play();
			System.out.println(control.getLawnmower().position());
		} catch (InterruptedException e) {
			LOGGER.error("thread  interrompu. Message d erreur: {}", e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param args les arguments de l'application
	 * @return non-<code>null</code> {@link Args} avec la valeur des paramètres
	 *         attendues par l'application .
	 * @throws ParameterException s'il y a des paramètres non valides
	 */
	private static Optional<Args> parseArgs(String[] args) {
		Optional<Args> result = Optional.empty();
		Args jargs = new Args();

		final JCommander commander = new JCommander(jargs);

		try {
			commander.parse(args);
			result = Optional.of(jargs);
		} catch (ParameterException e) {
			LOGGER.error("Parametres invalides. Message d erreur: [{}]", e.getMessage());
			commander.usage();
		}

		return result;
	}
}
