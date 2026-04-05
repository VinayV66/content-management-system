package cms.gui;

import cms.dao.UserDAO;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnExit;
    private final UserDAO userDAO = new UserDAO();

    public LoginFrame() {
        setTitle("Login - CMS");
        setSize(380, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(40, 30, 80, 25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(130, 30, 180, 25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(40, 70, 80, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130, 70, 180, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(70, 120, 100, 30);
        add(btnLogin);

        btnExit = new JButton("Exit");
        btnExit.setBounds(200, 120, 100, 30);
        add(btnExit);

        btnExit.addActionListener(e -> System.exit(0));

        btnLogin.addActionListener(e -> performLogin());

        txtPassword.addActionListener(e -> btnLogin.doClick());
    }

    private void performLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        boolean ok = userDAO.validateUser(username, password);
        if (ok) {
            dispose();
            new DashboardFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Optional: safe override if you really need it; otherwise you can remove this method.
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
