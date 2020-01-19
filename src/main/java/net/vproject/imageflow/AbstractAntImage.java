package net.vproject.imageflow;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tools.ant.Task;

/** Base for file Tasks in Ant.
 *
 * @author Volker */
public abstract class AbstractAntImage extends Task {
	protected int rating;
	protected String srcDir;
	protected String destDir;

	private List<String> readFiles(String srcDir) {
		final List<String> allFiles = new ArrayList<>();
		final Path dir = Paths.get(".");
		final String[] entries = new File(".").list();
		allFiles.addAll(Arrays.asList(entries));
		return allFiles;
	}

	public void setdest(String dest) {
		destDir = dest;
	}

	public void setrating(String rate) {
		rating = Integer.valueOf(rate);
	}

	public void setsrc(String src) {
		srcDir = src;
	}

	protected void writeFiles(List<File> input, String destDir) {
		System.out.println(input.size() + " Files to " + destDir);
		for (final File in : input) {
			final File out = new File(destDir + "\\" + in.getName());
			try {
				Files.copy(in.toPath(), out.toPath());
			} catch (final IOException ex) {
				Logger.getLogger(AbstractAntImage.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
