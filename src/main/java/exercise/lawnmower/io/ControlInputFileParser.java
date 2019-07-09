package exercise.lawnmower.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exercise.lawnmower.domain.Control;
import exercise.lawnmower.domain.Ground;
import exercise.lawnmower.domain.AutomaticLawnmower;
import exercise.lawnmower.domain.Orientation;
import exercise.lawnmower.domain.Control.Command;
import static exercise.lawnmower.utils.Assert.*;
import static java.lang.Integer.*;

public class ControlInputFileParser {
	public static String[] parserEnvironmentSetupFile(File f) throws IOException {
		List<String> lines = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line.trim());
			}
		}

		return lines.toArray(new String[lines.size()]);
	}

	public static List<Control> parse(File f) throws IOException {
		final List<Control> result = new ArrayList<>();
		final String[] lines = parserEnvironmentSetupFile(f);

		if (lines.length > 0) {
			String[] dimensions = lines[0].split(" ");

			final Ground g = new Ground(parseInt(dimensions[0].trim()), parseInt(dimensions[1].trim()));

			for (int i = 1; i < lines.length; i += 2) {
				String[] position = lines[i].split(" ");
				isTrue(position.length == 3,
						"Une position de la tondeuse à gazon doit comporter trois parties: les coordonnées (x, y) et l’orientation.");

				AutomaticLawnmower lawnmower = new AutomaticLawnmower(parseInt(position[0]), parseInt(position[1]),
						Orientation.valueOf(position[2].toUpperCase()));
				result.add(new Control(lawnmower, g).schedule(parseCommands(lines[i + 1])));
			}
		}

		return result;
	}

	private static Command[] parseCommands(String line) {
		Command[] commands = new Command[line.length()];

		for (int i = 0; i < line.length(); i++) {
			commands[i] = Command.valueOf(line.substring(i, i + 1));
		}

		return commands;
	}
}
