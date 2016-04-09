package org.hugonikanor.imageStitcher.graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Frame extends JFrame {
	private static final long serialVersionUID = 3869935805178282387L;

	public Frame( BufferedImage stitchedImage, ActionListener al ) {
		super( "ImageStitcher preview" );
		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} catch( ClassNotFoundException e ) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		this.getContentPane().add(
				new JScrollPane(new JLabel(
						new ImageIcon(stitchedImage))),
						BorderLayout.CENTER);

		JPanel confirmPane = new JPanel();
		confirmPane.setLayout( new java.awt.FlowLayout() );

		JButton closeBtn = new JButton("Cancel");
		closeBtn.addActionListener( (e) -> {
			System.exit( 0 );
		});
		confirmPane.add( closeBtn );

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener( al );
		confirmPane.add( saveBtn );

		this.getContentPane().add( confirmPane, BorderLayout.SOUTH );

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}
