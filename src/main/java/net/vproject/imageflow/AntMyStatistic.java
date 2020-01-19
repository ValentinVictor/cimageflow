package net.vproject.imageflow;

import org.apache.tools.ant.BuildException;

public class AntMyStatistic extends AntStatistic {

	@Override
	public void execute() throws BuildException {
		String lw=srcDir;
		setmode(2);
		setsrc(lw + "AddEvents");
		super.execute();
		setmode(3);
		setsrc(lw + "Additional");
		super.execute();
		setsrc(lw + "Analog");
		super.execute();
		setsrc(lw + "Digital");
		super.execute();
	}

}
