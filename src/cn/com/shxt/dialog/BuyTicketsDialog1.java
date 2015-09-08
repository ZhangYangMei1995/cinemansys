package cn.com.shxt.dialog;

import java.util.Date;
import java.util.LinkedList;
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

import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;

public class BuyTicketsDialog1 extends Dialog {

	protected Object result;
	protected Shell shell;
	private String name;
	private String start_time;
	private String end_time;
	private String seat_1 = "";
	private JdbcUtil ju=new JdbcUtil();
	private MessageBox box;
	private Text text;
	private int onshow_id ;
	private float money ;
	private Text text_1;
	private Text text_2;
	private String time;
	private float price;
	private float banlance ;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public BuyTicketsDialog1(Shell parent, int style) {
		super(parent, style);
		setText("自助购票");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String name,String start_time,String end_time,int onshow_id) {
		this.onshow_id = onshow_id;
		this.name=name;
		this.start_time=start_time;
		this.end_time=end_time;
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
		shell.setBounds(250,10,994, 761);
		shell.setText(getText());
		
		final Label lblNihao = new Label(shell, SWT.NONE);
		lblNihao.setBounds(757, 547, 61, 17);
		final Label label_21 = new Label(shell, SWT.NONE);
		label_21.setBounds(845, 547, 61, 17);
		
		Label label = new Label(shell, SWT.BORDER);
		//label.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\<dynamic>"));
		label.setBounds(38, 10, 147, 169);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u70ED\u6620");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("Courier", 14, SWT.BOLD));
		label_1.setBounds(87, 199, 42, 23);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_2.setText("\u7535\u5F71\u540D\u79F0\uFF1A");
		label_2.setBounds(10, 241, 61, 17);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_3.setText("\u56FD     \u5BB6\uFF1A");
		label_3.setBounds(10, 291, 61, 17);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_4.setText("\u5BFC     \u6F14\uFF1A");
		label_4.setBounds(10, 342, 61, 17);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_5.setText("\u4E3B     \u6F14\uFF1A");
		label_5.setBounds(10, 393, 61, 17);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_6.setText("\u8BED     \u8A00\uFF1A");
		label_6.setBounds(10, 435, 61, 17);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_7.setText("\u7C7B     \u578B\uFF1A");
		label_7.setBounds(10, 488, 61, 17);
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_8.setText("\u5F00\u59CB\u65F6\u95F4\uFF1A");
		label_8.setBounds(10, 547, 61, 17);
		
		Label label_9 = new Label(shell, SWT.NONE);
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_9.setText("\u8BE6\u7EC6\u4ECB\u7ECD\uFF1A");
		label_9.setBounds(10, 633, 61, 17);
		
		Label label_10 = new Label(shell, SWT.BORDER);
		label_10.setBounds(98, 240, 105, 17);
		
		Label label_11 = new Label(shell, SWT.BORDER);
		label_11.setBounds(98, 291, 105, 17);
		
		Label label_12 = new Label(shell, SWT.BORDER);
		label_12.setBounds(98, 342, 105, 17);
		
		Label label_13 = new Label(shell, SWT.BORDER);
		label_13.setBounds(98, 393, 105, 17);
		
		Label label_14 = new Label(shell, SWT.BORDER);
		label_14.setBounds(98, 435, 105, 17);
		
		Label label_15 = new Label(shell, SWT.BORDER);
		label_15.setBounds(98, 487, 105, 17);
		
		Label label_16 = new Label(shell, SWT.BORDER);
		label_16.setText("<dynamic>");
		label_16.setBounds(98, 546, 105, 17);
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		text.setBounds(87, 630, 364, 71);
		
		Label label_17 = new Label(shell, SWT.NONE);
		label_17.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_17.setText("\u7ED3\u675F\u65F6\u95F4\uFF1A");
		label_17.setBounds(10, 591, 61, 17);
		
		Label label_18 = new Label(shell, SWT.BORDER);
		label_18.setText("<dynamic>");
		label_18.setBounds(98, 590, 105, 17);
		
		int x = 341;
		int y = 62;
		//选座位放saleSeat里
		final List<String> saleSeat = new LinkedList<String>();
		/*List<Map<String, Object>> list_s = ju.query("select * from showroomseat_info where showroom_id = "+list1+"");*/
		//电影表 根据电影名称查出电影编号
		String sql="select * from film_info where film_name='"+name+"'";
		List<Map<String,Object>>list=ju.query(sql);
		//放映计划表 根据影厅编号查到放映厅编号
		String sql1="select osroom_id  from onshow_info where onshow_id = "+onshow_id+"";
		List<Map<String,Object>> list1 = ju.query(sql1);
		
		//影厅表  根据放映厅编号查到放映厅名称和类型
		String sql2="select * from showroom_info where showroom_id="+list1.get(0).get("osroom_id")+"";
		List<Map<String,Object>> list2 = ju.query(sql2);
		//影厅座位表   根据放映厅编号查到该影厅每排对应的座位数
		String sql3="select showroomseat_row,showroomseat_list from showroomseat_info " +
				"where showroom_id="+list1.get(0).get("osroom_id")+"";
		List<Map<String,Object>> list3 = ju.query(sql3);
		for (Map<String, Object> map : list3) {
			int cols = Integer.parseInt(map.get("showroomseat_list").toString());
			for (int i = 1; i <= cols; i++) {
				final Button button_s = new Button(shell, SWT.CENTER);
				button_s.setBounds(x, y, 30, 25);
				//button_s.setText(String.valueOf(i));
				button_s.setData(map.get("showroomseat_row") + ":" + i);// 存储行、列的信息进入每个按钮
				button_s.setImage(SWTResourceManager.getImage(BuyTicketsDialog1.class, "/cn/com/shxt/image/blue.gif"));
				
				String sql4 = "select sell_seat from selltable_info where onshow_id = "+onshow_id+"" ;
				if(ju.query(sql4).size()!=0){
					for(int k = 0 ; k< ju.query(sql4).size() ; k ++){
						if(ju.query(sql4).get(k).get("sell_seat").toString().equals(button_s.getData())){
							button_s.setImage(SWTResourceManager.getImage(BuyTicketsDialog1.class, "/cn/com/shxt/image/pink.gif"));
							button_s.setEnabled(false);
						}
					}
				}
				
				x += 67;
				button_s.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if(!saleSeat.contains(button_s.getData().toString())){
							if(saleSeat.size()<=4){
								saleSeat.add(button_s.getData().toString());
								button_s.setImage(SWTResourceManager.getImage(BuyTicketsDialog1.class, "/cn/com/shxt/image/red.gif"));
							}else {
								box=new MessageBox(shell,SWT.ICON_ERROR);
								 box.setMessage("每人最多买五张票");
								 box.setText("提示信息");
								 box.open();
							}
						}else{
							saleSeat.remove(button_s.getData().toString());
							button_s.setImage(SWTResourceManager.getImage(BuyTicketsDialog1.class, "/cn/com/shxt/image/blue.gif"));
						}
					}
				});
			}
			x = 341;
			y += 40;
		}
		String image=list.get(0).get("film_image").toString();
		label_10.setText(list.get(0).get("film_name").toString());
		label_11.setText(list.get(0).get("film_country").toString());
		label_12.setText(list.get(0).get("film_director").toString());
		label_13.setText(list.get(0).get("film_protagonist").toString());
		label_14.setText(list.get(0).get("film_language").toString());
		label_15.setText(list.get(0).get("film_type").toString());
		label_16.setText(start_time);
		label_18.setText(end_time);
		text.setText(list.get(0).get("film_introduction").toString());
		
		Label label_19 = new Label(shell, SWT.NONE);
		label_19.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_19.setText("\u8F93\u5165\u91D1\u989D\uFF1A");
		label_19.setBounds(517, 547, 61, 17);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(606, 544, 105, 23);
		
		Label label_20 = new Label(shell, SWT.NONE);
		label_20.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_20.setBounds(517, 608, 61, 17);
		label_20.setText("\u7528\u6237\u7F16\u53F7\uFF1A");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.addTraverseListener(new TraverseListener() {
			//判断用户的等级
			public void keyTraversed(TraverseEvent arg0) {
				String user_code =text_2.getText().trim();
				String sql = "select * from client_info where client_id = "+user_code+" ";
				List<Map<String,Object>> list = ju.query(sql);
				if(user_code.equals("")){
					box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("输入用户标识！！！");
					 box.setText("提示信息");
					 box.open();
				}else if (list.size()==0){
					box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("不存在该用户 ！！");
					 box.setText("提示信息");
					 box.open();
				}else{
					lblNihao.setText(list.get(0).get("client_grade").toString());
					lblNihao.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
					lblNihao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					if(list.get(0).get("client_grade").toString().equals("金卡")){
						label_21.setText("六折扣");
						label_21.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
						label_21.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(list.get(0).get("client_grade").toString().equals("银卡")){
						label_21.setText("八折扣");
						label_21.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
						label_21.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(list.get(0).get("client_grade").toString().equals("铜卡")){
						label_21.setText("九折扣");
						label_21.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
						label_21.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(list.get(0).get("client_grade").toString().equals("绿卡")){
						label_21.setText("为您积分！");
						label_21.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
						label_21.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					}
				}
			}
		});
		text_2.setBounds(606, 605, 105, 23);
		
		Button button_1 = new Button(shell, SWT.CENTER);
		button_1.addSelectionListener(new SelectionAdapter() {
			/*确定购票*/
			public void widgetSelected(SelectionEvent e) {
				//用户编号 
				String user_code =text_2.getText().trim();
				if(text_1.getText().trim().equals("")){
					box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("金额不能为空！！！");
					 box.setText("提示信息");
					 box.open();
				}else{
					if(!StringRegexUtils.isRegexpValidate(text_1.getText().trim(), StringRegexUtils.non_zero_negative_integers_regexp)){
						 box=new MessageBox(shell,SWT.ICON_ERROR);
						 box.setMessage("金额不正确！");
						 box.setText("提示信息");
						 box.open();
						 text_1.setFocus();
					 } 
				}
				
				money = Float.parseFloat(text_1.getText().trim());
				String sql_11 = "select * from client_info where client_id = "+user_code+" ";
				List<Map<String,Object>> list_1 = ju.query(sql_11);
				if(user_code.equals("")||text_1.getText().trim().equals("")){
					box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("输入信息不全！！！");
					 box.setText("提示信息");
					 box.open();
				}else if (list_1.size()==0){
					box=new MessageBox(shell,SWT.ICON_ERROR);
					 box.setMessage("不存在该用户 ！！");
					 box.setText("提示信息");
					 box.open();
				}else{
					String sql = "select client_grade from client_info where client_id = "+Integer.parseInt(user_code)+"";
					String sql_1= "select film_price from onshow_info where onshow_id = "+onshow_id+"";
				
					if(ju.query(sql).get(0).get("client_grade").toString().equals("金卡")){
						price = (float) (Float.parseFloat(ju.query(sql_1).get(0).get("film_price").toString())*0.6);
					}else if(ju.query(sql).get(0).get("client_grade").toString().equals("绿卡")){
						price = (float) (Float.parseFloat(ju.query(sql_1).get(0).get("film_price").toString())*1);
					}else if(ju.query(sql).get(0).get("client_grade").toString().equals("铜卡")){
						price = (float) (Float.parseFloat(ju.query(sql_1).get(0).get("film_price").toString())*0.9);
					}else if(ju.query(sql).get(0).get("client_grade").toString().equals("银卡")){
						price = (float) (Float.parseFloat(ju.query(sql_1).get(0).get("film_price").toString())*0.8);
					}
					if(saleSeat.size()*price>Float.parseFloat(text_1.getText().trim())){
						box=new MessageBox(shell,SWT.ICON_ERROR);
						 box.setMessage("金额不足！！！");
						 box.setText("提示信息");
						 box.open();
					}else{
						time = new Date ().toLocaleString().split(" ")[0];
						for(int i = 0 ; i < saleSeat.size() ; i ++){
							String seat = saleSeat.get(i);
							seat_1 += saleSeat.get(i)+"$";
							banlance = Float.parseFloat(text_1.getText().trim())-saleSeat.size()*price;
							String sql1 = "insert into selltable_info (user_id,onshow_id,sell_time,sell_seat,sell_price) values ('"+user_code+"',"+onshow_id+",'"+new Date().toLocaleString()+"','"+seat+"',"+price+")";
							ju.update(sql1);
						}
						box=new MessageBox(shell,SWT.ICON_INFORMATION);
						 box.setMessage("购票成功！！！");
						 box.setText("提示信息");
						 box.open();
						 OutTicketDialog otd = new OutTicketDialog(shell , SWT.NONE);
							otd.open(name, start_time, end_time, onshow_id , price , time , seat_1 , banlance ,money);
							shell.dispose();
					}
					
				}
			}
			
		});
		button_1.setText("\u7ED3\u7B97");
		button_1.setBounds(757, 603, 80, 27);
		
		Label label_22 = new Label(shell, SWT.NONE);
		label_22.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_22.setBounds(747, 501, 159, 17);
		label_22.setText("\u6BCF\u4EBA\u4E00\u6B21\u6700\u591A\u53EF\u4EE5\u8D2D\u4E94\u5F20\u7968\uFF01");
		
		Label label_23 = new Label(shell, SWT.NONE);
		label_23.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\src\\cn\\com\\shxt\\image\\blue.gif"));
		label_23.setBounds(517, 499, 25, 19);
		
		Label label_24 = new Label(shell, SWT.NONE);
		label_24.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_24.setBounds(605, 499, 106, 17);
		label_24.setText("\u4EE3  \u8868  \u7A7A  \u5EA7");
		
		
		
	
	}
}
