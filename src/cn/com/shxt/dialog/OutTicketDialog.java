package cn.com.shxt.dialog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import cn.com.shxt.util.JdbcUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class OutTicketDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private String name;
	private String start_time;
	private String end_time;
	private int onshow_id ;
	private float price ;
	private String time ;
	private String seat_1 ;
	private float banlance ;
	private float money ;
	private JdbcUtil ju=new JdbcUtil();
	private MessageBox box;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Label label_8;
	private Label label_9;
	private Text text_8;
	private Text text_9;
	private Label label_10;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OutTicketDialog(Shell parent, int style) {
		super(parent, style);
		setText("³öÆ±");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String name , String start_time , String end_time , int onshow_id , 
			float price , String time , String seat_1 , float banlance , float money) {
		this.name = name ;
		this.start_time = start_time ;
		this.end_time = end_time ;
		this.onshow_id = onshow_id ;
		this.price = price ;
		this.time = time;
		this.seat_1 = seat_1 ;
		this.banlance = banlance ;
		this.money = money ;
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(577, 441);
		shell.setText(getText());
		
		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label.setText("\u7F16      \u7801\uFF1A");
		label.setBounds(80, 38, 61, 17);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_1.setText("\u7535\u5F71\u540D\u79F0\uFF1A");
		label_1.setBounds(80, 82, 61, 17);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_2.setText("\u8D2D\u4E70\u65F6\u95F4\uFF1A");
		label_2.setBounds(80, 130, 61, 17);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_3.setText("\u7968     \u4EF7\uFF1A");
		label_3.setBounds(80, 183, 61, 17);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_4.setText("\u5F71     \u5385\uFF1A");
		label_4.setBounds(336, 42, 61, 17);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_5.setText("\u5EA7     \u4F4D\uFF1A");
		label_5.setBounds(336, 95, 61, 17);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_6.setText("\u5F00\u573A\u65F6\u95F4\uFF1A");
		label_6.setBounds(336, 152, 61, 17);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_7.setText("\u7ED3\u675F\u65F6\u95F4\uFF1A");
		label_7.setBounds(336, 195, 61, 17);
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text.setText("<dynamic>");
		text.setBounds(162, 38, 117, 23);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_1.setText("<dynamic>");
		text_1.setBounds(162, 82, 117, 23);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_2.setText("<dynamic>");
		text_2.setBounds(162, 124, 117, 23);
		
		text_3 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_3.setText("<dynamic>");
		text_3.setBounds(162, 177, 117, 23);
		
		text_4 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_4.setText("<dynamic>");
		text_4.setBounds(418, 39, 117, 23);
		
		text_5 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_5.setText("<dynamic>");
		text_5.setBounds(418, 92, 117, 23);
		
		text_6 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_6.setText("<dynamic>");
		text_6.setBounds(418, 149, 117, 23);
		
		text_7 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_7.setText("<dynamic>");
		text_7.setBounds(418, 195, 117, 23);
		
		text_8 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_8.setText("<dynamic>");
		text_8.setBounds(162, 237, 117, 23);
		
		text_9 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_9.setText("<dynamic>");
		text_9.setBounds(162, 290, 117, 23);
		
		String sql ="SELECT showroom_name FROM showroom_info WHERE showroom_id = (SELECT osroom_id FROM onshow_info WHERE onshow_id ="+onshow_id+")";
		
		text.setText(time+seat_1);
		text_1.setText(name);
		text_2.setText(time);
		text_3.setText(String.valueOf(price));
		text_4.setText(ju.query(sql).get(0).get("showroom_name").toString());
		text_5.setText(seat_1);
		text_6.setText(start_time);
		text_7.setText(end_time);
		text_8.setText(String.valueOf(money));
		text_9.setText(String.valueOf(banlance));
		
		
		
		
		
		label_8 = new Label(shell, SWT.NONE);
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_8.setBounds(80, 240, 61, 17);
		label_8.setText("\u652F     \u4ED8\uFF1A");
		
		label_9 = new Label(shell, SWT.NONE);
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_9.setBounds(80, 296, 61, 17);
		label_9.setText("\u4F59      \u989D\uFF1A");
		
		label_10 = new Label(shell, SWT.NONE);
		label_10.setImage(SWTResourceManager.getImage("E:\\\u56DB\u6D77\u5174\u5510\u8BFE\u4EF6\\\u7535\u5F71\u6D77\u62A5\\\u65B0\u5EFA\u6587\u4EF6\u5939\\20130109110115.gif"));
		label_10.setBounds(0, 0, 571, 415);
		
		
		
	}

}
