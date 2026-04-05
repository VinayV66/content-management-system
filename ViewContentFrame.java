package cms.gui;

import cms.dao.ContentDAO;
import cms.model.Content;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewContentFrame extends JFrame {

    private final JTable table;
    private final DefaultTableModel model;
    private final ContentDAO contentDAO = new ContentDAO();

    public ViewContentFrame() {
        setTitle("View Content");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        model = new DefaultTableModel(new Object[]{"ID", "Title", "Body", "Created At"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 20, 740, 400);
        add(scroll);

        loadContents();
    }

    private void loadContents() {
        model.setRowCount(0);
        List<Content> contents = contentDAO.getAllContents();
        contents.stream().forEach((c) -> {
            model.addRow(new Object[]{c.getId(), c.getTitle(), c.getBody(), c.getCreatedAt()});
        });
    }
}
