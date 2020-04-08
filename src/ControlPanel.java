
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kristinamantha
 */
class ControlPanel extends JPanel {

    GamePanel gameP;
    TopPanel topP;
    RunningBackPanel runningBP;
    TackleGuysPanel tackleP;

    public ControlPanel() {
        super();
        setLayout(new BorderLayout());

        topP = new TopPanel(runningBP, tackleP, gameP);
        add(topP, BorderLayout.NORTH);

        runningBP = new RunningBackPanel(topP, tackleP);
        add(runningBP, BorderLayout.SOUTH);

        tackleP = new TackleGuysPanel(runningBP, topP, gameP);
        add(tackleP, BorderLayout.SOUTH);

        gameP = new GamePanel(topP, runningBP, tackleP);
        add(gameP, BorderLayout.SOUTH);

    }

}