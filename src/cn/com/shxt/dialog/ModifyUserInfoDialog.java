package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

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
import cn.com.shxt.util.StringRegexUtils;

public class ModifyUserInfoDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private int client_id;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private JdbcUtil ju = new JdbcUtil();
	private MessageBox box;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ModifyUserInfoDialog(Shell parent, int style) {
		super(parent, style);
		setText("�޸��û���Ϣ!");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(int client_id) {
		this.client_id = client_id ;
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
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setBounds(300, 30, 677, 495);
		shell.setText(getText());
		
		Label label = new Label(shell, SWT.BORDER);
		label.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\null"));
		label.setBounds(36, 27, 130, 147);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u7167\u7247");
		label_1.setBounds(69, 193, 52, 17);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_2.setText("\u59D3       \u540D\uFF1A");
		label_2.setBounds(247, 28, 72, 17);
		
		text = new Text(shell, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text.setBounds(336, 25, 94, 23);
		
		final Button button = new Button(shell, SWT.RADIO);
		button.setText("\u5973");
		button.setBounds(389, 100, 41, 17);
		
		final Button button_1 = new Button(shell, SWT.RADIO);
		button_1.setText("\u7537");
		button_1.setBounds(338, 100, 33, 17);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_3.setText("\u6027      \u522B\uFF1A");
		label_3.setBounds(247, 100, 61, 17);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_4.setText("\u51FA\u751F\u65E5\u671F\uFF1A");
		label_4.setBounds(247, 157, 61, 17);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_1.setBounds(336, 151, 94, 23);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_5.setText("\u5BC6        \u7801\uFF1A");
		label_5.setBounds(247, 218, 72, 17);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_2.setBounds(336, 215, 94, 23);
		
		text_3 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_3.setBounds(336, 266, 94, 23);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_6.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_6.setBounds(247, 269, 61, 17);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_7.setText("\u90AE      \u7BB1\uFF1A");
		label_7.setBounds(247, 343, 61, 17);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_4.setBounds(336, 337, 94, 23);
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_8.setText("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		label_8.setBounds(247, 407, 61, 17);
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_5.setBounds(336, 404, 94, 23);
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
		/*�޸��û���Ϣ*/
			public void widgetSelected(SelectionEvent e) {
				String sql="select * from client_info where client_id="+client_id;
				List<Map<String,Object>>list=ju.query(sql);
				String name=text.getText().trim();								
				 String sex="";
				 if(button_1.getSelection()){
					 sex="��";
				 }else if(button.getSelection()){
					 sex="Ů";
				 }
				
				 String age=text_1.getText().trim();
				 String password=text_2.getText().trim();
				 String password1=text_3.getText().trim();				
				 String mail=text_4.getText().trim(); 
				 String telephone = text_5.getText().trim();
				
				 if(name.equals("")){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("����д����");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text.setFocus();
				 }else if(sex.equals("")){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("��ѡ���Ա�");
					 box.setText("��ʾ��Ϣ");
					 box.open();
				 }else if(age.equals("")){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("����ӳ�������");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text_4.setFocus();
				 }else if(!StringRegexUtils.isRegexpValidate(age, StringRegexUtils.date_regexp)){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("�������ڸ�ʽ����ȷ��");
					 box.setText("��ʾ��Ϣ");
					 box.open();						
					 text_1.setFocus();
				 }else if(password.equals("")){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("��������������");
					 box.setText("��ʾ��Ϣ");
					 box.open();						
					 text_2.setFocus();
				 } else if(password1.equals("")){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("ȷ�����벻��Ϊ��");
					 box.setText("��ʾ��Ϣ");
					 box.open();		
					 text_3.setFocus();
				 }else if(!password1.equals(password)){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("���벻һ��");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text_2.setText("");
					 text_2.setFocus();									
				 }else if(mail.equals("")){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("����д��������");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text_4.setFocus();
				 }else if(!StringRegexUtils.isRegexpValidate(mail, StringRegexUtils.email_regexp)){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("�����ʽ����ȷ");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text_4.setFocus();
				 }else if(!StringRegexUtils.isRegexpValidate(telephone, StringRegexUtils.phone_regexp)){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("��ϵ��ʽ��ʽ����ȷ");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text_5.setFocus();
				 }else{
					 String sql1="update client_info set client_name = '"+name+"' ,client_sex='"+sex+"',client_password='"+password+"',client_mail='"+mail+"', client_telephone = '"+telephone+"' where client_id = "+Integer.parseInt(list.get(0).get("client_id").toString())+"";
					 ju.update(sql1);
					 box=new MessageBox(shell,SWT.ICON_INFORMATION);
					 box.setMessage("�޸ĳɹ�");
					 box.setText("��ʾ��Ϣ");
					 box.open();				 
					 shell.dispose();
				 }
				
			}
		});
		button_2.setText("\u4FEE\u6539");
		button_2.setBounds(456, 402, 80, 27);

		String sql="select * from client_info where client_id="+client_id;
		List<Map<String,Object>>list=ju.query(sql);
		text.setText(list.get(0).get("client_name").toString());
		if(list.get(0).get("client_sex").toString().equals("��")){
			button_1.setSelection(true);
		}else{
			button.setSelection(true);
		}
		text.setText(list.get(0).get("client_name").toString());
		text_2.setText(list.get(0).get("client_password").toString());
		text_3.setText(list.get(0).get("client_password").toString());
		text_1.setText(list.get(0).get("client_age").toString());
		text_4.setText(list.get(0).get("client_mail").toString());
		text_5.setText(list.get(0).get("client_telephone").toString());
		label.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\"+list.get(0).get("client_image")));
		}
}


