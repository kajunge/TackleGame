
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author kristinamantha
 */
class TopPanel extends JPanel implements ChangeListener, ActionListener {

    private Timer tim_slow;
    private Timer tim_fast;
    private int limit = 0;
    private int delay = 0;
    // private BannerPanel bjp;
    private RunningBackPanel rjp;
    int tackles = 0;
    int touchdowns = 0;

//team attributes 
    private JButton scoreButton, playButton;
    private JLabel teamLabel;
    private String[] teamStrings = {"Choose a team", "Bears", "Ravens", "Broncos"};
    private String teamChoice = "";
    private JComboBox teamChoices;
//imageicons
    private ImageIcon bearsRB = new ImageIcon("images/bearsRB.png");
    private ImageIcon broncosRB = new ImageIcon("images/broncosRB.png");
    private ImageIcon ravensRB = new ImageIcon("images/ravensRB.png");
    private ImageIcon ravensBanner = new ImageIcon("images/ravenBanner.png");
    private ImageIcon bearsBanner = new ImageIcon("images/bearsBanner.png");
    private ImageIcon broncosBanner = new ImageIcon("images/broncosBanner.png");
    private ImageIcon defaultBanner = new ImageIcon("images/defaultBanner.png");
/////////
    private JLabel bearsLabel, ravensLabel, broncosLabel;

//Speed attributes
    private JLabel speedLabel;
    private JSlider js1;
    private int count;

    //score attributes
    private JButton score;
    //score attributes
    private JButton attempts;
    private TackleGuysPanel tgjp;
    GamePanel gjp;

    public TopPanel(RunningBackPanel importedRBP, TackleGuysPanel importedTG, GamePanel importedGP) {
        super();
        this.setPreferredSize(new Dimension(1200, 303));
        setBackground(Color.WHITE);

        rjp = importedRBP;
        tgjp = importedTG;
        gjp = importedGP;
//=====================================
// Adding label to team choice combo box   
//=====================================         
        teamLabel = new JLabel("Choose a team: ");
        add(teamLabel, BorderLayout.PAGE_START);
//=====================================
// Adding the team choices combo box 
//=====================================          
        teamChoices = new JComboBox(getTeamStrings());
        teamChoices.setBounds(470, 50, 100, 30);
        teamChoices.setSelectedIndex(0);
        teamChoices.setOpaque(true);
        teamChoices.addActionListener(this);
        add(teamChoices, BorderLayout.PAGE_START);
//=====================================
// Adding the speed slider and label 
//=====================================  
        speedLabel = new JLabel("Set game speed:   ");
        add(speedLabel, BorderLayout.LINE_START);

        js1 = new JSlider(JSlider.HORIZONTAL, 0, 10, 8);
        js1.setMajorTickSpacing(5);
        js1.setMajorTickSpacing(1);
        js1.setPaintTicks(true);
        js1.setPaintLabels(true);
        Hashtable labelTable = new Hashtable();
        labelTable.put(0, new JLabel("Fast"));
        labelTable.put(10, new JLabel("Slow"));
        js1.setLabelTable(labelTable);
        js1.setOpaque(false);
        add(js1, BorderLayout.CENTER);
        js1.addChangeListener(this);
//=====================================
// Adding the play game button    
//=====================================   
        playButton = new JButton("Play!");
        playButton.setOpaque(true);
        playButton.setEnabled(false);
        add(playButton, BorderLayout.CENTER);
//=====================================
// Adding the score info     
//=====================================         
        score = new JButton("Touchdowns: " + "  " + touchdowns + "  Tackles: " + tackles + "  ");
        score.setOpaque(true);
        add(score, BorderLayout.LINE_END);
//=====================================
// Adding the field image to the background    
//=====================================          
        teamLabel = new JLabel();
        teamLabel.setIcon(defaultBanner);
        add(teamLabel);
        teamLabel.setVisible(true);
        teamLabel.setOpaque(false);
    }

