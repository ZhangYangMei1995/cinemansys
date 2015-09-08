package cn.com.shxt.dialog;

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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.core.Activator;
import cn.com.shxt.util.JdbcUtil;
import org.eclipse.swt.widgets.DateTime;

public class UpdateFilmInfoDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private MessageBox box;
	private JdbcUtil ju=new JdbcUtil();
	private String id;
	private String source;
	private String target;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdateFilmInfoDialog(Shell parent, int style) {
		super(parent, style);
		setText("影片修改");
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX | SWT.APPLICATION_MODAL);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\image\\402.jpg"));
		shell.setBounds(200, 0, 800, 800);
		shell.setSize(809, 737);
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("\u5F71\u7247\u4FEE\u6539 ");
		group.setBounds(10, 10, 777, 663);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("\u7535\u5F71\u540D\u79F0\uFF1A");
		label.setBounds(230, 27, 61, 17);
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(319, 27, 118, 23);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("\u56FD     \u5BB6\uFF1A");
		label_1.setBounds(230, 74, 61, 17);
		
		final Combo combo = new Combo(group, SWT.READ_ONLY);
		combo.setItems(new String[] {"\u4E2D\u56FD", "\u7F8E\u56FD", "\u82F1\u56FD", "\u6CF0\u56FD", "\u9A6C\u6765\u897F\u4E9A", "\u6FB3\u5927\u5229\u4E9A", "\u65E5\u672C"});
		combo.setBounds(319, 74, 118, 25);
		combo.select(-1);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u5BFC      \u6F14\uFF1A");
		label_2.setBounds(230, 125, 61, 17);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("\u4E3B     \u6F14\uFF1A");
		label_3.setBounds(230, 170, 61, 17);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("\u65F6     \u957F\uFF1A");
		label_4.setBounds(230, 223, 61, 17);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("\u8BED     \u8A00\uFF1A");
		label_5.setBounds(230, 275, 61, 17);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("\u7C7B     \u578B\uFF1A");
		label_6.setBounds(230, 312, 61, 17);
		
		final Button button = new Button(group, SWT.CHECK);
		button.setText("\u60CA\u609A");
		button.setBounds(383, 312, 45, 17);
		
		final Button button_1 = new Button(group, SWT.CHECK);
		button_1.setText("\u6B66\u4FA0");
		button_1.setBounds(452, 312, 61, 17);
		
		final Button button_2 = new Button(group, SWT.CHECK);
		button_2.setText("\u8BB0\u5F55");
		button_2.setBounds(383, 350, 45, 17);
		
		final Button button_3 = new Button(group, SWT.CHECK);
		button_3.setText("\u79D1\u5E7B");
		button_3.setBounds(452, 350, 51, 17);
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(319, 125, 118, 23);
		
		text_2 = new Text(group, SWT.BORDER | SWT.MULTI);
		text_2.setBounds(319, 170, 118, 23);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u7B80     \u4ECB\uFF1A");
		label_7.setBounds(155, 488, 61, 17);
		
		text_3 = new Text(group, SWT.BORDER);
		text_3.setBounds(319, 223, 118, 23);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("\u5206\u949F");
		label_8.setBounds(442, 235, 61, 17);
		
		final Button button_4 = new Button(group, SWT.RADIO);
		button_4.setText("\u82F1\u6587");
		button_4.setBounds(319, 275, 45, 17);
		
		final Button button_5 = new Button(group, SWT.RADIO);
		button_5.setText("\u4E2D\u6587");
		button_5.setBounds(374, 275, 45, 17);
		
		final Button button_6 = new Button(group, SWT.RADIO);
		button_6.setText("\u4E2D\u6587/\u82F1\u6587");
		button_6.setBounds(442, 275, 97, 17);
		
		text_4 = new Text(group, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		text_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_4.setBounds(222, 485, 447, 152);
		
		final Button button_7 = new Button(group, SWT.CENTER);
		button_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_7.addSelectionListener(new SelectionAdapter() {
			/*照片修改*/
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(shell,SWT.NONE);//创建一个可打开的文件选择框
				source=fd.open();//open返回的是所选文件绝对路径
				if(source!=null){
					button_7.setImage(SWTResourceManager.getImage(source));
				}
			}
		});
		button_7.setText("photo");
		button_7.setFont(SWTResourceManager.getFont("@Meiryo", 14, SWT.NORMAL));
		button_7.setBounds(23, 22, 166, 218);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setText("\u7167\u7247");
		label_9.setBounds(10, 265, 51, 17);
		
		Button button_8 = new Button(group, SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter() {
		/*照片修改*/
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(shell,SWT.NONE);//创建一个可打开的文件选择框
				source=fd.open();//open返回的是所选文件绝对路径
				if(source!=null){
					button_7.setImage(SWTResourceManager.getImage(source));
				}
			}
		});
		button_8.setText("\u6D4F\u89C8");
		button_8.setBounds(109, 265, 80, 27);
		
		final Button button_9 = new Button(group, SWT.CHECK);
		button_9.setText("\u559C\u5267");
		button_9.setBounds(319, 350, 45, 17);
		
		final Button button_10 = new Button(group, SWT.CHECK);
		button_10.setText("\u8B66\u532A");
		button_10.setBounds(319, 312, 45, 17);
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setText("\u4E0A\u7EBF\u65E5\u671F\uFF1A");
		label_10.setBounds(230, 391, 61, 17);
		
		final DateTime dateTime = new DateTime(group, SWT.BORDER | SWT.DROP_DOWN);
		dateTime.setBounds(319, 391, 116, 24);
		
		Label label_11 = new Label(group, SWT.NONE);
		label_11.setText("\u4E0B\u7EBF\u65E5\u671F\uFF1A");
		label_11.setBounds(230, 445, 61, 17);
		
		final DateTime dateTime_1 = new DateTime(group, SWT.BORDER | SWT.DROP_DOWN);
		dateTime_1.setBounds(319, 445, 116, 24);
		
		List<Map<String,Object>>l=ju.query("select film_image from film_info where film_id='"+id+"'");
		String im=l.get(0).get("film_image").toString();
		source="E:\\javaworkplace\\cinemamanagerment.system\\icons\\"+im;
		/*获得各种值*/
		String sql="select * from film_info where film_id='"+id+"'";
		List<Map<String, Object>> list=ju.query(sql);
		text.setText(list.get(0).get("film_name").toString());
		combo.setText(list.get(0).get("film_country").toString());
		text_1.setText(list.get(0).get("film_director").toString());
		text_2.setText(list.get(0).get("film_protagonist").toString());
		text_3.setText(list.get(0).get("film_duration").toString());
		text_4.setText(list.get(0).get("film_introduction").toString());
		String image=list.get(0).get("film_image").toString();
		button_7.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\"+image));
		//获取上线时间和下线时间
		String str=ju.query(sql).get(0).get("film_update").toString();
		String str1=ju.query(sql).get(0).get("film_downdate").toString();
		String[]ss=str.split("-");
		String[]ss1=str1.split("-");
		dateTime.setDate(Integer.parseInt(ss[0]),Integer.parseInt(ss[1])-1, Integer.parseInt(ss[2]));
		dateTime_1.setDate(Integer.parseInt(ss1[0]),Integer.parseInt(ss1[1])-1, Integer.parseInt(ss1[2]));
		
		
		
		Button button_11 = new Button(group, SWT.NONE);
		button_11.setBounds(687, 618, 80, 25);
		button_11.addSelectionListener(new SelectionAdapter() {
			/*修改*/
			public void widgetSelected(SelectionEvent e) {				
				if(button_7.getImage()!=null){
					File sourceFile = new File(source);					
					URL url=Activator.getDefault().getBundle().getResource("icons");					
					String str="";
					try {
						str=FileLocator.toFileURL(url).toString().substring(6);						
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
					if(button_4.getSelection()==true){
						language="英文";
					}else if(button_5.getSelection()==true){
						language="中文";
					}else{
						language="中文/英文";
					}
					String type="";
					if(button_10.getSelection()==true){
						type+="警匪 ";
					}
					if(button.getSelection()==true){
						type+="惊悚 ";
					}
					if(button_1.getSelection()==true){
						type+="武侠 ";
					}
					if(button_9.getSelection()==true){
						type+="喜剧 ";
					}
					if(button_2.getSelection()==true){
						type+="记录 ";
					}
					if(button_3.getSelection()==true){
						type+="科幻";
					}	
					
					boolean flag1=button_4.getSelection()==false&&button_5.getSelection()==false&&button_6.getSelection()==false;
					boolean flag2=button.getSelection()==false&&button_1.getSelection()==false&&button_2.getSelection()==false&&button_10.getSelection()==false&&button_3.getSelection()==false&&button_9.getSelection()==false;
					
					if(name.equals("")){
						box=new MessageBox(shell,SWT.ICON_ERROR);
						 box.setMessage("请添加影片名称");
						 box.setText("提示信息");
						 box.open();
						 text.setFocus();
					}else if(director.equals("")){
						 box=new MessageBox(shell.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请添加导演");
						 box.setText("提示信息");
						 box.open();
						 text_1.setFocus();
					}else if(protagonist.equals("")){
						 box=new MessageBox(shell.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请选择主演");
						 box.setText("提示信息");
						 box.open();
						 text_2.setFocus();
					}else if(time.equals("")){
						 box=new MessageBox(shell.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请添加电影时长！");
						 box.setText("提示信息");
						 box.open();
						 text_3.setFocus();
					}else if(flag1){
						 box=new MessageBox(shell.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请添加影片语言");
						 box.setText("提示信息");
						 box.open();						
					}else if(flag2){
						 box=new MessageBox(shell.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请选择影片类型");
						 box.setText("提示信息");
						 box.open();						
					}else{				
							int duration=Integer.parseInt(time.toString());
							String sql="update film_info set film_name='"+name+"',film_country='"+country+"',film_director='"+director+"',film_protagonist='"+protagonist+"',film_duration='"+duration+"',film_language='"+language+"',film_type='"+type+"',film_update='"+date+"',film_downdate='"+date1+"',film_introduction='"+introduction+"',film_image='"+targetFile.getName()+"' where film_id="+id+"";
							ju.update(sql);
							 box=new MessageBox(shell.getShell(),SWT.ICON_INFORMATION);
							 box.setMessage("修改成功");
							 box.setText("提示信息");
							 box.open();			
							shell.dispose();//关闭dialog			
					}					
			}else{
				box=new MessageBox(shell,SWT.ICON_ERROR);
				 box.setMessage("图片未能初始化");
				 box.setText("提示信息");
				 box.open();
			    }
			}
		});
		button_11.setText("\u4FEE\u6539");
		if(list.get(0).get("film_language").equals("英文")){
			button_4.setSelection(true);
		}else if(list.get(0).get("film_language").equals("英文")){
			button_5.setSelection(true);
		}else {
			button_6.setSelection(true);
		}
		
		String []array=list.get(0).get("film_type").toString().split(" ");
		for(int i=0;i<array.length;i++){
			if(array[i].equals("警匪")){
				button_10.setSelection(true);
			}else if(array[i].equals("喜剧")){
				button_9.setSelection(true);
			}else if(array[i].equals("惊悚")){
				button.setSelection(true);
			}else if(array[i].equals("武侠")){
				button_1.setSelection(true);
			}else if(array[i].equals("记录")){
				button_2.setSelection(true);
			}else if(array[i].equals("科幻")){
				button_3.setSelection(true);
			}
		}
	 }
	}

