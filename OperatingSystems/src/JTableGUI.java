import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;

public class JTableGUI extends JPanel {

    public JTableGUI(int[] processID, int[] arrivalTimeInReady, int[] completionTime, int[] memoryNeeded, int[] criticalSection) {
        super(new GridLayout(1,0));
        String[] columnNames = {"Process ID",
                "STATE",
                "ARRIVAL_TIME",
                "COMPLETION_TIME",
                "MEMORY_NEEDED",
                "CRITICAL_SECTION"
                };

        Object[][] data = {};

        DefaultTableModel listModel = new DefaultTableModel(data, columnNames);

        for(int i = 0; i < processID.length; i++) {

            int pid = processID[i];
            int arrivalTime = arrivalTimeInReady[i];
            int compTime = completionTime[i];
            int memory = memoryNeeded[i];
            int critical = criticalSection[i];
            listModel.addRow(new Object[]{pid, "RUNNING", arrivalTime, compTime, memory, critical});
        }

        JTable listTable;
        listTable = new JTable(listModel);
        listTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        listTable.setCellEditor(null);
        listTable.setBounds(37, 143, 397, 183);

        JFrame frame = new JFrame();
        frame.add(listTable);
        frame.setVisible(true);
        frame.pack();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(listTable);
        add(scrollPane);
    }
}