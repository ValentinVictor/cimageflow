package net.vproject.imageflow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;

import net.vproject.common.FileUtil;
import net.vproject.imageio.ImgPlus;

public class AbstractImageList extends AbstractAntImage {
	@Override
	public void execute() throws BuildException {
		final List<File> input = FileUtil.find(srcDir, null);
		writeFiles(filter(input), destDir);
	}

	protected List<File> filter(List<File> input) {
		final List<File> result = new ArrayList<>();
		// XXX stream
		for (final File in : input) {
			if (filterFile(in)) {
				result.add(in);
			}
		}
		System.out.println(input.size() + " in, out " + result.size());
		return result;
	}

	protected boolean filterFile(File fn) {
		if (FileUtil.checkPic(fn)) {
			final ImgPlus img = new ImgPlus(fn);
			return filterImage(img);
		} else {
			return false;
		}
	}

	protected boolean filterImage(ImgPlus img) {
		return true;
	}
}
