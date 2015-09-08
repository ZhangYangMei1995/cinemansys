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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
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

public class AddManagerInfoEditor extends EditorPart implements IEditorInput{

	public static final String ID = "cn.com.shxt.editor.AddManagerInfoEditor"; //$NON-NLS-1$
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private String source;
	private String target;
	private String str ;
	private JdbcUtil ju=new JdbcUtil();
	private MessageBox box;
	private Text text;
	private Text text_5;
	private Text text_4;
	private Text text_6;
	public AddManagerInfoEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		Group group = new Group(container, SWT.NONE);
		group.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		group.setText("\u7BA1\u7406\u5458\u4FE1\u606F\u6DFB\u52A0");
		group.setBounds(62, 57, 720, 530);
		
		final Label label = new Label(group, SWT.BORDER);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label.setBounds(61, 46, 130, 147);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_1.setBounds(197, 187, 52, 17);
		label_1.setText("\u7167\u7247");
		
		Button button = new Button(group, SWT.NONE);
		button.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		button.addSelectionListener(new SelectionAdapter() {
			/*照片浏览*/
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(container.getShell(),SWT.NONE);//创建一个可打开的文件选择框
				source=fd.open();//open返回的是所选文件绝对路径
				if(source!=null){
					label.setImage(SWTResourceManager.getImage(source));
				}
			}
		});
		button.setBounds(84, 227, 80, 27);
		button.setText("\u6D4F\u89C8");
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setBounds(341, 47, 72, 17);
		label_3.setText("\u59D3       \u540D\uFF1A");
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(430, 44, 94, 23);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setBounds(341, 190, 72, 17);
		label_4.setText("\u5BC6        \u7801\uFF1A");
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_5.setBounds(341, 232, 61, 17);
		label_5.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		
		text_2 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_2.setBounds(430, 187, 94, 23);
		
		text_3 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_3.setBounds(430, 229, 94, 23);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_6.setBounds(341, 100, 61, 17);
		label_6.setText("\u6027      \u522B\uFF1A");
		
		final Button button_1 = new Button(group, SWT.RADIO);
		button_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_1.setBounds(430, 100, 33, 17);
		button_1.setText("\u7537");
		
		final Button button_2 = new Button(group, SWT.RADIO);
		button_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_2.setBounds(483, 100, 33, 17);
		button_2.setText("\u5973");
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_7.setBounds(341, 333, 61, 17);
		label_7.setText("\u89D2      \u8272\uFF1A");
		
		final Combo combo = new Combo(group, SWT.READ_ONLY);
		combo.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		combo.setItems(new String[] {"   \u7BA1\u7406\u5458", "   \u7EF4\u62A4\u5458"});
		combo.setBounds(430, 330, 94, 25);
		combo.select(0);
		
		Button button_3 = new Button(group, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			/*管理员信息注册*/
			public void widgetSelected(SelectionEvent e) {		 
				if(source==null){
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("请选择照片");
					 box.setText("提示信息");
					 box.open();
				}else{
					/*图片上传至icorns目录下*/
					File sourceFile = new File(source);//通过原图片的完整路径 创建原文件对象	
					
					//获取icons的目录位置
					URL url=Activator.getDefault().getBundle().getResource("icons");
					//System.out.println(url);//bundleresource://28.fwk11376172:2/icons/
					str="";
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
					byte [] bs=new byte[(int) sourceFile.length()];//创建一个空的字节缓冲区数组
					try {
						in=new BufferedInputStream(new FileInputStream(sourceFile));//在sourceFile文件上，插入了字节输入流管道
						in.read(bs);//以字节的形式读取sourceFile文件中的内容到bs缓冲区数组中
						
						out=new BufferedOutputStream(new FileOutputStream(targetFile));//覆盖式写入
					 	out.write(bs);
					 	out.flush();//刷新写入缓冲区
					 	out.close();//关闭流对象
					 	in.close();
					 } catch (Exception e1) {
						
						e1.printStackTrace();
					}
					 
					 String name=text_1.getText().trim();					
					 String sex="";
					 if(button_1.getSelection()){
						 sex="男";
					 }else if(button_2.getSelection()){
						 sex="女";
					 }
					 String age=text_5.getText().trim();
					 String password=text_2.getText().trim();
					 String password1=text_3.getText().trim();
					 String telephone = text_6.getText().trim();
					 String mail=text.getText().trim(); 
					 String role=combo.getText().trim();
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
						 box.setMessage("出生日期格式不正确！");
						 box.setText("提示信息");
						 box.open();
						 text.setFocus();
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
						 text.setFocus();
					 }else if(!StringRegexUtils.isRegexpValidate(mail, StringRegexUtils.email_regexp)){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("邮箱格式不正确");
						 box.setText("提示信息");
						 box.open();
						 text.setFocus();
					 }else if(!StringRegexUtils.isRegexpValidate(telephone, StringRegexUtils.phone_regexp)){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("电话号码不正确");
						 box.setText("提示信息");
						 box.open();
						 text_6.setFocus();
					 }else if(label.getImage() == null){
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
					    	 String sql="insert into client_info (client_name,client_sex,client_age,client_password,client_role,client_mail,client_image,client_grade,client_telephone)values('"+name+"','"+sex+"',"+age+",'"+password+"','"+role+"','"+mail+"','"+targetFile.getName()+"','金卡',"+telephone+")";
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
							 text_6.setText("");
							 button_1.setSelection(false);
							 button_2.setSelection(false);			
							 label.setImage(null);				 
							 text_1.setFocus();		
					     }
					 }
					 				 			 				 						
						
				
				}
				
			}
		});
		button_3.setBounds(387, 425, 80, 27);
		button_3.setText("\u6CE8\u518C");
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setBounds(341, 288, 61, 17);
		label_2.setText("\u90AE      \u7BB1\uFF1A");
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(430, 285, 94, 23);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_8.setBounds(341, 149, 61, 17);
		label_8.setText("\u51FA\u751F\u65E5\u671F\uFF1A");
		
		text_5 = new Text(group, SWT.BORDER);
		text_5.setBounds(430, 149, 94, 23);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_9.setBounds(341, 386, 61, 17);
		label_9.setText("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		
		text_6 = new Text(group, SWT.BORDER);
		text_6.setBounds(430, 380, 94, 23);
		
		Button button_5 = new Button(group, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			/*员工注销*/
			public void widgetSelected(SelectionEvent e) {
				button_1.setSelection(false);
				button_2.setSelection(false);
				String name=text_4.getText().trim();
				if(name.equals("")){
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("请添加姓名");
					 box.setText("提示信息");
					 box.open();
					 text_4.setFocus();
				}else{
					String sql="select * from client_info where client_name='"+name+"' and (client_role='管理员' or client_role='维护员')";
					List<Map<String,Object>>list=ju.query(sql);					
					if(list.size()==0){
						box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("不存在该姓名的员工");
						 box.setText("提示信息");
						 box.open();
						 text.setText("");
						 button_1.setSelection(false);
						 button_2.setSelection(false);
						 text_5.setText("");
						 text_2.setText("");
						 text_3.setText("");
						 text.setText("");
						 label.setImage(null);	
					}else{
						String sql1 = "delete from client_info where client_name = '"+name+"'";
						ju.update(sql1);
						box=new MessageBox(container.getShell(),SWT.ICON_INFORMATION);
						 box.setMessage("注销成功");
						 box.setText("提示信息");
						 box.open();
						 text.setText("");
						 button_1.setSelection(false);
						 button_2.setSelection(false);
						 text_1.setText("");
						 text_5.setText("");
						 text_2.setText("");
						 text_3.setText("");
						 text_6.setText("");
						 text.setText("");
						 label.setImage(null);	
					}
				}
			}
		});
		button_5.setBounds(620, 425, 80, 27);
		button_5.setText("\u6CE8\u9500");
		
		Label label_11 = new Label(container, SWT.NONE);
		label_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_11.setText("\u59D3\u540D\u67E5\u8BE2");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label_11.setBounds(107, 28, 80, 23);
		
		text_4 = new Text(container, SWT.BORDER);
		text_4.setBounds(224, 27, 115, 23);
		
		Button button_4 = new Button(container, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			/*搜索*/
			public void widgetSelected(SelectionEvent e) {
				button_1.setSelection(false);
				button_2.setSelection(false);
				String name=text_4.getText().trim();
				if(name.equals("")){
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("请添加姓名");
					 box.setText("提示信息");
					 box.open();
					 text_4.setFocus();
				}else{
					String sql="select * from client_info where client_name='"+name+"' and (client_role='管理员' or client_role='维护员')";
					List<Map<String,Object>>list=ju.query(sql);					
					if(list.size()==0){
						box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("不存在该姓名的员工");
						 box.setText("提示信息");
						 box.open();
						 text.setText("");
						 button_1.setSelection(false);
						 button_2.setSelection(false);
						 text_5.setText("");
						 text_2.setText("");
						 text_3.setText("");
						 text.setText("");
						 label.setImage(null);	
					}else{
						text_1.setText(list.get(0).get("client_name").toString());
						String sex=list.get(0).get("client_sex").toString();
						if(sex.equals("男")){
							button_1.setSelection(true);
						}else{
							button_2.setSelection(true);
						}
						text_5.setText(list.get(0).get("client_age").toString());
						text_6.setText(list.get(0).get("client_telephone").toString());
						text_2.setText(list.get(0).get("client_password").toString());
						text_3.setText(list.get(0).get("client_password").toString());
						text.setText(list.get(0).get("client_mail").toString());
						if(list.get(0).get("client_role").toString().equals("管理员")){
							combo.select(0);
						}else{
							combo.select(1);
						}
						label.setImage(SWTResourceManager.getImage(str+list.get(0).get("client_image")));
					}				
				}
			}
		});
		button_4.setText("\u641C\u7D22");
		button_4.setBounds(393, 25, 80, 27);

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
		return "AddManagerInfoEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "管理员信息添加";
	}
}
