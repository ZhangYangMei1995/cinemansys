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
			/*��Ƭ���*/
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(container.getShell(),SWT.NONE);//����һ���ɴ򿪵��ļ�ѡ���
				source=fd.open();//open���ص�����ѡ�ļ�����·��
				if(source!=null){
					label.setImage(SWTResourceManager.getImage(source));
				}
			}
		});
		button.setBounds(84, 227, 80, 27);
		button.setText("\u6D4F\u89C8");
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("΢���ź�", 9, SWT.BOLD));
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setBounds(341, 47, 72, 17);
		label_3.setText("\u59D3       \u540D\uFF1A");
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(430, 44, 94, 23);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_4.setFont(SWTResourceManager.getFont("΢���ź�", 9, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setBounds(341, 190, 72, 17);
		label_4.setText("\u5BC6        \u7801\uFF1A");
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("΢���ź�", 9, SWT.BOLD));
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
		label_6.setFont(SWTResourceManager.getFont("΢���ź�", 9, SWT.BOLD));
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
		label_7.setFont(SWTResourceManager.getFont("΢���ź�", 9, SWT.BOLD));
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
			/*����Ա��Ϣע��*/
			public void widgetSelected(SelectionEvent e) {		 
				if(source==null){
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("��ѡ����Ƭ");
					 box.setText("��ʾ��Ϣ");
					 box.open();
				}else{
					/*ͼƬ�ϴ���icornsĿ¼��*/
					File sourceFile = new File(source);//ͨ��ԭͼƬ������·�� ����ԭ�ļ�����	
					
					//��ȡicons��Ŀ¼λ��
					URL url=Activator.getDefault().getBundle().getResource("icons");
					//System.out.println(url);//bundleresource://28.fwk11376172:2/icons/
					str="";
					try {
						str=FileLocator.toFileURL(url).toString().substring(6);
						//System.out.println(str);//E:/javaworkplace/thirdlesson.rcp.app/icons/
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					
					//�����ϴ�����ļ�����
					String fileName=new Date().getTime()+""+Math.random()*100+".jpg";
					//ͨ��iconsĿ¼�����ɵ��ļ������ϳ�Ŀ���ļ�����
					target =str+fileName;	
					
					File targetFile=new File(target);
					if(!targetFile.exists()){
						try {
							targetFile.createNewFile();
						} catch (IOException e1) {						
							e1.printStackTrace();
						}
					}
					
					//ͼƬ���Ʋ��裬������  �����
					BufferedInputStream in=null;
					BufferedOutputStream out=null;
					byte [] bs=new byte[(int) sourceFile.length()];//����һ���յ��ֽڻ���������
					try {
						in=new BufferedInputStream(new FileInputStream(sourceFile));//��sourceFile�ļ��ϣ��������ֽ��������ܵ�
						in.read(bs);//���ֽڵ���ʽ��ȡsourceFile�ļ��е����ݵ�bs������������
						
						out=new BufferedOutputStream(new FileOutputStream(targetFile));//����ʽд��
					 	out.write(bs);
					 	out.flush();//ˢ��д�뻺����
					 	out.close();//�ر�������
					 	in.close();
					 } catch (Exception e1) {
						
						e1.printStackTrace();
					}
					 
					 String name=text_1.getText().trim();					
					 String sex="";
					 if(button_1.getSelection()){
						 sex="��";
					 }else if(button_2.getSelection()){
						 sex="Ů";
					 }
					 String age=text_5.getText().trim();
					 String password=text_2.getText().trim();
					 String password1=text_3.getText().trim();
					 String telephone = text_6.getText().trim();
					 String mail=text.getText().trim(); 
					 String role=combo.getText().trim();
					 if(name.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("����д����");
						 box.setText("��ʾ��Ϣ");
						 box.open();
						 text_1.setFocus();
					 }else if(sex.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("��ѡ���Ա�");
						 box.setText("��ʾ��Ϣ");
						 box.open();
					 }else if(age.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("����ӳ������ڣ�");
						 box.setText("��ʾ��Ϣ");
						 box.open();
						 text_5.setFocus();
					 }else if(!StringRegexUtils.isRegexpValidate(age, StringRegexUtils.date_regexp)){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("�������ڸ�ʽ����ȷ��");
						 box.setText("��ʾ��Ϣ");
						 box.open();
						 text.setFocus();
					 } else if(password.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("��������������");
						 box.setText("��ʾ��Ϣ");
						 box.open();						
						 text_2.setFocus();
					 }else if(password1.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("ȷ�����벻��Ϊ��");
						 box.setText("��ʾ��Ϣ");
						 box.open();		
						 text_3.setFocus();
					 }else if(!password1.equals(password)){
						 System.out.println(password);
						 System.out.println(password1);
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("���벻һ��");
						 box.setText("��ʾ��Ϣ");
						 box.open();
						 text_3.setText("");
						 text_3.setFocus();									
					 }else if(mail.equals("")){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("����д��������");
						 box.setText("��ʾ��Ϣ");
						 box.open();
						 text.setFocus();
					 }else if(!StringRegexUtils.isRegexpValidate(mail, StringRegexUtils.email_regexp)){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("�����ʽ����ȷ");
						 box.setText("��ʾ��Ϣ");
						 box.open();
						 text.setFocus();
					 }else if(!StringRegexUtils.isRegexpValidate(telephone, StringRegexUtils.phone_regexp)){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("�绰���벻��ȷ");
						 box.setText("��ʾ��Ϣ");
						 box.open();
						 text_6.setFocus();
					 }else if(label.getImage() == null){
						 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("����ӱ��˶�����Ƭ");
						 box.setText("��ʾ��Ϣ");
						 box.open();
					 }else{
						 List<Map<String,Object>>list=ju.query("select count(*) as num from client_info where client_name='"+name+"'");				
					     if(Integer.parseInt(list.get(0).get("num").toString())==1){
					    	 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
							 box.setMessage("�����ظ�,��������д");
							 box.setText("��ʾ��Ϣ");
							 box.open();
							 text_1.setFocus();
					     }else{
					    	 String sql="insert into client_info (client_name,client_sex,client_age,client_password,client_role,client_mail,client_image,client_grade,client_telephone)values('"+name+"','"+sex+"',"+age+",'"+password+"','"+role+"','"+mail+"','"+targetFile.getName()+"','��',"+telephone+")";
							 ju.update(sql);
							 box=new MessageBox(container.getShell(),SWT.ICON_INFORMATION);
							 box.setMessage("ע��ɹ�");
							 box.setText("��ʾ��Ϣ");
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
		label_2.setFont(SWTResourceManager.getFont("΢���ź�", 9, SWT.BOLD));
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setBounds(341, 288, 61, 17);
		label_2.setText("\u90AE      \u7BB1\uFF1A");
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(430, 285, 94, 23);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("΢���ź�", 9, SWT.BOLD));
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_8.setBounds(341, 149, 61, 17);
		label_8.setText("\u51FA\u751F\u65E5\u671F\uFF1A");
		
		text_5 = new Text(group, SWT.BORDER);
		text_5.setBounds(430, 149, 94, 23);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("΢���ź�", 9, SWT.BOLD));
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_9.setBounds(341, 386, 61, 17);
		label_9.setText("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		
		text_6 = new Text(group, SWT.BORDER);
		text_6.setBounds(430, 380, 94, 23);
		
		Button button_5 = new Button(group, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			/*Ա��ע��*/
			public void widgetSelected(SelectionEvent e) {
				button_1.setSelection(false);
				button_2.setSelection(false);
				String name=text_4.getText().trim();
				if(name.equals("")){
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("���������");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text_4.setFocus();
				}else{
					String sql="select * from client_info where client_name='"+name+"' and (client_role='����Ա' or client_role='ά��Ա')";
					List<Map<String,Object>>list=ju.query(sql);					
					if(list.size()==0){
						box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("�����ڸ�������Ա��");
						 box.setText("��ʾ��Ϣ");
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
						 box.setMessage("ע���ɹ�");
						 box.setText("��ʾ��Ϣ");
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
		label_11.setFont(SWTResourceManager.getFont("΢���ź�", 11, SWT.BOLD));
		label_11.setBounds(107, 28, 80, 23);
		
		text_4 = new Text(container, SWT.BORDER);
		text_4.setBounds(224, 27, 115, 23);
		
		Button button_4 = new Button(container, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			/*����*/
			public void widgetSelected(SelectionEvent e) {
				button_1.setSelection(false);
				button_2.setSelection(false);
				String name=text_4.getText().trim();
				if(name.equals("")){
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("���������");
					 box.setText("��ʾ��Ϣ");
					 box.open();
					 text_4.setFocus();
				}else{
					String sql="select * from client_info where client_name='"+name+"' and (client_role='����Ա' or client_role='ά��Ա')";
					List<Map<String,Object>>list=ju.query(sql);					
					if(list.size()==0){
						box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("�����ڸ�������Ա��");
						 box.setText("��ʾ��Ϣ");
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
						if(sex.equals("��")){
							button_1.setSelection(true);
						}else{
							button_2.setSelection(true);
						}
						text_5.setText(list.get(0).get("client_age").toString());
						text_6.setText(list.get(0).get("client_telephone").toString());
						text_2.setText(list.get(0).get("client_password").toString());
						text_3.setText(list.get(0).get("client_password").toString());
						text.setText(list.get(0).get("client_mail").toString());
						if(list.get(0).get("client_role").toString().equals("����Ա")){
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
		return "����Ա��Ϣ���";
	}
}
