package exercise.lawnmower.jcommander.validators;

import java.io.File;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Ce validateur s'assure que le param�tre est un {@link File} et qu'il existe
 * et peut �tre lu.
 *
 */
public class FileExistsValidator implements IParameterValidator {
	@Override
	public void validate(String name, String value) throws ParameterException {
		final File f = new File(value);

		if (!f.exists()) {
			throw new ParameterException(
					String.format("Param�tre invalide [%s]. Fichier [%s] n'existe pas", name, value));
		}

		if (!f.canRead()) {
			throw new ParameterException(
					String.format("Param�tre invalide [%s]. Fichier [%s] ne peut pas �tre lu", name, value));
		}
	}
}
