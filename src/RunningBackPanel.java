
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author kristinamantha
 */
public class RunningBackPanel extends JPanel {

    private ImageIcon rb = new ImageIcon("images/rb.png");
    private ImageIcon bearsRB = new ImageIcon("images/bearsRB.png");
    private ImageIcon broncosRB = new ImageIcon("images/broncosRB.png");
    private ImageIcon ravensRB = new ImageIcon("images/ravensRB.png");
    private JButton runningBack;
    private int x = 20;
    private int y = 30;
    private int velX = 0;
    private int velY = 0;
    private Rectangle r1;
    private String teamChoice = "";
    private TopPanel tjp;
    TackleGuysPanel tgjp;

    public RunningBackPanel(TopPanel importedTP, TackleGuysPanel importedTGP) {
        super();
        tjp = importedTP;
        tgjp = importedTGP;
        teamChoice = tjp.getTeamChoice();
        //  tjp.getPlayButton().addActionListener(this);
        //  tjp.getTeamChoices().addActionListener(this);
//=====================================
// Creates the running back       
//===================================== 
        Rectangle r1 = new Rectangle(98, 230, 75, 90);

//=====================================
// Creates the running back       
//===================================== 
        runningBack = new JButton();
        runningBack.setBounds(r1);
        runningBack.setIcon(rb);
        runningBack.setVisible(false);
        runningBack.setOpaque(false);
        runningBack.setContentAreaFilled(false);
        runningBack.setBorderPainted(false);
        add(runningBack);

        tjp.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj == tjp.getPlayButton()) {
                    runningBack.setVisible(true);

                }
            }

        });

        tjp.getTeamChoices().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                setTeamChoice((String) tjp.getTeamChoices().getSelectedItem());

                if (obj == tjp.getTeamChoices()) {
                    if (tjp.getTeamChoices().getSelectedItem().equals("Bears")) {
                        setTeamChoice("Bears");
                        runningBack.setIcon(bearsRB);
                    }
                }
                if (obj == tjp.getTeamChoices()) {
                    if (tjp.getTeamChoices().getSelectedItem().equals("Ravens")) {
                        setTeamChoice("Ravens");
                        runningBack.setIcon(ravensRB);
                    }
                }
                if (obj == tjp.getTeamChoices()) {
                    if (tjp.getTeamChoices().getSelectedItem().equals("Broncos")) {
                        setTeamChoice("Broncos");
                        runningBack.setIcon(broncosRB);
                    }
                }
                if (obj == tjp.getTeamChoices()) {
                    if (tjp.getTeamChoices().getSelectedItem().equals("Choose a team")) {
                        setBackground(Color.WHITE);
                    }

                }
            }

        });
    }

    /**
     * @return the runningBack
     */
    public JButton getRunningBack() {
        return runningBack;
    }

    /**
     * @param runningBack the runningBack to set
     */
    public void setRunningBack(JButton runningBack) {
        this.runningBack = runningBack;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the velX
     */
    public int getVelX() {
        return velX;
    }

    /**
     * @param velX the velX to set
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * @return the velY
     */
    public int getVelY() {
        return velY;
    }

    /**
     * @param velY the velY to set
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    /**
     * @return the r1
     */
    public Rectangle getR1() {
        return r1;
    }

    /**
     * @param r1 the r1 to set
     */
    public void setR1(Rectangle r1) {
        this.r1 = r1;
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
     * @return the tjp
     */
    public TopPanel getTjp() {
        return tjp;
    }

    /**
     * @param tjp the tjp to set
     */
    public void setTjp(TopPanel tjp) {
        this.tjp = tjp;
    }
}

