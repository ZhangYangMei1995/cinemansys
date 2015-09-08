package cn.com.shxt.editor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import cn.com.shxt.dialog.FilmNameSearchDialog;
import cn.com.shxt.util.JdbcUtil;
import cn.com.shxt.util.StringRegexUtils;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddOnShowInfoEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.AddOnShowInfoEditor"; //$NON-NLS-1$
	private Text text_2;
	private Text text_4;
	private MessageBox box;
	private JdbcUtil ju=new JdbcUtil();
	private String hours1;
	private String seconds1;
	private String minutes1;
	private Date f_downdate;
	private Text text;
	private Text text_1;
	private String start_time;
	private String end_time;
	public AddOnShowInfoEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setBounds(51, 54, 889, 545);
		
		Label label = new Label(composite, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label.setText("\u7535\u5F71\u540D\u79F0\uFF1A");
		label.setBounds(154, 49, 61, 23);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_1.setText("\u5F71\u5385\u540D\u79F0\uFF1A");
		label_1.setBounds(154, 109, 61, 17);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_2.setText("\u4E0A\u6620\u65E5\u671F\uFF1A");
		label_2.setBounds(154, 179, 61, 17);
		
		final DateTime dateTime = new DateTime(composite, SWT.BORDER | SWT.DROP_DOWN);
		dateTime.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		dateTime.setBounds(281, 172, 135, 24);
		text_1 = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(281, 299, 135, 23);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_3.setText("\u64AD\u653E\u65F6\u95F4\uFF1A");
		label_3.setBounds(154, 243, 61, 17);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_5.setText("\u4EF7     \u683C\uFF1A");
		label_5.setBounds(154, 361, 61, 17);
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(281, 363, 135, 23);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_6.setText("\u5143");
		label_6.setBounds(431, 373, 61, 17);
		
		final Button button_3 = new Button(composite, SWT.RADIO);
		button_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_3.setText("\u4E2D\u6587");
		button_3.setBounds(281, 440, 50, 17);
		
		final Button button_4 = new Button(composite, SWT.RADIO);
		button_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		button_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_4.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_4.setText("\u82F1\u6587");
		button_4.setBounds(355, 440, 61, 17);
		
		final Button button_2 = new Button(composite, SWT.RADIO);
		button_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		button_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_2.setBounds(441, 440, 88, 17);
		button_2.setText("\u4E2D\u6587/\u82F1\u6587");

		final Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setBounds(281, 109, 135, 25);
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button.addSelectionListener(new SelectionAdapter() {
			/*上映计划*/
			public void widgetSelected(SelectionEvent e) {
				String f_name=text.getText().trim();
				String r_name=combo.getText().trim();
				String date=dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
				start_time=text_2.getText().trim();
				Date now_time=new Date();//当前时刻，如果电影下线，不能添加放映计划
				
				if(f_name.equals("")){					
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("影片名称不能为空！");
					box.open();
					text.setFocus();
				}else if(r_name.equals("")){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("影厅不能为空！");
					box.open();
				}else if(start_time.equals("")){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间不能为空！");
					box.open();
					text_2.setFocus();
				}else if(start_time.length()!=8){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间格式不正确！");
					box.open();
					text_2.setText("");
					text_2.setFocus();
				}else if(Integer.parseInt(start_time.split(":")[0])>23||Integer.parseInt(start_time.split(":")[0])<0
						||Integer.parseInt(start_time.split(":")[1])>60||Integer.parseInt(start_time.split(":")[1])<0||
						Integer.parseInt(start_time.split(":")[2])>60||Integer.parseInt(start_time.split(":")[2])<0){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间格式不正确！");
					box.open();
					text_2.setText("");
					text_2.setFocus();
				}else if(text_4.getText().equals("")){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("价格不能为空！");
					box.open();
					text_4.setFocus();
				}else if(!StringRegexUtils.isRegexpValidate(text_4.getText(), StringRegexUtils.positive_integer_regexp)){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("价格必须为正整数！");
					box.open();
					text_4.setFocus();
				}else {
					String sql="select * from film_info where film_name='"+f_name+"'";
					List<Map<String,Object>>list=ju.query(sql);
					if(list.size()==0){
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("不存在该影片！");
						box.open();
						text.setText("");
						text.setFocus();
					}else{
						String sql2="select film_downdate from film_info where film_name='"+f_name+"'";
						//电影表里电影的下线时间f_downdate
						try {
							f_downdate = new SimpleDateFormat("yyyy-MM-dd").parse(ju.query(sql2).get(0).get("film_downdate").toString());
						} catch (ParseException e1) {
							e1.printStackTrace();
						} 
						if(now_time.getTime()>f_downdate.getTime()){
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("该电影已经下线，不能上映！");
							box.open();
							text.setText("");
							text.setFocus();
						}else{
							//d是上映时所选的日期    判断该影厅是否被占用
							String d = dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
							String sql1="select showroom_id  from showroom_info where showroom_name='"+r_name+"'";
							int osroom_id = Integer.parseInt(ju.query(sql1).get(0).get("showroom_id").toString());
							String sql4 = "select * from onshow_info where osroom_id = "+osroom_id+" and onshow_date = '"+d+"' and filmend_time >'"+start_time+"' and filmstart_time < '"+end_time+"' ";
							List<Map<String,Object>>list1=ju.query(sql4);
							if(list1.size()!=0){
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("该影厅已占用！");
								box.open();
							}else{
								float price=Float.parseFloat(text_4.getText().trim().toString());
								String language1="";
								if(button_3.getSelection()){
									language1="中文";
								}else if(button_4.getSelection()){
									language1="英文";
								}else if(button_2.getSelection()){
									language1="中文/英文";
								}
								//把时间00:00:00转化成秒，加上影片时间长后，在转化成00:00:00样式存储在放映计划表
								String hours=start_time.split(":")[0];
								String minutes=start_time.split(":")[1];
								String seconds=start_time.split(":")[2];
								
								int time=Integer.parseInt(hours)*3600+Integer.parseInt(minutes)*60+Integer.parseInt(seconds);
								int time1=time+Integer.parseInt(list.get(0).get("film_duration").toString())*60;
								int h=time1/3600;
								int m=(time1%3600)/60;
								int s=(time1%3600)%60;
								if(h<10){
									hours1="0"+h+"";
								}else{
									hours1=String.valueOf(h);
								}
								if(s<10){
									seconds1="0"+s+"";
								}else{
									seconds1=String.valueOf(s);
								}
								if(m<10){
									 minutes1="0"+m+"";
								}else{
									minutes1=String.valueOf(m);
								}
								end_time=hours1.trim()+":"+minutes1.trim()+":"+seconds1.trim();							
								int film_id=Integer.parseInt(list.get(0).get("film_id").toString());
							//	int room_id=Integer.parseInt(list1.get(0).get("showroom_id").toString());
								String sql3="insert into onshow_info (osfilm_id,osroom_id,onshow_date,filmstart_time,filmend_time,film_language,film_price) " +
										"values ("+film_id+","+osroom_id+",'"+date+"','"+start_time+"','"+end_time+"','"+language1+"',"+price+")";
								ju.update(sql3);
								box = new MessageBox(container.getShell(), SWT.ICON_INFORMATION);
								box.setText("提示信息");
								box.setMessage("放映计划添加成功！");
								box.open();
								text.setText("");
								text_1.setText("");
								text_2.setText("");
								text_4.setText("");
								button_2.setSelection(false);
								button_3.setSelection(false);
								button_4.setSelection(false);
								Date dt=new Date();
								String str=dt.toLocaleString();
								String[]ss=str.split(" ")[0].split("-");
								dateTime.setDate(Integer.parseInt(ss[0]),Integer.parseInt(ss[1])-1, Integer.parseInt(ss[2]));
							}
						}
					}
				}
			}
		});
		button.setText("\u589E\u52A0\u4E0A\u6620\u8BA1\u5212");
		button.setBounds(659, 430, 114, 27);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_8.setText("\u64AD\u653E\u8BED\u8A00\uFF1A");
		label_8.setBounds(154, 435, 61, 17);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(281, 49, 135, 23);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_1.addSelectionListener(new SelectionAdapter() {
			/*模糊查询电影名称*/
			public void widgetSelected(SelectionEvent e) {
				FilmNameSearchDialog fnd = new FilmNameSearchDialog(container.getShell(), SWT.NONE);
				fnd.open();
				if(!FilmNameSearchDialog.name.equals("")&&FilmNameSearchDialog.name!=null){
					text.setText(FilmNameSearchDialog.name);
				}
			}
		});
		button_1.setBounds(483, 45, 80, 27);
		button_1.setText("\u6A21\u7CCA\u67E5\u8BE2");
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_4.setText("\u7ED3\u675F\u65F6\u95F4\uFF1A");
		label_4.setBounds(154, 299, 61, 17);
		
		String sql = "select showroom_name from showroom_info ";
		List<Map<String,Object>> list = ju.query(sql);
		for(int i = 0 ; i < list.size() ; i ++){
			combo.add(list.get(i).get("showroom_name").toString());
		}
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.addTraverseListener(new TraverseListener() {
			//键盘按显示电影结束时间
			public void keyTraversed(TraverseEvent arg0) {
				String f_name=text.getText().trim();
				start_time=text_2.getText().trim();
				/*String pat ="[0-2][0-4]:[0-5][0-9]:[0-5][0-9]";
				String str ="start_time";
				Pattern pattern = Pattern.compile(pat);
			    Matcher macher = pattern.matcher(str);
				boolean tag = macher.matches();*/
				if(f_name.equals("")){					
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("影片名称不能为空！");
					box.open();
					text.setFocus();
				}else if(start_time.equals("")){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间不能为空！");
					box.open();
					text_2.setFocus();
				}/*else if(!tag){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间格式不正确！");
					box.open();
					text_2.setText("");
					text_2.setFocus();
				}*/
				else if(start_time.length()!=8){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间格式不正确！");
					box.open();
					text_2.setText("");
					text_2.setFocus();
				}else if(Integer.parseInt(start_time.split(":")[0])>23||Integer.parseInt(start_time.split(":")[0])<0
						||Integer.parseInt(start_time.split(":")[1])>60||Integer.parseInt(start_time.split(":")[1])<0||
						Integer.parseInt(start_time.split(":")[2])>60||Integer.parseInt(start_time.split(":")[2])<0){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间格式不正确！");
					box.open();
					text_2.setText("");
					text_2.setFocus();
				}else{
					String sql="select * from film_info where film_name='"+f_name+"'";
					List<Map<String,Object>>list=ju.query(sql);
					
					String hours=start_time.split(":")[0];
					String minutes=start_time.split(":")[1];
					String seconds=start_time.split(":")[2];
					
					int time=Integer.parseInt(hours)*3600+Integer.parseInt(minutes)*60+Integer.parseInt(seconds);
					int time1=time+Integer.parseInt(list.get(0).get("film_duration").toString())*60;
					int h=time1/3600;
					int m=(time1%3600)/60;
					int s=(time1%3600)%60;
					if(h<10){
						hours1="0"+h+"";
					}else{
						hours1=String.valueOf(h);
					}
					if(s<10){
						seconds1="0"+s+"";
					}else{
						seconds1=String.valueOf(s);
					}
					if(m<10){
						 minutes1="0"+m+"";
					}else{
						minutes1=String.valueOf(m);
					}
					end_time=hours1.trim()+":"+minutes1.trim()+":"+seconds1.trim();	
					text_1.setText(end_time);
				}
				}
				
		});
		text_2.setBounds(281, 240, 135, 23);
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
		return "AddOnShowInfoEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "AddOnShowInfoEditor";
	}
}
