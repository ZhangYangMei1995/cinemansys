package cn.com.shxt.editor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.core.Activator;
import cn.com.shxt.util.JdbcUtil;
import cn.com.shxt.util.StringRegexUtils;

public class AddUserInfoEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.AddUserInfoEditor"; //$NON-NLS-1$
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private String source;
	private String target;
	private String str="";
	private JdbcUtil ju=new JdbcUtil();
	private MessageBox box;
	private Text text_6;
	public AddUserInfoEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		final Label label = new Label(container, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u59D3\u540D\u67E5\u8BE2");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label.setBounds(85, 23, 80, 23);
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(196, 22, 115, 23);
		
		
		Group group = new Group(container, SWT.NONE);
		group.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		group.setText("\u7528\u6237\u4FE1\u606F\u6DFB\u52A0");
		group.setBounds(65, 52, 768, 557);
		
		final Label label_1 = new Label(group, SWT.BORDER);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_1.setBounds(71, 44, 130, 147);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setText("\u7167\u7247");
		label_2.setBounds(223, 174, 52, 17);
		
		final Button button_1 = new Button(group, SWT.NONE);
		button_1.setForeground(SWTResourceManager.getColor(0, 128, 0));
		button_1.addSelectionListener(new SelectionAdapter() {
			/*照片添加*/
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(container.getShell(),SWT.NONE);//创建一个可打开的文件选择框
				source=fd.open();//open返回的是所选文件绝对路径
				if(source!=null){
					label_1.setImage(SWTResourceManager.getImage(source));
				}
			}
		});
		button_1.setText("\u6D4F\u89C8");
		button_1.setBounds(95, 212, 80, 27);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setText("\u59D3       \u540D\uFF1A");
		label_3.setBounds(365, 47, 72, 17);
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(454, 44, 94, 23);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setText("\u5BC6        \u7801\uFF1A");
		label_4.setBounds(365, 196, 72, 17);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_5.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_5.setBounds(365, 246, 61, 17);
		
		text_2 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_2.setBounds(454, 193, 94, 23);
		
		text_3 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_3.setBounds(454, 243, 94, 23);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_6.setText("\u6027      \u522B\uFF1A");
		label_6.setBounds(365, 100, 61, 17);
		
		final Button button_2 = new Button(group, SWT.RADIO);
		button_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_2.setText("\u7537");
		button_2.setBounds(454, 100, 33, 17);
		
		final Button button_3 = new Button(group, SWT.RADIO);
		button_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_3.setText("\u5973");
		button_3.setBounds(507, 100, 33, 17);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_7.setText("\u89D2      \u8272\uFF1A");
		label_7.setBounds(365, 353, 61, 17);
		
		final Combo combo = new Combo(group, SWT.READ_ONLY);
		combo.setItems(new String[] {"  \u666E\u901A\u7528\u6237", "  \u4F1A\u5458\u7528\u6237"});
		combo.setBounds(454, 350, 94, 25);
		combo.select(0);
		
		final Combo combo_1 = new Combo(group, SWT.READ_ONLY);
		combo_1.setItems(new String[] {"    \u7EFF\u5361", "    \u94DC\u5361", "    \u94F6\u5361", "    \u91D1\u5361"});
		combo_1.setBounds(454, 408, 94, 25);
		combo_1.select(0);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/*搜索*/
			public void widgetSelected(SelectionEvent e) {

				button_2.setSelection(false);
				button_3.setSelection(false);
				String name=text.getText().trim();
				if(name.equals("")){
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("请添加姓名");
					 box.setText("提示信息");
					 box.open();
					 text.setFocus();
				}else{
					String sql="select * from client_info where client_name='"+name+"' and (client_role='普通用户' or client_role='会员用户')";
					List<Map<String,Object>>list=ju.query(sql);					
					if(list.size()==0){
						box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("不存在该姓名的客户");
						 box.setText("提示信息");
						 box.open();
						 text_1.setText("");
						 text_2.setText("");
						 text_3.setText("");
						 text.setText("");
						 text_5.setText("");
						 text_4.setText("");
						 button_3.setSelection(false);
						 button_2.setSelection(false);			
						 label_1.setImage(null);				 
						 text_1.setFocus();		
					}else{
						text_1.setText(list.get(0).get("client_name").toString());
						String sex=list.get(0).get("client_sex").toString();
						if(sex.equals("男")){
							button_2.setSelection(true);
						}else{
							button_3.setSelection(true);
						}
						text_5.setText(list.get(0).get("client_age").toString());
						text_2.setText(list.get(0).get("client_password").toString());
						text_3.setText(list.get(0).get("client_password").toString());
						text_4.setText(list.get(0).get("client_mail").toString());
						text_6.setText(list.get(0).get("client_telephone").toString());
						if(list.get(0).get("client_role").toString().equals("普通用户")){
							combo.select(0);
						}else{
							combo.select(1);
						}
						if(list.get(0).get("client_grade").toString().equals("绿卡")){
							combo_1.select(0);
						}else if(list.get(0).get("client_grade").toString().equals("铜卡")){
							combo_1.select(1);
						}if(list.get(0).get("client_grade").toString().equals("银卡")){
							combo_1.select(2);
						}else{
							combo.select(3);
						}
						label_1.setImage(SWTResourceManager.getImage(str+list.get(0).get("client_image")));
					}				
				}
			
				
			}
		});
		button.setText("\u641C\u7D22");
		button.setBounds(385, 20, 80, 27);
		
		Button button_4 = new Button(group, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			/*信息注册*/
			public void widgetSelected(SelectionEvent e) {
				 
				if(source==null){
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("请选择照片");
					 box.setText("提示信息");
					 box.open();
				}else{
					File sourceFile = new File(source);	
					//获取icons的目录位置
					URL url=Activator.getDefault().getBundle().getResource("icons");
					//System.out.println(url);//bundleresource://28.fwk11376172:2/icons/
					try {
						str=FileLocator.toFileURL(url).toString().substring(6);
						//System.out.println(str);//E:/javaworkplace/thirdlesson.rcp.app/icons/
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					//生成上传后的文件名称
					String fileName=new Date().getTime()+""+Math.random()*100+".jpg";
					//通过icons目录和生成的文件名，合成目标文件名称
					target =str+fileName;	
					File targetFile=new File(target);
					if(!targetFile.exists()){
						try {
							targetFile.createNewFile();
						} catch (IOException e1) {						
							e1.printStackTrace();
						}
					}
					//图片复制步骤，输入流  输出流
					BufferedInputStream in=null;
					BufferedOutputStream out=null;
					byte [] bs=new byte[(int) sourceFile.length()];
					try {
						in=new BufferedInputStream(new FileInputStream(sourceFile));
						in.read(bs);
						
						out=new BufferedOutputStream(new FileOutputStream(targetFile));
					 	out.write(bs);
					 	out.flush();
					 	out.close();
					 	in.close();
					 } catch (Exception e1) {
						
						e1.printStackTrace();
					}
					 
					 String name=text_1.getText().trim();					
					 String sex="";
					 if(button_2.getSelection()){
						 sex="男";
					 }else if(button_3.getSelection()){
						 sex="女";
					 }
					 String age=text_5.getText().trim();
					 String password=text_2.getText().trim();
					 String password1=text_3.getText().trim();
					 String telephone = text_6.getText().trim();
					 String mail=text_4.getText().trim(); 
					 String role=combo.getText().trim();
					/* if(role.equals("普通用户")){
						 combo_1.select(0);
					 }*/ //没用
					 if(name.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请添写姓名");
						 box.setText("提示信息");
						 box.open();
						 text_1.setFocus();
					 }else if(sex.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请选择性别");
						 box.setText("提示信息");
						 box.open();
					 }else if(age.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请添加出生日期！");
						 box.setText("提示信息");
						 box.open();
						 text_5.setFocus();
					 }else if(!StringRegexUtils.isRegexpValidate(age, StringRegexUtils.date_regexp)){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("出生日期填写错误");
						 box.setText("提示信息");
						 box.open();
						 text_5.setFocus();
					 } else if(password.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请设置您的密码");
						 box.setText("提示信息");
						 box.open();						
						 text_2.setFocus();
					 }else if(password1.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("确认密码不能为空");
						 box.setText("提示信息");
						 box.open();		
						 text_3.setFocus();
					 }else if(!password1.equals(password)){
						 System.out.println(password);
						 System.out.println(password1);
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("密码不一样");
						 box.setText("提示信息");
						 box.open();
						 text_3.setText("");
						 text_3.setFocus();									
					 }else if(mail.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请填写您的邮箱");
						 box.setText("提示信息");
						 box.open();
						 text_4.setFocus();
					 }else if(!StringRegexUtils.isRegexpValidate(mail, StringRegexUtils.email_regexp)){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("邮箱格式不正确");
						 box.setText("提示信息");
						 box.open();
						 text.setFocus();
					 }else if(!StringRegexUtils.isRegexpValidate(telephone, StringRegexUtils.phone_regexp)){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("电话号码格式不正确");
						 box.setText("提示信息");
						 box.open();
						 text.setFocus();
					 }else if(label_1.getImage() == null){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请添加本人二寸照片");
						 box.setText("提示信息");
						 box.open();
					 }else{
						 List<Map<String,Object>>list=ju.query("select count(*) as num from client_info where client_name='"+name+"'");				
					     if(Integer.parseInt(list.get(0).get("num").toString())==1){
					    	 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
							 box.setMessage("姓名重复,请重新填写");
							 box.setText("提示信息");
							 box.open();
							 text_1.setFocus();
					     }else{
					    	 String sql="insert into client_info (client_name,client_sex,client_age,client_password,client_role,client_mail,client_image,client_grade,client_telephone)values('"+name+"','"+sex+"',"+age+",'"+password+"','"+role+"','"+mail+"','"+targetFile.getName()+"','"+combo_1.getText()+"','"+telephone+"')";
							 ju.update(sql);
							 box=new MessageBox(container.getShell(),SWT.ICON_INFORMATION);
							 box.setMessage("注册成功");
							 box.setText("提示信息");
							 box.open();
							 text_1.setText("");
							 text_2.setText("");
							 text_3.setText("");
							 text.setText("");
							 text_5.setText("");
							 text_4.setText("");
							 text_6.setText("");
							 button_3.setSelection(false);
							 button_2.setSelection(false);			
							 label_1.setImage(null);				 
							 text_1.setFocus();		
					     }
					 }
					 				 			 				 						
						
				
				}
				
			
			}
		});
		button_4.setText("\u6CE8\u518C");
		button_4.setBounds(615, 463, 80, 27);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_8.setText("\u90AE      \u7BB1\uFF1A");
		label_8.setBounds(365, 302, 61, 17);
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setBounds(454, 299, 94, 23);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_9.setText("\u51FA\u751F\u65E5\u671F\uFF1A");
		label_9.setBounds(365, 149, 61, 17);
		
		text_5 = new Text(group, SWT.BORDER);
		text_5.setBounds(454, 149, 94, 23);
		
		Label label_12 = new Label(group, SWT.NONE);
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_12.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_12.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_12.setBounds(365, 408, 61, 17);
		label_12.setText("\u7B49       \u7EA7\uFF1A");
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_10.setBounds(365, 468, 61, 17);
		label_10.setText(" \u8054\u7CFB\u65B9\u5F0F\uFF1A");
		
		text_6 = new Text(group, SWT.BORDER);
		text_6.setBounds(454, 463, 94, 23);
		
		

	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "AddUserInfoEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "AddUserInfoEditor";
	}
}
