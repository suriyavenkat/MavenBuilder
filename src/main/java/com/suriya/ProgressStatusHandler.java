package com.suriya;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationOutputHandler;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class ProgressStatusHandler extends JFrame implements ActionListener {

	private MavenBuilderMain mainProgram;
	JProgressBar jb; 
	JDialog dlg ;
	MavenCommandExecuter cmd= new MavenCommandExecuter();
	public ProgressStatusHandler(MavenBuilderMain main) {
		mainProgram=main;
		

		dlg= new JDialog(mainProgram, "Progress Dialog", true);
		JProgressBar dpb = new JProgressBar(0,500);
		//dpb.setIndeterminate(true);
		dlg.add(BorderLayout.CENTER, dpb);
		dlg.add(BorderLayout.NORTH, new JLabel("Progress..."));
		dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dlg.setSize(300, 75);
		dlg.setLocationRelativeTo(null);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainProgram.go.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		String pomDir = mainProgram.pomLocation.getText();  			
		String mavenDir = mainProgram.mavenLocation.getText(); 
		OutputConsoleDirHandler outputDir = mainProgram.outputDir;


		Boolean isError = false;
		Thread t = new Thread(new Runnable() {
			public void run() {
				dlg.setVisible(true);
			}
		});
		t.start();		

		

		System.out.println("RRRRRRRRRRR"+pomDir);
		// validation code
		if(pomDir != null && mavenDir!=null)
		{
			//ToDO: validation
			pomDir=pomDir.replace("\\", "/");
			mavenDir=mavenDir.replace("\\", "/");
			System.out.println(pomDir +"    "+ mavenDir);
			StringBuilder mavenOutput=runCommand("test",new File(pomDir), mavenDir);//new StringBuilder("dfhh");//
			writeToFile(mavenOutput,outputDir,isError);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Input file not found.", "Error", JOptionPane.ERROR_MESSAGE);
			isError = true;
			dlg.setVisible(false);
		}

		if(!isError){
			dlg.setVisible(false);
			System.out.println("OVER");
			mainProgram.go.setEnabled(true);
			JOptionPane.showMessageDialog(this, "Process Complete", "Info", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	public boolean writeToFile(StringBuilder mavenOutput,OutputConsoleDirHandler outDir, Boolean isError){
		try{
			System.out.println(outDir.getSelectedDirectory().getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outDir.getSelectedDirectory().getAbsolutePath() + "/console.txt")));
			bw.write(mavenOutput.toString());
			bw.newLine();
			bw.close();

		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
			isError = true;
		}
		return isError;

	}

	private StringBuilder runCommand(String mavenCommand, File workingDirectory, String mavenLocation) {
		
	
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File(workingDirectory, "pom.xml"));
		request.setGoals(Collections.singletonList(mavenCommand));

		Invoker invoker = new DefaultInvoker();
		final StringBuilder mavenOutput = new StringBuilder();
		invoker.setOutputHandler(new InvocationOutputHandler() {

			public void consumeLine(String line) {
				mavenOutput.append(line).append(System.lineSeparator());
			}
		});
		// You can find the Maven home by calling "mvn --version"
		invoker.setMavenHome(new File(mavenLocation));
		try {
			InvocationResult invocationResult = invoker.execute(request);
			// Process maven output
			System.out.println("rtyrty"+mavenOutput);
			if (invocationResult.getExitCode() != 0) {
				// handle error
			}
		} catch (MavenInvocationException e) {
			e.printStackTrace();
		}
		return mavenOutput;
	}


}
