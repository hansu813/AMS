package ams;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * AMS의 AWT클래스
 * 
 * @author 김한수
 *
 */
public class AMSPanel extends Panel {
	Inventory inventory;

	Button inquireB, deleteB, findB, addB, viewAllB;
	Checkbox typeCB;
	Choice choice;
	Label typeLB, numLB, ownerLB, passwdLB, inmoneyLB, borrowLB, listLB, unitLB;
	TextField numTF, ownerTF, passwdTF, inmoneyTF, borrowTF;
	TextArea listTA;

	GridBagConstraints constraint = new GridBagConstraints();
	GridBagLayout layout = new GridBagLayout();

	/** 생성자에서 초기화 */
	public AMSPanel(Inventory inventory) {
		this.inventory = inventory;
		inquireB = new Button("조 회");
		deleteB = new Button("삭 제");
		findB = new Button("검 색");
		addB = new Button("신규등록");
		viewAllB = new Button("전체조회");

		choice = new Choice();
		Accounts[] accounts = Accounts.values();
		for (Accounts account : accounts) {
			choice.add(account.getAccountType());
		}

		inmoneyLB = new Label("입금금액", Label.RIGHT);
		typeLB = new Label("계좌종류", Label.RIGHT);
		numLB = new Label("계좌번호", Label.RIGHT);
		ownerLB = new Label("예금주명", Label.RIGHT);
		passwdLB = new Label("비밀번호", Label.RIGHT);
		borrowLB = new Label("대출금액", Label.LEFT);
		unitLB = new Label("(단위 : 원)", Label.RIGHT);
		listLB = new Label("계좌목록", Label.RIGHT);

		numTF = new TextField(20);
		ownerTF = new TextField(20);
		passwdTF = new TextField(20);
		borrowTF = new TextField(20);
		inmoneyTF = new TextField(20);

		listTA = new TextArea();
	}

	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, int fill) {
		constraint.gridx = gridx;
		constraint.gridy = gridy;
		constraint.gridwidth = gridwidth;
		constraint.gridheight = gridheight;
		constraint.fill = fill;
		constraint.insets = new Insets(2, 2, 2, 2);
		layout.setConstraints(component, constraint);
		add(component);
	}

	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, int fill,
			int direction) {
		constraint.gridx = gridx;
		constraint.gridy = gridy;
		constraint.gridwidth = gridwidth;
		constraint.gridheight = gridheight;
		constraint.anchor = direction;
		constraint.fill = fill;
		constraint.insets = new Insets(2, 2, 2, 2);
		layout.setConstraints(component, constraint);
		add(component);
	}

	private void appendBasic() {
		String basic = String.format("%-9s|\t%-12s|\t%-6s|\t%-8s|\t%-8s|", "계좌종류", "계좌번호", "예금주명", "현재잔액", "대출금액");
		listTA.setText("");
		listTA.append("-------------------------------------------------------------------------\n");
		listTA.append(basic + "\n");
		listTA.append("=========================================================================\n");
	}

	private Account makeAccount(String accountType, String accountNum, String accountOwner, int passwd) {
		Account account = null;
		if (accountType.equals("입출금 계좌"))
			account = new Account(accountNum, accountOwner, passwd);
		if (accountType.equals("마이너스 계좌")) {
			account = new MinusAccount(accountNum, accountOwner, passwd);
		}
		return account;
	}

	public void init() {
		setLayout(layout);

		add(typeLB, 0, 0, 1, 1, GridBagConstraints.NONE);
		add(choice, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(numLB, 0, 1, 1, 1, GridBagConstraints.NONE);
		add(numTF, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(inquireB, 2, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(deleteB, 3, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(ownerLB, 0, 2, 1, 1, GridBagConstraints.NONE);
		add(ownerTF, 1, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(findB, 2, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(passwdLB, 0, 3, 1, 1, GridBagConstraints.NONE);
		add(passwdTF, 1, 3, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(inmoneyLB, 2, 3, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(inmoneyTF, 3, 3, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(borrowLB, 0, 4, 1, 1, GridBagConstraints.NONE);
		add(borrowTF, 1, 4, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(addB, 2, 4, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(viewAllB, 3, 4, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(listLB, 0, 5, 1, 1, GridBagConstraints.NONE);
		add(unitLB, 3, 5, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST);
		add(listTA, 0, 6, 4, 1, GridBagConstraints.NONE);
	}

	/** 이벤트 관리 메소드 */
	public void eventRegist() {
		class ActionEventListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object eventSource = e.getSource();
				if (eventSource == addB) {	// 계좌 추가
					appendBasic();
					Account account = null;
					account = makeAccount(choice.getSelectedItem(), numTF.getText(), ownerTF.getText(),
							Integer.parseInt(passwdTF.getText()));
					try {
						inventory.create(account);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (eventSource == inquireB) {	// 계좌번호로 조회
					appendBasic();
					String num = "";
					num = numTF.getText();
					String accountText = inventory.findNum(num).toString();
					listTA.append(accountText);
				}
				if (eventSource == findB) {		// 예금주명으로 조회
					List<Account> list = null;
					appendBasic();
					String owner = "";
					owner = ownerTF.getText();
					list = inventory.findOwner(owner);
					for (int i = 0; i < list.size(); i++) {
						listTA.append(list.get(i).toString() + "\n");
					}
				}
				if (eventSource == deleteB) {	// 삭제
					appendBasic();
					inventory.remove(numTF.getText());
				}
				if (eventSource == viewAllB) {		// 전체 조회
					appendBasic();
					List<Account> list = null;
					list = inventory.readList();
					for (int i = 0; i < list.size(); i++) {
						listTA.append((list.get(i).toString()) + "\n");
					}
				}
				if(eventSource == inmoneyTF) {
					Account account = null;
					account = inventory.findNum(numTF.getText());
					try {
						account.deposit(Long.parseLong(inmoneyTF.getText()));
						inventory.update(account);
					} catch (NumberFormatException | InvalidException | IOException e1) {
						e1.printStackTrace();
					}
				}
				if(eventSource == borrowTF) {
					Account account = null;
					account = inventory.findNum(numTF.getText());
					if (account instanceof MinusAccount) {
						try {
							((MinusAccount) account).loan(Long.parseLong(borrowTF.getText()));
						} catch (InvalidException e1) {
							e1.getMessage();
						}
					}
				}
			}
		}
		ActionEventListener listener = new ActionEventListener();
		inquireB.addActionListener(listener);
		addB.addActionListener(listener);
		findB.addActionListener(listener);
		viewAllB.addActionListener(listener);
		deleteB.addActionListener(listener);
		inmoneyTF.addActionListener(listener);
		borrowTF.addActionListener(listener);
	}
}