import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.io.*;

class editor extends JFrame implements ActionListener {

    //creating the TextArea
    JTextArea t;
    //creating the frame to accumdate the textArea and the menubar
    JFrame f;

    editor() {

        //Initialising the frame
        f = new JFrame("Text Editor");

        //Setting the overall theme of editor
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetallicLool&Feel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {

        }
        //Initialising the textArea
        t = new JTextArea();

        //Initialising the MenuBar
        JMenuBar m = new JMenuBar();

        //#1initialising file menu
        JMenu f1 = new JMenu("File");

        //creating the individual menu items
        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Open");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Print");

        //adding actionListener to File menu
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        //Adding menuItems buttons to file menu
        f1.add(m1);
        f1.add(m2);
        f1.add(m3);
        f1.add(m4);


        //#2initialising Edit menu
        JMenu f2 = new JMenu("Edit");

        //creating the individual Edit menu items
        JMenuItem m5 = new JMenuItem("Cut");
        JMenuItem m6 = new JMenuItem("Copy");
        JMenuItem m7 = new JMenuItem("Paste");

        //adding actionListener to edit menu
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);

        //Adding menuItems buttons to Edit menu
        f2.add(m5);
        f2.add(m6);
        f2.add(m7);

        //creating exit menu item
        JMenuItem close = new JMenuItem("Exit");

        close.addActionListener(this);

        //Adding Menu like File,Edit,Exit to MenuBar
        m.add(f1);
        m.add(f2);
        m.add(close);

        //adding textArea 't' and  MenuBar 'm' to frame 'f'
        f.add(t);
        f.setJMenuBar(m);
        //setting size of frame
        f.setSize(700, 700);
        f.show();


    }

    public void actionPerformed(ActionEvent e) {
         String s = e.getActionCommand();


            if(s.equals("New")){
                t.setText("");  //if parenthesis is null it delets the old text
            }
            else if(s.equals("Open")){

               JFileChooser j = new JFileChooser("c:"); // it will point ot the folder "c' here,whatever inside the ()
               int r = j.showOpenDialog(null);

               if(r == JFileChooser.APPROVE_OPTION){

                   File fi = new File(j.getSelectedFile().getAbsolutePath());

                   try{
                       String s1 = "";
                       String s2 = "";

                       FileReader fr = new FileReader(fi);
                       BufferedReader br = new BufferedReader(fr);

                       s2 = br.readLine();

                       while((s1 = br.readLine())!= null ){
                           s2 = s2 +"\n"+s1 ;
                       }

                       t.setText(s2);
                   }
                   catch(Exception et){
                       JOptionPane.showMessageDialog(f,et.getMessage());
                   }
               }
               else{
                   JOptionPane.showMessageDialog(f,"Operation Cancelled");
               }
            }
            else if(s.equals("Save")){
                JFileChooser j = new JFileChooser("c:"); // it will point ot the folder "c' here,whatever inside the ()
                int r = j.showSaveDialog(null);

                if(r == JFileChooser.APPROVE_OPTION){

                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    try{
                        FileWriter wr = new FileWriter(fi);

                        BufferedWriter bw = new BufferedWriter(wr);

                        bw.write(t.getText());

                        bw.flush();
                        bw.close();
                    }
                    catch(Exception et){
                        JOptionPane.showMessageDialog(f,et.getMessage());
                    }
                }
                else{
                    JOptionPane.showMessageDialog(f,"Operation Cancelled");
                }
            }
            else if(s.equals("Print")){
                try{
                    t.print();
                }
                catch(Exception et){
                    JOptionPane.showMessageDialog(f,et.getMessage());
                }
            }
            else if(s.equals("Cut")){
                t.cut();
            }
            else if(s.equals("Copy")){
                t.copy();
            }
            else if(s.equals("Paste")){
                t.paste();
            }
            else if(s.equals("Exit")){
               f.setVisible(false);
            }

    }

    public static void main(String [] args) {
        editor e = new editor();

    }
}