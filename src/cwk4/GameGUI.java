package cwk4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk
 * @version 20/01/24
 */
public class GameGUI 
{
    private CARE gp = new Tournament("Fred");
    private JFrame myFrame = new JFrame("Game GUI");
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton meetBtn = new JButton("Meet Challenge");
    private JButton viewBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");
    private JButton gameState = new JButton("View Game State");
    private JPanel eastPanel = new JPanel();

    public static void main(String[] args)
    {
        new GameGUI();
    }
    
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        JScrollPane scrollPane = new JScrollPane(listing);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        myFrame.setLayout(new BorderLayout());
        myFrame.add(scrollPane, BorderLayout.CENTER);
        listing.setVisible(false);
        myFrame.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.add(meetBtn);
        eastPanel.add(clearBtn);
        eastPanel.add(quitBtn);
        eastPanel.add(gameState);
        
        clearBtn.addActionListener(new ClearBtnHandler());
        meetBtn.addActionListener(new MeetBtnHandler());
        quitBtn.addActionListener(new QuitBtnHandler());
        gameState.addActionListener(new GameBtnHandler());
        
        meetBtn.setVisible(true);
        clearBtn.setVisible(true);
        quitBtn.setVisible(true);
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);

        // Set the size of the frame before making it visible
        myFrame.setSize(800, 600);  // Set the width to 800 pixels and the height to 600 pixels
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu championMenu = new JMenu("Champions");
        menubar.add(championMenu);
        
        JMenuItem listChampionItem = new JMenuItem("List Champions in reserve");
        listChampionItem.addActionListener(new ListReserveHandler());
        championMenu.add(listChampionItem);

        JMenuItem listTeamItem = new JMenuItem("List Champions in Team");
        listTeamItem.addActionListener(new ListTeamHandler());
        championMenu.add(listTeamItem);

        JMenuItem viewChampionItem = new JMenuItem("View Champion");
        viewChampionItem.addActionListener(new viewChampionHandler());
        championMenu.add(viewChampionItem);

        JMenuItem enterChampionItem = new JMenuItem("Enter Champion");
        enterChampionItem.addActionListener(new enterChampionHandler());
        championMenu.add(enterChampionItem);


        // challenges
        JMenu challengesMenu = new JMenu("Challenges");
        menubar.add(challengesMenu);

        JMenuItem listChallengesItem = new JMenuItem("List Challenges");
        listChallengesItem.addActionListener(new ListChallengesHandler());
        challengesMenu.add(listChallengesItem);
 
    }

    private class viewChampionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String inputValue = JOptionPane.showInputDialog("Champion name ?: ");
            String result = gp.getChampionDetails(inputValue);
            JOptionPane.showMessageDialog(myFrame,result);
        }
    }

    private class enterChampionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int result = -1;
            String answer = "no such champion";
            String inputValue = JOptionPane.showInputDialog("Enter a champion: ");
            result = gp.enterChampion(inputValue);
            switch (result)
            {
                case 0:answer = "Champion has entered the viziers team"; break;
                case 1:answer = "Champion is not in reserve";break;
                case 2:answer = "Not enough money in the treasury";break;
            }

            JOptionPane.showMessageDialog(myFrame,answer);
        }
    }

    private class ListReserveHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = gp.getReserve();
            listing.setText(xx);
        }
    }

    private class ListChallengesHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String yy = gp.getAllChallenges();
            listing.setText(yy);
        }
    }

    private class ListTeamHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = gp.getTeam();
            listing.setText(xx);
        }
    }
    
   
    private class ClearBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setText(" ");
        }
    }
    
    private class MeetBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int result = -1;
            String answer = "no such challenge";
            String inputValue = JOptionPane.showInputDialog("Challenge number ?: ");
            int num = Integer.parseInt(inputValue);
            result = gp.meetChallenge(num);
            switch (result)
            {
                case 0:answer = "challenge won by champion"; break;
                case 1:answer = "challenge lost on skills, champion disqualified";break;
                case 2:answer = "challenge lost as no suitable champion is available";break;
                case 3:answer = "challenge lost and vizier completely defeated";break;
            }
            
            JOptionPane.showMessageDialog(myFrame,answer);    
        }
    }
    
    private class QuitBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int answer = JOptionPane.showConfirmDialog(myFrame,
                "Are you sure you want to quit?","Finish",
                JOptionPane.YES_NO_OPTION);
            // closes the application
            if (answer == JOptionPane.YES_OPTION)
            {
                System.exit(0); //closes the application
            }              
        }
    }

    private class GameBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String result = gp.toString();
            listing.setText(result);
        }
    }
    
}
   
