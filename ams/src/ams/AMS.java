package ams;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AMS {
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		
		Frame frame = new Frame(Account.bankName + "계좌 관리 프로그램");
		AMSPanel panel = new AMSPanel(inventory);
		panel.init();
		panel.eventRegist();
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
