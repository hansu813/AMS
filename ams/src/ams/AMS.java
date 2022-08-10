package ams;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * AMS project_MainClass
 * 
 * @author 김한수
 *
 */
public class AMS {
	public static void main(String[] args) {
		FileUtil file = null;
		Inventory in = null;
		try {
			file = new FileUtil();
			in = new Inventory(file);

			Frame frame = new Frame(Account.BANKNAME + "계좌 관리 프로그램");
			AMSPanel panel = new AMSPanel(in);
			panel.init();
			panel.eventRegist();
			frame.add(panel, BorderLayout.CENTER);
			frame.setSize(600, 400);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			file.save(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
