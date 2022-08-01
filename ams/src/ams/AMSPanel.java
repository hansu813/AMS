package ams;

import java.awt.*;

public class AMSPanel extends Panel {
	Inventory inventory;
	
	Button inquireB, deleteB, findB, addB, viewB;
	Checkbox typeCB;
	Choice choice;
	Label typeLB, numLB, ownerLB, passwdLB, inmoneyLB, borrowLB, listLB, unitLB;
	TextField numTF, ownerTF, passwdTF, inmoneyTF, borrowTF;
	TextArea listTA;
	
	GridBagConstraints constraint = new GridBagConstraints();
	GridBagLayout layout = new GridBagLayout();
	Account account;
	MinusAccount minusAccount;
	Account tempAccount;
	
	String ownerStr;
	String numStr;
	String accountNum;
	String accountOwner;
	String accountType;
	long inMoney;
	String inMoneyStr;
	long borrow;
	String borrowStr;
	int passwd;
	
	public AMSPanel(Inventory inventory) {
		this.inventory = inventory;
		inquireB = new Button("조 회");
		deleteB = new Button("삭 제");
		findB = new Button("검 색");
		addB = new Button("신규등록");
		viewB = new Button("전체조회");
		
		choice = new Choice();
		Accounts[] accounts = Accounts.values();
		for (Accounts account : accounts) {
			choice.add(account.getAccountType());
		}
		
		inmoneyLB = new Label("입금금액", Label.RIGHT);
		typeLB = new Label("계좌종류", Label.RIGHT);
		numLB = new Label("계좌번호", Label.RIGHT);
		ownerLB = new Label("예금주명", Label.RIGHT);
		passwdLB = new Label("대출금액", Label.RIGHT);
		borrowLB = new Label("계좌목록",Label.LEFT);
		unitLB = new Label("(단위 : 원)", Label.RIGHT);
		listLB = new Label("계좌목록", Label.RIGHT);
		
		numTF = new TextField(20);
		ownerTF = new TextField(20);
		passwdTF = new TextField(20);
		borrowTF = new TextField(20);
		inmoneyTF = new TextField(20);
		
		listTA = new TextArea();
	}
	
	public void init() {
		setLayout(layout);
		
		add(typeLB,    0, 0, 1, 1, GridBagConstraints.NONE);
		add(choice,    1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(numLB,     0, 1, 1, 1, GridBagConstraints.NONE);
		add(numTF,     1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(inquireB,  2, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(deleteB,   3, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(ownerLB,   0, 2, 1, 1, GridBagConstraints.NONE);
		add(ownerTF,   1, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(findB,     2, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(passwdLB,  0, 3, 1, 1, GridBagConstraints.NONE);
		add(passwdTF,  1, 3, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(inmoneyLB, 2, 3, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(inmoneyTF, 3, 3, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(borrowLB,  0, 4, 1, 1, GridBagConstraints.NONE);
		add(borrowTF,  1, 4, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(addB,      2, 4, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(viewB,     3, 4, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
		add(listLB,    0, 5, 1, 1, GridBagConstraints.NONE);
		add(unitLB,    3, 5, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST);
		add(listTA,    0, 6, 4, 1, GridBagConstraints.NONE);
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
	
	public void eventRegist() {
		
	}
}