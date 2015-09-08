package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.util.JdbcUtil;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class LoginDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private JdbcUtil ju=new JdbcUtil();
	private MessageBox box;
	public static String role;
	public static String userName ;
	public LoginDialog(Shell parent, int style) {
		super(parent, style);
		setText("登录界面");
	}

	public Object open() {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX | SWT.APPLICATION_MODAL);
		shell.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\image\\denglu.jpg"));
		shell.setBackgroundImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\image\\denglu.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);//使标签上字体没有背景
		shell.setSize(498, 354);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shell.setBounds(350,200, 498, 355);
		
		Label label = new Label(shell, SWT.SHADOW_IN | SWT.CENTER);
		label.setDragDetect(false);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setBounds(142, 222, 76, 26);
		label.setText("\u7528\u6237\u540D\uFF1A");
		
		text = new Text(shell, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text.setBounds(233, 226, 112, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_1.setBounds(142, 271, 75, 26);
		label_1.setText("\u5BC6   \u7801\uFF1A");
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_1.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
			//	int code = e.keyCode ;
				/*String sql = "select * from client_info where client_name = '"+text.getText().trim()+"' and client_password = '"+text_1.getText().trim()+"'";
			System.out.println(ju.query(sql).size());
				if(ju.query(sql).size()==1){
					label_2.setText("正确！");
				}else{
					label_2.setText("不存在！");
				}*/
			}
		});
		text_1.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent arg0) {
				userName=text.getText().trim();
				String password=text_1.getText().trim();
				List<Map<String,Object>>list=ju.query("select * from client_info where client_name='"+userName+"' and client_password='"+password+"'");
				if(list.size()==1){
					role=list.get(0).get("client_role").toString();					
					box=new MessageBox(shell,SWT.ICON_INFORMATION);
					box.setText("提示信息");
					box.setMessage("登录成功");
					box.open();		
					result="登录成功";//把结果返回给调用open方法的调用者
					shell.dispose();//登录成功，正常关闭shell窗口
				}else{
					box=new MessageBox(shell,SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("登录用户名或密码错误");
					box.open();	
				}
			}
		});
		text_1.setBounds(233, 275, 112, 23);
		
		Button button = new Button(shell, SWT.NONE);
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		button.addSelectionListener(new SelectionAdapter() {
			/*登录*/
			public void widgetSelected(SelectionEvent e) {				
				String userName=text.getText().trim();
				String password=text_1.getText().trim();
				List<Map<String,Object>>list=ju.query("select * from client_info where client_name='"+userName+"' and client_password='"+password+"'");
				if(list.size()==1){
					role=list.get(0).get("client_role").toString();					
					box=new MessageBox(shell,SWT.ICON_INFORMATION);
					box.setText("提示信息");
					box.setMessage("登录成功");
					box.open();		
					result="登录成功";
					shell.dispose();
				}else{
					box=new MessageBox(shell,SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("登录用户名或密码错误");
					box.open();	
				}
			}
		});
		button.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		button.setBounds(385, 276, 80, 27);
		button.setText("\u767B\u5F55");
		
		

	}
}
