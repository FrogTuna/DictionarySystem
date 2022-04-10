import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.json.*;
import org.json.simple.JSONObject;


public class Client{

    HashMap<String, String> hashmap;
    JFrame frame;
    JFrame addFrame;
    JFrame deleteFrame;
    JFrame updateFrame;
    JPanel mainpanel;
    JPanel buttonpanel;
    JPanel northpanel;
    JPanel addWordPanel;
    JPanel deleteWordPanel;
    JPanel updateWordPanel;
    JTextField searchField;
    String word;
    String meaning;
    JSONObject newCommand;

    private static String ip = "localhost";
    private static int port = 3500;


    Client(String word,String meaning) throws IOException {


        hashmap = new HashMap<String, String>();
        this.word = word;
        this.meaning = meaning;

        createJson();

        //createGUI();



    }
    
    public void createJson() throws IOException{
    	

        
        
    }

    public void createGUI(){

        frame = new JFrame("Client");
        mainpanel = new JPanel();

        mainpanel.setLayout(new BorderLayout());
        mainpanel.add(setEast(), BorderLayout.EAST);
        mainpanel.add(setNorth(), BorderLayout.NORTH);
        mainpanel.add(setCenter(),BorderLayout.CENTER);

        // And JPanel needs to be added to the JFrame itself!
        frame.add(mainpanel);

        frame.setSize(400,250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
   

    public JPanel setNorth(){

        northpanel = new JPanel();
        searchField = new JTextField(20);
        searchField.setPreferredSize( new Dimension( 200, 35 ) );
        JLabel label = new JLabel();
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = searchField.getText();
                label.setText(input);
            }
        });
        northpanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchButton.setFocusable(false);
        searchButton.setPreferredSize( new Dimension( 100, 35 ) );
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = searchField.getText();
                label.setText(input);
            }
        });
        northpanel.add(searchButton);

        return northpanel;

    }

    public JPanel setEast(){


        buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridLayout(3,1,1,1));
        JButton addButton = new JButton("Add");
        addButton.setFocusable(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                addWord(word,meaning);

            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                removeWord(word,meaning);
            }
        });
        JButton updateButton = new JButton("Update");
        updateButton.setFocusable(false);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                updateWord(word,meaning);
            }
        });

        // Add button to JPanel
        buttonpanel.add(addButton);
        buttonpanel.add(deleteButton);
        buttonpanel.add(updateButton);



        return buttonpanel;

    }

    public JPanel setCenter(){
        JPanel textAreaPanel = new JPanel();
        JTextArea area=new JTextArea("");
        area.setBounds(10,30, 500,120);
        textAreaPanel.add(area);
        textAreaPanel.setLayout(null);
        textAreaPanel.setVisible(true);

        return textAreaPanel;

    }


    public void searchWord(String word,String meaning){

        String text = searchField.getText();

    }


    public void addWord(String word,String meaning){



        hashmap.put(word,meaning);

        addFrame = new JFrame("Add Word");
        addWordPanel = new JPanel();
        addWordPanel.setLayout(new GridLayout(3,1,2,2));

        JPanel internalWordPanel = new JPanel();
        JPanel internalMeaningPanel = new JPanel();
        JPanel internalButtonPanel = new JPanel();

        JLabel wordLabel = new JLabel("word:");
        JTextField wordField = new JTextField(20);
        internalWordPanel.add(wordLabel);
        internalWordPanel.add(wordField);
        addWordPanel.add(internalWordPanel);

        JLabel meaningLabel = new JLabel("meaning:");
        JTextField meaningField = new JTextField(20);
        internalMeaningPanel.add(meaningLabel);
        internalMeaningPanel.add(meaningField);
        addWordPanel.add(internalMeaningPanel);

        JButton addWordButton = new JButton("Add");
        addWordButton.setFocusable(false);
        addWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    if(wordField.getText().length()>0 && meaningField.getText().length()>0){
                        System.out.println(wordField);
                        addFrame.setVisible(false);
                        frame.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(addFrame, "Invalid input for adding word.");
                    }


            }
        });
        internalButtonPanel.add(addWordButton);

        addWordPanel.add(internalButtonPanel);
        addFrame.add(addWordPanel);
        addFrame.setSize(400,250);
        addFrame.setLocationRelativeTo(null);
        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFrame.setVisible(true);


    }


    public void removeWord(String word,String meaning){

        hashmap.remove(word,meaning);



        deleteFrame = new JFrame("Delete Word");
        deleteFrame.setSize(400,250);
        deleteFrame.setLocationRelativeTo(null);
        deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteFrame.setVisible(true);

    }


    public void updateWord(String word,String meaning){

        updateFrame = new JFrame("Update Word");
        updateFrame.setSize(400,250);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateFrame.setVisible(true);


    }




    public static void main(String[] args) throws IOException{
    	
        HashMap<String, String> hashmap= new HashMap<String, String>();

    	try {
    		
        	Socket clientSocket = new Socket(ip,port);
        	
        	try {
        		
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                output.writeUTF("I want to connect!");
                output.flush();

                
        	}
        	finally {
        		if(clientSocket!= null) {
        			clientSocket.close();
        		}
        	}
    		
    	}
    	catch(UnknownHostException var1) {
    		
    		System.out.println("Unknown host");
    		
    	}
    	catch(IOException var1) {
    		
    		System.out.println("ioe exception");
    		
    	}

    }


}
