package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.util.JdbcUtil;
import cn.com.shxt.util.StringRegexUtils;

public class UpdateUserInfoDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private MessageBox box;
	private JdbcUtil ju=new JdbcUtil();
	private String id;
	private String source;
	private String target;
	private Combo combo_1;
	private Text text_5;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdateUserInfoDialog(Shell parent, int style) {
		super(parent, style);
		setText("��Ա��Ϣ�޸�");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String id) {
		this.id=id;
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
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(677, 553);
		shell.setText("\u4FE1\u606F\u4FEE\u6539");
		shell.setBounds(300, 30, 677, 495);
		
		final Group group = new Group(shell, SWT.BORDER);
		group.setText("\u4FE1\u606F\u4FEE\u6539");
		group.setBounds(40, 10, 520, 457);
		
		final Label label = new Label(group, SWT.BORDER);
		label.setBounds(10, 46, 130, 147);
		
		text_5 = new Text(group, SWT.BORDER);
		text_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_5.setBounds(310, 425, 94, 23);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("\u7167\u7247");
		label_1.setBounds(43, 212, 52, 17);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u59D3       \u540D\uFF1A");
		label_2.setBounds(221, 47, 72, 17);
		
		text = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text.setBounds(310, 44, 94, 23);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("\u5BC6        \u7801\uFF1A");
		label_3.setBounds(221, 190, 72, 17);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_4.setBounds(221, 232, 61, 17);
		
		text_1 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_1.setBounds(310, 187, 94, 23);
		
		text_2 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_2.setBounds(310, 229, 94, 23);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("\u6027      \u522B\uFF1A");
		label_5.setBounds(221, 100, 61, 17);
		
		final Button button_1 = new Button(group, SWT.RADIO);
		button_1.setText("\u7537");
		button_1.setBounds(310, 100, 33, 17);
		
		final Button button_2 = new Button(group, SWT.RADIO);
		button_2.setText("\u5973");
		button_2.setBounds(363, 100, 41, 17);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("\u89D2      \u8272\uFF1A");
		label_6.setBounds(221, 333, 61, 17);
		
		final Combo combo = new Combo(group, SWT.READ_ONLY);
		combo.setItems(new String[] {"    \u7BA1\u7406\u5458", "    \u7EF4\u62A4\u5458", "    \u666E\u901A\u7528\u6237", "    \u4F1A\u5458\u7528\u6237"});
		combo.setBounds(310, 330, 94, 25);
		combo.select(0);
		
		combo_1 = new Combo(group, SWT.READ_ONLY);
		combo_1.setItems(new String[] {"     \u7EFF\u5361", "     \u94DC\u5361", "     \u94F6\u5361", "     \u91D1\u5361"});
		combo_1.setBounds(310, 386, 94, 25);
		combo_1.select(0);
		
		Button button_3 = new Button(group, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			/*�޸�*/
			public void widgetSelected(SelectionEvent e) {
				String sql="select * from client_info where client_id="+id;
				List<Map<String,Object>>list=ju.query(sql);
				String name=text.getText().trim();								
				 String sex="";
				 if(button_1.getSelection()){
					 sex="��";
				 }else if(button_2.getSelection()){
					 sex="Ů";
				 }
				 String age=text_4.getText().trim();
				 String password=text_1.getText().trim();
				 String password1=text_2.getText().trim();				
				 String mail=text_3.getText().trim(); 
				 String role=combo.getText().trim();
				 String grade=combo_1.getText().trim();
				 String telephone = text_5.getText().trim();
				 if(role.equals("����Ա")||role.equals("ά��Ա")){
					 combo_1.select(3);
				 } 
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
					 text_4.setFocus();
				 }else if(password.equals("")){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("��������������");
					 box.setText("��ʾ��Ϣ");
					 box.open();						
					 text_1.setFocus();
				 } else if(password1.equals("")){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("ȷ�����벻��Ϊ��");
					 box.setText("��ʾ��Ϣ");
					 box.open();		
					 text_2.setFocus();
				 }else if(!password1.equals(password)){
					 System.out.println(password);
					 System.out.println(password1);
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
					 text_3.setFocus();
				 }else if(!StringRegexUtils.isRegexpValidate(mail, StringRegexUtils.email_regexp)){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("�����ʽ����ȷ");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text_3.setFocus();
				 }else if(!StringRegexUtils.isRegexpValidate(telephone, StringRegexUtils.phone_regexp)){
					 box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("��ϵ��ʽ��ʽ����ȷ");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text_5.setFocus();
				 }else{
					 String sql1="update client_info set client_sex='"+sex+"',client_password='"+password+"',client_mail='"+mail+"',client_role='"+role+"',client_grade='"+grade+"', client_telephone = '"+telephone+"' where client_id = "+Integer.parseInt(list.get(0).get("client_id").toString())+"";
					 ju.update(sql1);
					 box=new MessageBox(shell,SWT.ICON_INFORMATION);
					 box.setMessage("�޸ĳɹ�");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text.setText("");
					 text_1.setText("");
					 text_2.setText("");
					 text_3.setText("");
					 text_4.setText("");
					 text_5.setText("");
					 button_1.setSelection(false);
					 button_2.setSelection(false);							 
					 text_1.setFocus();	
					 shell.dispose();
				 }
			}
		});
		button_3.setText("\u4FEE\u6539");
		button_3.setBounds(430, 421, 80, 27);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u90AE      \u7BB1\uFF1A");
		label_7.setBounds(221, 288, 61, 17);
		
		text_3 = new Text(group, SWT.BORDER);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_3.setBounds(310, 285, 94, 23);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("\u51FA\u751F\u65E5\u671F\uFF1A");
		label_8.setBounds(221, 149, 61, 17);
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_4.setBounds(310, 149, 94, 23);
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setText("*");
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_10.setBounds(419, 47, 52, 17);
		
		Label label_11 = new Label(group, SWT.NONE);
		label_11.setText("\u7B49       \u7EA7\uFF1A");
		label_11.setBounds(221, 386, 61, 17);
		
		Label label_13 = new Label(group, SWT.NONE);
		label_13.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_13.setBounds(10, 426, 72, 17);
		label_13.setText("*\u9879\u4E0D\u80FD\u4FEE\u6539");

		//��ó�ֵ
		String sql="select * from client_info where client_id="+id;
		List<Map<String,Object>>list=ju.query(sql);
		text.setText(list.get(0).get("client_name").toString());
		if(list.get(0).get("client_sex").toString().equals("��")){
			button_1.setSelection(true);
		}else{
			button_2.setSelection(true);
		}
		text_1.setText(list.get(0).get("client_password").toString());
		text_2.setText(list.get(0).get("client_password").toString());
		text_4.setText(list.get(0).get("client_age").toString());
		text_3.setText(list.get(0).get("client_mail").toString());
		if(list.get(0).get("client_role").toString().equals("����Ա")){
			combo.select(0);
			combo_1.select(3);
		}else if(list.get(0).get("client_role").toString().equals("ά��Ա")){
			combo.select(1);
			combo_1.select(3);
		}else if(list.get(0).get("client_role").toString().equals("��ͨ�û�")){
			combo.select(2);
			combo_1.select(0);
		}else if(list.get(0).get("client_role").toString().equals("��Ա�û�")){
			combo.select(3);
			if(list.get(0).get("client_grade").toString().equals("ͭ��")){
				combo_1.select(1);
			}
			if(list.get(0).get("client_grade").toString().equals("����")){
				combo_1.select(2);
			}
			if(list.get(0).get("client_grade").toString().equals("��")){
				combo_1.select(3);
			}
		}
		text_5.setText(list.get(0).get("client_telephone").toString());
		label.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\"+list.get(0).get("client_image")));
		
		Label label_12 = new Label(group, SWT.NONE);
		label_12.setBounds(221, 426, 61, 17);
		label_12.setText("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		
		
	}
}
