package ams;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;

/**
 * 메인 화면
 * @author 김한수
 *
 */
public class AMS {
	private static Inventory in = null;
	private static FileUtil file = null;
	public static void main(String[] args) {
		try {
			file = new FileUtil();
			if (file.getRecordCount() != 0) {
				in = new Inventory(file.road());
			} else {
				in = new Inventory();
			}

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
					try {
						file.save(in);
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						System.exit(0);
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
