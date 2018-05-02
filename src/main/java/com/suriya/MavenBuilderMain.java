package com.suriya;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;


public class MavenBuilderMain extends JFrame  implements ActionListener{ 
	JTextField pomLocation;
	JTextField mavenLocation ;

	JButton go;
	OutputConsoleDirHandler outputDir = new OutputConsoleDirHandler(this);
	private ProgressStatusHandler progress = new ProgressStatusHandler(this);
	private JButton outputDir1;
	private JLabel outputDirLabel;
	JProgressBar pb;

	int i=0,num=0;     
	public MavenBuilderMain( )
	{
		super("Maven Execute Test "); 	// instead of setTitle( )
		Container c = getContentPane();
		//JFrame c= new JFrame();  
		c.setLayout(new GridLayout(3, 2, 20, 10));

		pomLocation = new JTextField(25);			
		//pomLocation.setBounds(50,50,150,20);


		mavenLocation = new JTextField(250);
		//mavenLocation.setBounds(50,50,150,20);

		c.add(new JLabel("Enter pom location"));
		c.add(pomLocation);
		pomLocation.setAlignmentX(CENTER_ALIGNMENT);
		c.add(Box.createRigidArea(new Dimension(1, 10)));

		c.add(new JLabel("Enter maven location"));
		c.add(mavenLocation);
		mavenLocation.setAlignmentX(CENTER_ALIGNMENT);
		c.add(Box.createRigidArea(new Dimension(1, 10)));

		outputDir1 = new JButton("Select output Directory");
		outputDir1.addActionListener(outputDir);
		outputDir1.setAlignmentX(CENTER_ALIGNMENT);
		c.add(outputDir1);

		outputDirLabel = new JLabel("not selected");
		add(outputDirLabel);
		outputDirLabel.setAlignmentX(CENTER_ALIGNMENT);
		//c.add(Box.createRigidArea(new Dimension(15, 5)));

		go=new JButton("Go");

		go.addActionListener(progress);
		go.setAlignmentX(CENTER_ALIGNMENT);
		//go.addActionListener(this);
		c.add(go,BorderLayout.CENTER);														


		//	add(jb);  
		setSize(550,300);
		setVisible(true);
		setLocationRelativeTo(null);


	}  

	public void actionPerformed(ActionEvent e)
	{}
	public static void main(String args[])
	{
		MavenBuilderMain main=new MavenBuilderMain();
		main.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		//main.iterate();
	}
	public void setOutputDirLabel(String outputDir)
	{
		outputDirLabel.setText(outputDir);
	}


}