    public void showMessage() {

        JOptionPane.showMessageDialog(null, "<html><center>How to Play:<br>"
                + "First choose a team, then click play.<br>"
                + "You can adjust the game speed using the slider.<br>"
                + "Object of the game: Don't get tackled.</center></html>");
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object obj = e.getSource();
        if (obj == getJs1()) {
            setCount(getJs1().getValue());
            getSpeedLabel().setText("Set game speed:   " + getCount() + "   ");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();

        Object obj = e.getSource();

        setTeamChoice((String) cb.getSelectedItem());

        if (cb.getSelectedItem().equals("Bears")) {
            setTeamChoice("Bears");
            teamChoices.setEnabled(false);
            playButton.setEnabled(true);
            this.setBackground(Color.BLACK);
            getTeamLabel().setIcon(getBearsBanner());
            getTeamChoices().setBackground(Color.WHITE);
            getTeamChoices().setForeground(Color.BLACK);
            getJs1().setBackground(Color.BLACK);
            getJs1().setForeground(Color.WHITE);
            getSpeedLabel().setForeground(Color.WHITE);
            teamLabel.setForeground(Color.WHITE);
            teamLabel.setBackground(Color.WHITE);
        }
        if (cb.getSelectedItem().equals("Ravens")) {
            setTeamChoice("Ravens");
            this.setBackground(Color.BLACK);
            teamChoices.setEnabled(false);
            playButton.setEnabled(true);
            getTeamLabel().setIcon(getRavensBanner());
            getTeamChoices().setBackground(Color.WHITE);
            getTeamChoices().setForeground(Color.BLACK);
            getJs1().setBackground(Color.BLACK);
            getJs1().setForeground(Color.WHITE);
            getSpeedLabel().setForeground(Color.WHITE);
            teamLabel.setForeground(Color.WHITE);
        }
        if (cb.getSelectedItem().equals("Broncos")) {
            setTeamChoice("Broncos");
            this.setBackground(Color.BLACK);
            teamChoices.setEnabled(false);
            playButton.setEnabled(true);
            getTeamLabel().setIcon(getBroncosBanner());
            getTeamChoices().setBackground(Color.WHITE);
            getTeamChoices().setForeground(Color.BLACK);
            getJs1().setBackground(Color.BLACK);
            getJs1().setForeground(Color.WHITE);
            getSpeedLabel().setForeground(Color.WHITE);
            teamLabel.setForeground(Color.WHITE);
        }
        if (cb.getSelectedItem().equals("Choose a team")) {
            this.setBackground(Color.WHITE);
            teamChoices.setEnabled(true);
            playButton.setEnabled(false);
            getTeamLabel().setIcon(getDefaultBanner());
            getJs1().setForeground(Color.BLACK);
            getSpeedLabel().setForeground(Color.BLACK);
        }
    }

    /**
     * @return the teamStrings
     */
    public String[] getTeamStrings() {
        return teamStrings;
    }

    /**
     * @param teamStrings the teamStrings to set
     */
    public void setTeamStrings(String[] teamStrings) {
        this.setTeamStrings(teamStrings);
    }

    /**
     * @return the teamChoice
     */
    public String getTeamChoice() {
        return teamChoice;
    }

    /**
     * @param teamChoice the teamChoice to set
     */
    public void setTeamChoice(String teamChoice) {
        this.teamChoice = teamChoice;
    }

    /**
     * @return the teamChoices
     */
    public JComboBox getTeamChoices() {
        return teamChoices;
    }

    /**
     * @param teamChoices the teamChoices to set
     */
    public void setTeamChoices(JComboBox teamChoices) {
        this.teamChoices = teamChoices;
    }

    /**
     * @return the rjp
     */
    public RunningBackPanel getRjp() {
        return rjp;
    }

    /**
     * @param rjp the rjp to set
     */
    public void setRjp(RunningBackPanel rjp) {
        this.rjp = rjp;
    }

    /**
     * @return the scoreButton
     */
    public JButton getScoreButton() {
        return scoreButton;
    }

    /**
     * @param scoreButton the scoreButton to set
     */
    public void setScoreButton(JButton scoreButton) {
        this.scoreButton = scoreButton;
    }

    /**
     * @return the bearsRB
     */
    public ImageIcon getBearsRB() {
        return bearsRB;
    }

    /**
     * @param bearsRB the bearsRB to set
     */
    public void setBearsRB(ImageIcon bearsRB) {
        this.bearsRB = bearsRB;
    }

    /**
     * @return the speedLabel
     */
    public JLabel getSpeedLabel() {
        return speedLabel;
    }

    /**
     * @param speedLabel the speedLabel to set
     */
    public void setSpeedLabel(JLabel speedLabel) {
        this.speedLabel = speedLabel;
    }

    /**
     * @return the js1
     */
    public JSlider getJs1() {
        return js1;
    }

    /**
     * @param js1 the js1 to set
     */
    public void setJs1(JSlider js1) {
        this.js1 = js1;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @param score the score to set
     */
    public void setScore(JButton score) {
        this.score = score;
    }

    /**
     * @return the attempts
     */
    public JButton getAttempts() {
        return attempts;
    }

    /**
     * @param attempts the attempts to set
     */
    public void setAttempts(JButton attempts) {
        this.attempts = attempts;
    }

    /**
     * @return the teamLabel
     */
    public JLabel getTeamLabel() {
        return teamLabel;
    }

    /**
     * @param teamLabel the teamLabel to set
     */
    public void setTeamLabel(JLabel teamLabel) {
        this.teamLabel = teamLabel;
    }

    /**
     * @return the broncosRB
     */
    public ImageIcon getBroncosRB() {
        return broncosRB;
    }

    /**
     * @param broncosRB the broncosRB to set
     */
    public void setBroncosRB(ImageIcon broncosRB) {
        this.broncosRB = broncosRB;
    }

    /**
     * @return the ravensRB
     */
    public ImageIcon getRavensRB() {
        return ravensRB;
    }

    /**
     * @param ravensRB the ravensRB to set
     */
    public void setRavensRB(ImageIcon ravensRB) {
        this.ravensRB = ravensRB;
    }

    /**
     * @return the ravensBanner
     */
    public ImageIcon getRavensBanner() {
        return ravensBanner;
    }

    /**
     * @param ravensBanner the ravensBanner to set
     */
    public void setRavensBanner(ImageIcon ravensBanner) {
        this.ravensBanner = ravensBanner;
    }

    /**
     * @return the bearsBanner
     */
    public ImageIcon getBearsBanner() {
        return bearsBanner;
    }

    /**
     * @param bearsBanner the bearsBanner to set
     */
    public void setBearsBanner(ImageIcon bearsBanner) {
        this.bearsBanner = bearsBanner;
    }

    /**
     * @return the broncosBanner
     */
    public ImageIcon getBroncosBanner() {
        return broncosBanner;
    }

    /**
     * @param broncosBanner the broncosBanner to set
     */
    public void setBroncosBanner(ImageIcon broncosBanner) {
        this.broncosBanner = broncosBanner;
    }

    /**
     * @return the defaultBanner
     */
    public ImageIcon getDefaultBanner() {
        return defaultBanner;
    }

    /**
     * @param defaultBanner the defaultBanner to set
     */
    public void setDefaultBanner(ImageIcon defaultBanner) {
        this.defaultBanner = defaultBanner;
    }

    /**
     * @return the bearsLabel
     */
    public JLabel getBearsLabel() {
        return bearsLabel;
    }

    /**
     * @param bearsLabel the bearsLabel to set
     */
    public void setBearsLabel(JLabel bearsLabel) {
        this.bearsLabel = bearsLabel;
    }

    /**
     * @return the ravensLabel
     */
    public JLabel getRavensLabel() {
        return ravensLabel;
    }

    /**
     * @param ravensLabel the ravensLabel to set
     */
    public void setRavensLabel(JLabel ravensLabel) {
        this.ravensLabel = ravensLabel;
    }

    /**
     * @return the broncosLabel
     */
    public JLabel getBroncosLabel() {
        return broncosLabel;
    }

    /**
     * @param broncosLabel the broncosLabel to set
     */
    public void setBroncosLabel(JLabel broncosLabel) {
        this.broncosLabel = broncosLabel;
    }

    /**
     * @return the tim_slow
     */
    public Timer getTim_slow() {
        return tim_slow;
    }

    /**
     * @param tim_slow the tim_slow to set
     */
    public void setTim_slow(Timer tim_slow) {
        this.tim_slow = tim_slow;
    }

    /**
     * @return the tim_fast
     */
    public Timer getTim_fast() {
        return tim_fast;
    }

    /**
     * @param tim_fast the tim_fast to set
     */
    public void setTim_fast(Timer tim_fast) {
        this.tim_fast = tim_fast;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * @return the playButton
     */
    public JButton getPlayButton() {
        return playButton;
    }

    /**
     * @param playButton the playButton to set
     */
    public void setPlayButton(JButton playButton) {
        this.playButton = playButton;
    }

    /**
     * @return the tgjp
     */
    public TackleGuysPanel getTgjp() {
        return tgjp;
    }

    /**
     * @param tgjp the tgjp to set
     */
    public void setTgjp(TackleGuysPanel tgjp) {
        this.tgjp = tgjp;
    }

    /**
     * @return the touchdowns
     */
    public int getTackles() {
        return tackles;
    }

    /**
     * @param tackles the tackles to set
     */
    public void setTackles(int tackles) {
        this.tackles = tackles;
    }

    /**
     * @return the touchdowns
     */
    public int getTouchdowns() {
        return touchdowns;
    }

    /**
     * @param touchdowns the touchdowns to set
     */
    public void setTouchdowns(int touchdowns) {
        this.touchdowns = touchdowns;
    }

    /**
     * @return the score
     */
    public JButton getScore() {
        return score;
    }

}
