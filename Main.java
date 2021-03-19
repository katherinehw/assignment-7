package herman;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Main {

	private JFrame frame;
	private JTextField textFieldNumLines;
	private JTextField textFieldNumKeys;
	private JTextField textFieldTime;
	private JButton btnChooseJava;
	private JButton btnChooseKeys;
	private JButton btnSearch;
	private JFileChooser choose;
	private File lineFile;
	private File keyFile;
	private String lineCount, keyCount, timeHold;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search For Keywords From a File");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(117, 18, 211, 16);
		frame.getContentPane().add(lblNewLabel);
		
		btnChooseJava = new JButton("Choose Java File");
		btnChooseJava.setBounds(39, 61, 167, 29);
		frame.getContentPane().add(btnChooseJava);
		
		btnChooseKeys = new JButton("Choose Keywords File");
		btnChooseKeys.setBounds(232, 61, 167, 29);
		frame.getContentPane().add(btnChooseKeys);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(160, 117, 117, 29);
		frame.getContentPane().add(btnSearch);
		
		JLabel lblNewLabel_1 = new JLabel("# of lines:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(39, 164, 108, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("# of keywords:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(39, 192, 108, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Time");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(39, 220, 108, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		textFieldNumLines = new JTextField();
		textFieldNumLines.setBounds(160, 159, 130, 26);
		frame.getContentPane().add(textFieldNumLines);
		textFieldNumLines.setColumns(10);
		
		textFieldNumKeys = new JTextField();
		textFieldNumKeys.setBounds(160, 187, 130, 26);
		frame.getContentPane().add(textFieldNumKeys);
		textFieldNumKeys.setColumns(10);
		
		textFieldTime = new JTextField();
		textFieldTime.setBounds(160, 215, 130, 26);
		frame.getContentPane().add(textFieldTime);
		textFieldTime.setColumns(10);
	}
	
	private void createEvents() {
		btnChooseJava.addActionListener(new ActionListener() {//user chooses java file when button pressed
			public void actionPerformed(ActionEvent e) {
				chooseJavaFile();
		 
			}
		});
		
		btnChooseKeys.addActionListener(new ActionListener() {//user chooses keywords file when button pressed
			public void actionPerformed(ActionEvent e) {
				chooseKeysFile();
		
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Timer timer = new Timer();
				timer.startTime(System.currentTimeMillis());//records start time so time can be calculated
				calcLines();//calculates line count
				calcKeys();//calculates how many times key words appear
				timeHold = timer.endTime();//ends timer finds time
				try {
					writeToFile();	//writes to a temporary file
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
		
			}
		});
	}
	private void chooseJavaFile() {//user chooses java file
		//choose file code:
        File F = new File("/Users/KatherineHerman-Williams/eclipse-workspace/hw7/");
        choose = new JFileChooser(lineFile, FileSystemView.getFileSystemView());

        int i = choose.showOpenDialog(null);
        
        if(i == JFileChooser.APPROVE_OPTION){
            lineFile = new File(choose.getSelectedFile().getAbsolutePath());

        }
        else{
            JOptionPane.showMessageDialog(null, "Please choose a Java file.");
        }
	}
	
	private void chooseKeysFile() {//user chooses keywords file
		//choose file code:
        keyFile = new File("/Users/KatherineHerman-Williams/eclipse-workspace/hw7/");
        choose = new JFileChooser(keyFile, FileSystemView.getFileSystemView());

        int i = choose.showOpenDialog(null);
        
        if(i == JFileChooser.APPROVE_OPTION){
            keyFile = new File(choose.getSelectedFile().getAbsolutePath());

        }
        else{
            JOptionPane.showMessageDialog(null, "Please choose a Keywords file.");
        }
	}
	
	private void calcKeys() {//calculates number of times keywords appear in file
		
		  HashTable tempFile = new HashTable(keyFile);//creates a hashtable with values from keywords file
		  
		  Keys keys = new Keys(lineFile, tempFile.hash());//keys class with file and hashtable attributes
		  												//tempFile.hash makes file hashtable	
          try {
        	  keyCount = String.valueOf(keys.keyWordFind());//count of times keywords appear in file
              textFieldNumKeys.setText(keyCount);//displays keycount
          } 
          catch (IOException ioException) {
              ioException.printStackTrace();
          }
		
	}
	
	private void calcLines() {//calculates number of lines of code in file
		
		LineCounter counter = new LineCounter(lineFile);//creates now counter
        try {
        	lineCount = String.valueOf(counter.search());//count of lines
            textFieldNumLines.setText(lineCount);//displays linecount
        } 
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
	
	}
	
	private void writeToFile() throws IOException {//writes to a temporary file
		
		FileWriter fiwr = new FileWriter("/Users/KatherineHerman-Williams/eclipse-workspace/hw7/tempFile.txt");
        fiwr.write("Lines of Code: " + lineCount + System.lineSeparator());
        fiwr.write("Special Words: " + keyCount + System.lineSeparator());
        fiwr.write("Time Till Finish: " + timeHold);
        fiwr.close();

      
	}

}
