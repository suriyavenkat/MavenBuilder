package com.suriya;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class OutputConsoleDirHandler extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private File selectedDirectory;

	private MavenBuilderMain mainProgram;

	public OutputConsoleDirHandler(MavenBuilderMain mavenBuilderMain)
	{
		mainProgram = mavenBuilderMain;
	}

	public void actionPerformed(ActionEvent e)
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select output directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		//
		// disable the "All files" option.
		//
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			setSelectedDirectory(chooser.getSelectedFile());
			mainProgram.setOutputDirLabel(selectedDirectory.toString());

			//System.out.println(getSelectedDirectory());
		}
		else
		{
			System.out.println("No Selection ");
		}
	}

	public void setSelectedDirectory(File selectedDirectory)
	{
		this.selectedDirectory = selectedDirectory;
	}

	public File getSelectedDirectory()
	{
		return selectedDirectory;
	}
}

