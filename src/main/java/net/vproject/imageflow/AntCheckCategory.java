package net.vproject.imageflow;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.tools.ant.BuildException;

import net.vproject.imageio.IMetaInfo;
import net.vproject.imageio.MetaInfo;

/** baut eine Statisitik über alle Kategorien aller Bilder eines Verzeichnisses auf.
 *
 * @author Volker */
public class AntCheckCategory extends AbstractAntImage {
	@Override
	public void execute() throws BuildException {
		final Map<String, Integer> tagCounter = new TreeMap<>();
		int counter = 0;
		final List<File> filelist = FileUtil.find(srcDir, ".*\\.jpg");
		for (final File oneFile : filelist) {
			counter++;
			try {
				final IMetaInfo meta = new MetaInfo(oneFile);
				final List<String> allTags = meta.getTags();
				for (final String tag : allTags) {
					final Integer existCount = tagCounter.putIfAbsent(tag, 1);
					if (existCount != null) {
						tagCounter.replace(tag, existCount + 1);
					}
				}
			} catch (final Exception e) {
				System.err.println(oneFile.getName() + " " + e.getMessage());
			}
		}
		System.out.println(counter + " pictures");
		tagCounter.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " , " + entry.getValue()));
		final File allowedCategory = new File("");
		// TODO read, compare, add to a list and scan which files have not allowed category
	}
}
