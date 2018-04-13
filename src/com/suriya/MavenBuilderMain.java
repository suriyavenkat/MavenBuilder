package com.suriya;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class MavenBuilderMain extends JFrame  implements ActionListener{ 
	JTextField pomLocation;
	JTextField mavenLocation ;
	MavenCommandExecuter cmd= new MavenCommandExecuter();
	private JButton go;


	public MavenBuilderMain( )
	{
		super("Maven Execute Test "); 	// instead of setTitle( )
		Container c = getContentPane();
		c.setLayout(new GridLayout(3, 2, 1, 1));

		pomLocation = new JTextField(15);			
		c.add(pomLocation);

		
		mavenLocation = new JTextField(10);
		c.add(mavenLocation);


		c.add(new JLabel("Enter pom location"));
		c.add(pomLocation);
		pomLocation.setAlignmentX(CENTER_ALIGNMENT);

		c.add(new JLabel("Enter maven location"));
		c.add(mavenLocation);
		mavenLocation.setAlignmentX(CENTER_ALIGNMENT);

		go=new JButton("Go");
		c.add(go);
		go.addActionListener(this);
		go.setAlignmentX(CENTER_ALIGNMENT);

		setSize(550,200);
		setVisible(true);
		c.add(Box.createRigidArea(new Dimension(10, 10)));
		
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}  
	public void actionPerformed(ActionEvent e)
	{
		String pomDir = pomLocation.getText();  			
		String mavenDir = mavenLocation.getText();  			
		// validation code
		if(pomDir != null && mavenDir!=null)
		{
			//ToDO: validation
			pomDir=pomDir.replace("\\", "/");
			mavenDir=mavenDir.replace("\\", "/");
			System.out.println(pomDir +"    "+ mavenDir);
			//cmd.mavenExecuter(pomDir, mavenDir);
		}
		else
		{
		}
	}
	public static void main(String args[])
	{
		new MavenBuilderMain();
		
	}
}
