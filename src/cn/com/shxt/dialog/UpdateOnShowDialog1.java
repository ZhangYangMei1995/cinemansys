package cn.com.shxt.dialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import cn.com.shxt.util.JdbcUtil;
import cn.com.shxt.util.StringRegexUtils;
import org.eclipse.wb.swt.SWTResourceManager;

public class UpdateOnShowDialog1 extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_2;
	private int id;
	private String f_name;
	private String r_name;
	private JdbcUtil ju = new JdbcUtil();
	private MessageBox box;
	private String hours1;
	private String seconds1;
	private String minutes1;
	private Date f_downdate;
	private Text text_3;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdateOnShowDialog1(Shell parent, int style) {
		super(parent, style);
		setText("放映计划修改1");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(int id,String f_name,String r_name)  {
		this.f_name=f_name;
		this.r_name=r_name;
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
		shell.setText("\u653E\u6620\u8BA1\u5212\u4FEE\u6539");
		shell.setBounds(270, 20, 830, 542);
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u7535\u5F71\u540D\u79F0\uFF1A");
		label.setBounds(162, 10, 61, 23);
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setText("<dynamic>");
		text.setBounds(271, 10, 135, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5F71\u5385\u540D\u79F0\uFF1A");
		label_1.setBounds(162, 102, 61, 17);
		
		final Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.setBounds(271, 94, 135, 25);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u4E0A\u6620\u65E5\u671F\uFF1A");
		label_2.setBounds(162, 190, 61, 17);
		
		final DateTime dateTime = new DateTime(shell, SWT.BORDER | SWT.DROP_DOWN);
		dateTime.setBounds(271, 183, 135, 24);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u64AD\u653E\u65F6\u95F4\uFF1A");
		label_3.setBounds(162, 275, 61, 17);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u4EF7     \u683C\uFF1A");
		label_4.setBounds(162, 371, 61, 17);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(271, 272, 135, 23);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u64AD\u653E\u8BED\u8A00\uFF1A");
		label_5.setBounds(162, 449, 61, 17);
		
		final Button button = new Button(shell, SWT.RADIO);
		button.setText("\u4E2D\u6587");
		button.setBounds(271, 449, 50, 17);
		
		final Button button_1 = new Button(shell, SWT.RADIO);
		button_1.setText("\u82F1\u6587");
		button_1.setBounds(345, 449, 61, 17);
		
		final Button button_2 = new Button(shell, SWT.RADIO);
		button_2.setText("\u4E2D\u6587/\u82F1\u6587");
		button_2.setBounds(431, 449, 88, 17);
		
		Button button_3 = new Button(shell, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			/*放映计划修改*/
			public void widgetSelected(SelectionEvent e) {

				String f_name=text.getText().trim();
				String r_name=combo.getText().trim();
				String date=dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
				String start_time=text_2.getText().trim();
				Date now_time=new Date();//当前时刻，如果电影下线，不能添加放映计划
				if(r_name.equals("")){
					box = new MessageBox(shell, SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("影厅不能为空！");
					box.open();
				}else if(start_time.equals("")){
					box = new MessageBox(shell, SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间不能为空！");
					box.open();
					text_2.setFocus();
				}else if(start_time.length()!=8){
					box = new MessageBox(shell, SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间格式不正确！");
					box.open();
					text_2.setFocus();
				}else if(Integer.parseInt(start_time.split(":")[0])>23||Integer.parseInt(start_time.split(":")[0])<0
						||Integer.parseInt(start_time.split(":")[1])>60||Integer.parseInt(start_time.split(":")[1])<0||
						Integer.parseInt(start_time.split(":")[2])>60||Integer.parseInt(start_time.split(":")[2])<0){
					box = new MessageBox(shell, SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("开始时间格式不正确！");
					box.open();
					text_2.setText("");
					text_2.setFocus();
				}else if(text_3.getText().equals("")){
					box = new MessageBox(shell, SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("价格不能为空！");
					box.open();
					text_3.setFocus();
				}else if(!StringRegexUtils.isRegexpValidate(text_3.getText(), StringRegexUtils.positive_integer_regexp)){
					box = new MessageBox(shell, SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("价格必须为正整数！");
					box.open();
					text_3.setFocus();
				}else {
						String sql2="select film_downdate from film_info where film_name='"+f_name+"'";
						//电影表里电影的下线时间f_downdate
						try {
							f_downdate = new SimpleDateFormat("yyyy-MM-dd").parse(ju.query(sql2).get(0).get("film_downdate").toString());
						} catch (ParseException e1) {
							e1.printStackTrace();
						} 
						if(now_time.getTime()>f_downdate.getTime()){
							box = new MessageBox(shell, SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("该电影已经下线，不能上映！");
							box.open();
							text.setText("");
							text.setFocus();
						}else{
							String sql1="select * from showroom_info where showroom_name='"+r_name+"'";
							List<Map<String,Object>>list1=ju.query(sql1);
							if(list1.size()==0){
								box = new MessageBox(shell, SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("不存在该影厅！");
								box.open();
							}else{
								String sql="select * from film_info where film_name='"+f_name+"'";
								List<Map<String,Object>>list=ju.query(sql);
								float price=Float.parseFloat(text_3.getText().trim().toString());
								String language1="";
								if(button.getSelection()){
									language1="中文";
								}else if(button_1.getSelection()){
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
									minutes1=String.valueOf(s);
								}
								String end_time=hours1.trim()+":"+minutes1.trim()+":"+seconds1.trim();							
								int room_id=Integer.parseInt(list1.get(0).get("showroom_id").toString());
								String sql3="update onshow_info set osroom_id = "+room_id+",onshow_date = '"+date+"',filmstart_time = '"+start_time+"',filmend_time = '"+end_time+"',film_language = '"+language1+"',film_price = "+price+"  where onshow_id = "+id+"";
								ju.update(sql3);
								box = new MessageBox(shell, SWT.ICON_INFORMATION);
								box.setText("提示信息");
								box.setMessage("放映计划修改成功！");
								box.open();
								shell.dispose();
							}
						}
					}
				
			}
		});
		button_3.setText("\u4FEE\u6539\u4E0A\u6620\u8BA1\u5212");
		button_3.setBounds(541, 444, 114, 27);

		String sql = " select * from onshow_info where onshow_id = "+id+"";
		List<Map<String,Object>> list = ju.query(sql);
		//放映日期date
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(0).get("onshow_date").toString());
			dateTime.setDate(date.getYear(), (date.getMonth()-1), date.getDay());
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(271, 368, 135, 23);
		
		text.setText(f_name);
		text_2.setText(list.get(0).get("filmstart_time").toString());
		text_3.setText(list.get(0).get("film_price").toString());
		
		
		
		if(list.get(0).get("film_language").toString().equals("中文")){
			button.setSelection(true);
		}else if(list.get(0).get("film_language").toString().equals("英文")){
			button_1.setSelection(true);
		}else if(list.get(0).get("film_language").toString().equals("中文/英文")){
			button_2.setSelection(true);
		}
		String sql1 = "select showroom_name from showroom_info ";
		List<Map<String,Object>> list1 = ju.query(sql1);
		for(int i = 0 ; i < list1.size() ; i ++){
			combo.add(list1.get(i).get("showroom_name").toString());
		}
		combo.setText(r_name);
	}

}
