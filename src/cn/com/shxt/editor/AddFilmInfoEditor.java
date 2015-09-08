package cn.com.shxt.editor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

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
import org.eclipse.swt.widgets.DateTime;

public class AddFilmInfoEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.AddFilmInfoEditor"; //$NON-NLS-1$
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private String source;
	private String target;
	private MessageBox box;
	private JdbcUtil ju=new JdbcUtil();
	public AddFilmInfoEditor() {
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
		group.setBackgroundMode(SWT.INHERIT_DEFAULT);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		group.setText("\u5F71\u7247\u6DFB\u52A0");
		group.setBounds(41, 21, 799, 604);
		
		Label label = new Label(group, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label.setBounds(230, 27, 61, 17);
		label.setText("\u7535\u5F71\u540D\u79F0\uFF1A");
		
		text = new Text(group, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text.setBounds(319, 27, 118, 23);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_1.setBounds(230, 74, 61, 17);
		label_1.setText("\u56FD     \u5BB6\uFF1A");
		
		final Combo combo = new Combo(group, SWT.READ_ONLY);
		combo.setBackgroundMode(SWT.INHERIT_DEFAULT);
		combo.setItems(new String[] {"         \u4E2D\u56FD", "         \u7F8E\u56FD    ", "         \u82F1\u56FD", "         \u6CF0\u56FD", "         \u9A6C\u6765\u897F\u4E9A", "         \u6FB3\u5927\u5229\u4E9A", "         \u65E5\u672C"});
		combo.setBounds(319, 74, 118, 25);
		combo.select(0);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_2.setBounds(230, 125, 61, 17);
		label_2.setText("\u5BFC      \u6F14\uFF1A");
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_3.setBounds(230, 170, 61, 17);
		label_3.setText("\u4E3B     \u6F14\uFF1A");
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_4.setBounds(230, 223, 61, 17);
		label_4.setText("\u65F6     \u957F\uFF1A");
		
		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		lblNewLabel.setBounds(230, 275, 61, 17);
		lblNewLabel.setText("\u8BED     \u8A00\uFF1A");
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_5.setBounds(230, 312, 61, 17);
		label_5.setText("\u7C7B     \u578B\uFF1A");
		
		final Button button_1 = new Button(group, SWT.CHECK);
		button_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_1.setBounds(383, 312, 45, 17);
		button_1.setText("\u60CA\u609A");
		
		final Button button_2 = new Button(group, SWT.CHECK);
		button_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_2.setBounds(452, 312, 45, 17);
		button_2.setText("\u6B66\u4FA0");
		
		final Button button_3 = new Button(group, SWT.CHECK);
		button_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_3.setBounds(383, 350, 45, 17);
		button_3.setText("\u8BB0\u5F55");
		
		final Button button_4 = new Button(group, SWT.CHECK);
		button_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_4.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_4.setBounds(452, 350, 45, 17);
		button_4.setText("\u79D1\u5E7B");
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_1.setBounds(319, 125, 118, 23);
		
		text_2 = new Text(group, SWT.BORDER | SWT.MULTI);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_2.setBounds(319, 170, 118, 23);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_6.setBounds(30, 404, 61, 17);
		label_6.setText("\u7B80     \u4ECB\uFF1A");
		
		text_3 = new Text(group, SWT.BORDER);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_3.setBounds(319, 223, 118, 23);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_7.setBounds(442, 235, 24, 17);
		label_7.setText("\u5206\u949F");
		
		final Button button_5 = new Button(group, SWT.RADIO);
		button_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_5.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_5.setBounds(319, 275, 45, 17);
		button_5.setText("\u82F1\u6587");
		
		final Button button_6 = new Button(group, SWT.RADIO);
		button_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_6.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_6.setBounds(383, 275, 54, 17);
		button_6.setText("\u4E2D\u6587");
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_9.setBounds(571, 27, 61, 17);
		label_9.setText("\u4E0A\u7EBF\u65E5\u671F\uFF1A");
		
		final DateTime dateTime = new DateTime(group, SWT.BORDER | SWT.DROP_DOWN);
		dateTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		dateTime.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		dateTime.setBounds(651, 27, 116, 24);
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_10.setText("\u4E0B\u7EBF\u65E5\u671F\uFF1A");
		label_10.setBounds(571, 88, 61, 17);
		
		final DateTime dateTime_1 = new DateTime(group, SWT.BORDER | SWT.DROP_DOWN);
		dateTime_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		dateTime_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		dateTime_1.setBounds(651, 88, 116, 24);
		
		final Button button_7 = new Button(group, SWT.RADIO);
		button_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_7.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_7.setBounds(442, 275, 74, 17);
		button_7.setText("\u4E2D\u6587/\u82F1\u6587");
		
		text_4 = new Text(group, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		text_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_4.setBounds(109, 401, 658, 152);
		
		final Button btnNewButton = new Button(group, SWT.CENTER);
		btnNewButton.setImage(SWTResourceManager.getImage("E:\\\u56DB\u6D77\u5174\u5510\u8BFE\u4EF6\\\u7535\u5F71\u6D77\u62A5\\\u65B0\u5EFA\u6587\u4EF6\u5939\\20130109104825.gif"));
		btnNewButton.setFont(SWTResourceManager.getFont("@Meiryo", 14, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			/*照片添加*/
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(container.getShell(),SWT.NONE);//创建一个可打开的文件选择框
				source=fd.open();//open返回的是所选文件绝对路径
				if(source!=null){
					btnNewButton.setImage(SWTResourceManager.getImage(source));
				}
			}
		});
		btnNewButton.setBounds(23, 22, 166, 218);
		btnNewButton.setText("photo");
		
		Button button_8 = new Button(group, SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter() {
			/*照片添加*/
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(container.getShell(),SWT.NONE);//创建一个可打开的文件选择框
				source=fd.open();//open返回的是所选文件绝对路径
				if(source!=null){
					btnNewButton.setImage(SWTResourceManager.getImage(source));
				}
			}
		});
		button_8.setBounds(69, 259, 80, 27);
		button_8.setText("\u6D4F\u89C8");
		
		final Button button_10 = new Button(group, SWT.CHECK);
		button_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_10.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_10.setBounds(319, 350, 45, 17);
		button_10.setText("\u559C\u5267");
		
		final Button button = new Button(group, SWT.CHECK);			
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button.setBounds(319, 312, 45, 17);
		button.setText("\u8B66\u532A");
		
		Button button_9 = new Button(group, SWT.NONE);
		button_9.addSelectionListener(new SelectionAdapter() {
			/*电影信息录入*/
			public void widgetSelected(SelectionEvent e) {
				 //把图片传至icons目录下
				if(source!=null){
					File sourceFile = new File(source);					
					URL url=Activator.getDefault().getBundle().getResource("icons");
					//System.out.println(url);//bundleresource://28.fwk11376172:2/icons/
					String str="";
					try {
						str=FileLocator.toFileURL(url).toString().substring(6);
						//System.out.println(str);//E:/javaworkplace/thirdlesson.rcp.app/icons/
					} catch (Exception e2) {					
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
					    String name=text.getText().trim();
						String country=combo.getText().trim();
						String director=text_1.getText().trim();
						String protagonist=text_2.getText().trim();
						String time=text_3.getText().trim();
						String introduction=text_4.getText().trim();	
						String date=dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
						String date1=dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-"+dateTime_1.getDay();
						String language="";
						if(button_5.getSelection()==true){
							language="英文";
						}else if(button_6.getSelection()==true){
							language="中文";
						}else{
							language="中文/英文";
						}
						String type="";
						if(button.getSelection()==true){
							type+="警匪 ";
						}
						if(button_1.getSelection()==true){
							type+="惊悚 ";
						}
						if(button_2.getSelection()==true){
							type+="武侠 ";
						}
						if(button_10.getSelection()==true){
							type+="喜剧 ";
						}
						if(button_3.getSelection()==true){
							type+="记录 ";
						}
						if(button_4.getSelection()==true){
							type+="科幻";
						}	
						
						boolean flag1=button_5.getSelection()==false&&button_6.getSelection()==false&&button_7.getSelection()==false;
						boolean flag2=button.getSelection()==false&&button_1.getSelection()==false&&button_2.getSelection()==false&&button_10.getSelection()==false&&button_3.getSelection()==false&&button_4.getSelection()==false;
						
						if(name.equals("")){
							box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
							 box.setMessage("请添加影片名称");
							 box.setText("提示信息");
							 box.open();
							 text.setFocus();
						}else if(director.equals("")){
							 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
							 box.setMessage("请添加导演");
							 box.setText("提示信息");
							 box.open();
							 text_1.setFocus();
						}else if(protagonist.equals("")){
							 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
							 box.setMessage("请选择主演");
							 box.setText("提示信息");
							 box.open();
							 text_2.setFocus();
						}else if(time.equals("")){
							 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
							 box.setMessage("请添加电影时长！");
							 box.setText("提示信息");
							 box.open();
							 text_3.setFocus();
						}else if(flag1){
							 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
							 box.setMessage("请添加影片语言");
							 box.setText("提示信息");
							 box.open();
							
						}else if(flag2){
							 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
							 box.setMessage("请选择影片类型");
							 box.setText("提示信息");
							 box.open();
							
						}else if(introduction.equals("")){
							 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
							 box.setMessage("请添加影片介绍");
							 box.setText("提示信息");
							 box.open();
							
						}else{
							String sql1="select count(*) as count from film_info where film_name='"+name+"'";
						 //	System.out.println(ju.query(sql1).get(0).get("count"));
							
							if(Integer.parseInt(ju.query(sql1).get(0).get("count").toString())==1){
								 box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
								 box.setMessage("电影重复，请检查电影名称");
								 box.setText("提示信息");
								 box.open();
							}else{
								int duration=Integer.parseInt(time.toString());
								String sql="insert into film_info (film_name,film_country,film_director,film_protagonist,film_duration,film_language,film_type,film_update,film_downdate,film_introduction,film_image) " +
										"values('"+name+"','"+country+"','"+director+"','"+protagonist+"',"+duration+",'"+language+"','"+type+"','"+date+"','"+date1+"','"+introduction+"','"+targetFile.getName()+"')";
								ju.update(sql);
								 box=new MessageBox(container.getShell(),SWT.ICON_INFORMATION);
								 box.setMessage("录入成功");
								 box.setText("提示信息");
								 box.open();
								 text.setText("");
								combo.select(-1);
								text_1.setText("");
								text_2.setText("");
								text_3.setText("");				
								button_5.setSelection(false);
								button_6.setSelection(false);
								button_7.setSelection(false);
								button.setSelection(false);
								button_1.setSelection(false);
								button_2.setSelection(false);
								button_10.setSelection(false);
								button_3.setSelection(false);
								button_4.setSelection(false);	
								btnNewButton.setImage(null);
								text_4.setText("");	
								Date d=new Date();
								String str1=d.toLocaleString();
								String[]ss=str1.split(" ")[0].split("-");
								dateTime.setDate(Integer.parseInt(ss[0]),Integer.parseInt(ss[1])-1, Integer.parseInt(ss[2]));
								dateTime_1.setDate(Integer.parseInt(ss[0]),Integer.parseInt(ss[1])-1, Integer.parseInt(ss[2]));
								text.setFocus();
							}					
						}
						
				}else{
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("图片未能初始化");
					 box.setText("提示信息");
					 box.open();
				}
												 							    				 
				
		}
		});
		button_9.setBounds(724, 571, 65, 33);
		button_9.setText("\u5F55\u5165");

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
		return "AddFilmInfoEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "AddFilmInfoEditor";
	}
}
