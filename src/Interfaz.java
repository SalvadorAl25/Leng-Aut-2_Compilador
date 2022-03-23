
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Interfaz extends JFrame {
	JTextArea texto, num,consola;
	JButton abrir=new JButton("Abrir"), iniciar=new JButton("Iniciar"), guardar=new JButton("Guardar");
	JFileChooser seleccionado=new JFileChooser();
	File archivo;
	JScrollPane stexto,sconsola;
	int renglon=1;
	static Interfaz Int;
	Lector lec=new Lector();
	
	public Interfaz()
	{
		setSize(800,700);
		setLayout(null);
		setResizable(false);
		texto=new JTextArea();
		stexto=new JScrollPane(texto, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		consola=new JTextArea();
		sconsola=new JScrollPane(consola, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		sconsola.setBounds(50, 560, 700, 100);
		stexto.setBounds(50, 50, 700, 500);
		add(stexto); add(sconsola);
		
		abrir.setBounds(250, 15, 80, 20);
		iniciar.setBounds(350,15,80,20);
		guardar.setBounds(450, 15,80, 20);
		add(abrir); add(iniciar); add(guardar);
				
		abrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(seleccionado.showDialog(null, "Abrir Archivo")==JFileChooser.APPROVE_OPTION)
				{
					archivo=seleccionado.getSelectedFile();
					if(archivo.getName().endsWith(".jbc"))
					{
						if(archivo.canRead())
						{
							lec=new Lector();
							String contenido=lec.AbrirTexto(archivo);
							texto.setText(contenido);					
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Extensión: jbc");
				}
				
			}
		});
		
		iniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try
				{
					lec=new Lector();
					lec.GuardarArchivo(archivo, texto.getText());
					lec.Lectura(archivo, texto.getText(), Int);
				}
				catch(NullPointerException ex)
				{
					if(seleccionado.showDialog(null, "Guardar Como")==JFileChooser.APPROVE_OPTION)
					{
						if(seleccionado.getSelectedFile().getName().endsWith(".jbc"))
						{
							lec=new Lector();
							archivo=seleccionado.getSelectedFile();
							String contenido=texto.getText();
							lec.GuardarArchivo(archivo,contenido);
							lec.Lectura(archivo, contenido, Int);
							
						}
						else
							JOptionPane.showMessageDialog(null, "Extensión: jbc");
					}
				}
			}
		});
		
		guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(seleccionado.showDialog(null, "Guardar Como")==JFileChooser.APPROVE_OPTION)
				{
					if(seleccionado.getSelectedFile().getName().endsWith(".jbc"))
					{
						archivo=seleccionado.getSelectedFile();
						String contenido=texto.getText();
						lec.GuardarArchivo(archivo,contenido);
					}
					else
						JOptionPane.showMessageDialog(null, "Extensión: jbc");
				}
			}
		});	
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		Int=new Interfaz();
	}
	public void imprimirConsola(String cadena) {
		consola.append(cadena+"\n");
		
	}
}
