package net.vproject.imageflow;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.apache.tools.ant.BuildException;

import net.vproject.common.FileUtil;
import net.vproject.imageio.IMetaInfo;
import net.vproject.imageio.MetaInfo;

/**
 * baut eine Statisitik über alle Bilder eines Verzeichnisses auf.
 *
 * @author Volker
 */
public class AntStatistic extends AbstractAntImage {
	private int mode = 1;

	@Override
	public void execute() throws BuildException {
		int counter = 0;
		int counterNoAuthor = -1;
		int counterTags = 0;
		int counterModFiles = 0;
		final int[] counterQuality = new int[6];
		final List<File> filelist = FileUtil.find(srcDir, ".*\\.jpg");
		for (final File oneFile : filelist) {
			counter++;
			try {
				final IMetaInfo meta = new MetaInfo(oneFile);
				counterQuality[meta.getQuality()]++;
				if (meta.getAuthor().isEmpty()) {
					counterNoAuthor++;
				}
				counterTags += meta.getTags().size();
				if (meta.getKey().endsWith("A")) {
					counterModFiles++;
				}
			} catch (final Exception e) {
				System.err.println(oneFile.getName() + " " + e.getMessage());
				counterQuality[0]++;
			}
		}
		final int countEvents = FileUtil.findDir(srcDir).size();
		final int countInfoFiles = FileUtil.findEvents(srcDir).size();
		switch (mode) {
		case 2:
			System.out.println(
					"Datum,Verzeichnis,Anzahl Bilder,Events,EventInfos(*.md),Qualitaet keine,1,2,3,4,5,Tags,Mod.Files");
		case 3:
			System.out.println(LocalDate.now() + "," + srcDir + "," + counter + "," + countEvents + "," + countInfoFiles
					+ "," + counterQuality[0] + "," + counterQuality[1] + "," + counterQuality[2] + ","
					+ counterQuality[3] + "," + counterQuality[4] + "," + counterQuality[5] + "," + counterTags + ","
					+ counterModFiles);
			break;
		default:
			System.out.println(counter + " files (" + srcDir + "), in " + countEvents + " Events " + countInfoFiles
					+ " mit Beschreibungen" + " " + counterTags + " keywords, files verbessert " + counterModFiles);
			System.out.println("kein Author: " + counterNoAuthor + " no:" + counterQuality[0] + " 1:"
					+ counterQuality[1] + " 2:" + counterQuality[2] + " 3:" + counterQuality[3] + " 4:"
					+ counterQuality[4] + " 5:" + counterQuality[5]);
		}
	}

	/**
	 * <ul>
	 * <li>1 zweizeilig alle Informationen</li>
	 * <li>2 die Kopfzeile fÃ¼r [3]</li>
	 * <li>3 csv variant of information</li>
	 * </ul>
	 *
	 * @param outputMode an integer for the type of output.
	 */
	public void setmode(int ouputMode) {
		mode = ouputMode;
	}
}
