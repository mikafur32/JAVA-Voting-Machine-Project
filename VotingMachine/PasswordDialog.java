import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class PasswordDialog extends JDialog {
    private JPasswordField password;
    private static ArrayList<Integer> SSList = new ArrayList<Integer>();
    private final int LENGTH_OF_SS_NUM = 9;
    private JLabel label;
    private GridBagConstraints layoutConstraints;

    public PasswordDialog(Frame owner, Ballot ballot) {
        super(owner, "Social Security number", true);
        password = new JPasswordField(LENGTH_OF_SS_NUM);
        setSize(400, 150);
        buildUI(ballot);

        final PasswordDialog bf = this;
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(bf,
                                "Must enter a Social Security Number.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    private void buildUI(Ballot ballot) {
        //Frame setup

        //GridBag setup
        setLayout(new GridBagLayout());

        //Label setup
        label = new JLabel("Enter your 9-digit Social Security Number: ");

        //Constraints for label
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.insets = new Insets(10, 10, 10, 10);
        add(label, layoutConstraints);

        //Constraints for password
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        layoutConstraints.insets = new Insets(10, 10, 10, 10);
        add(password, layoutConstraints);

        final PasswordDialog pass = this;

        //password Button
        JButton passButton = new JButton("Enter");
        passButton.addActionListener
                (new ActionListener() {
                     public void actionPerformed(ActionEvent event) {
                         String userIn = String.valueOf(password.getPassword());
                         if (isPasswordValid(userIn)) {
                             SSList.add(Integer.parseInt(userIn));

                             dispose();
                         } else {
                             JOptionPane.showMessageDialog(pass,
                                     "Invalid Social Security Number",
                                     "Error",
                                     JOptionPane.ERROR_MESSAGE);
                         }
                     }
                 }
                );
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 0;
        layoutConstraints.insets = new Insets(10, 10, 10, 10);
        add(passButton, layoutConstraints);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }



    private boolean isPasswordValid(String userIn)
    {
        try
        {
            Integer.parseInt(userIn);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return userIn.length() == 9 && !SSList.contains(Integer.parseInt(userIn));
    }

    public static ArrayList<Integer> getSSList()
    {
        return SSList;
    }

    public static void main(String[] args)
    {
        PasswordDialog pass = new PasswordDialog(null, null);
        pass.setVisible(true);
        System.out.println(getSSList());

        PasswordDialog pass2 = new PasswordDialog(null, null);
        pass.setVisible(true);
        System.out.println(getSSList());
    }
}



