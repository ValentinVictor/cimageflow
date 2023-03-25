/* To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor. */
package net.vproject.imageflow;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.apache.tools.ant.BuildException;
import org.junit.jupiter.api.Test;

import net.vproject.common.FileUtil;

/** @author Volker */
public class TestAnt {
	// mit "." funktioniert es nicht, die zwei Backslashes am Ende sind notwendig
	// private static final String PICDIR =
	// "C:\\vpfaff_tmp\\tmpProjekte\\cimageflow\\src\\test\\resources\\";
	private static final String PICDIR = "E:\\EigeneBilder\\";
	private static final String LACIEDIR = "E:\\Bilder\\EigeneBilder";

	@Test
	public void checkCategory() {
		final AntCheckCategory statClass = new AntCheckCategory();
		statClass.setsrc(PICDIR);
		try {
			statClass.execute();
		} catch (final BuildException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void copy() {
		final AntImageCopy copyClass = new AntImageCopy();
		copyClass.setsrc(PICDIR);
		copyClass.setrating("1");
		copyClass.setdest("C:\\Transfer\\Bilder");
		try {
			copyClass.execute();
		} catch (final BuildException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void fileUtil() {
		List<String> allDir = FileUtil.findDir(PICDIR);
		assertTrue(allDir.size() > 4);
		allDir = FileUtil.findEvents(PICDIR);
		assertTrue(allDir.size() > 0);
	}

	@Test
	public void myStatistic() {
		final AntMyStatistic statClass = new AntMyStatistic();
		statClass.setsrc(PICDIR);
		try {
			statClass.execute();
		} catch (final BuildException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void statistic() {
		final AntStatistic statClass = new AntStatistic();
		statClass.setsrc(PICDIR);
		try {
			statClass.execute();
		} catch (final BuildException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
