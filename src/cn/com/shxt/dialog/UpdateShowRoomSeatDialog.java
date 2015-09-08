package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import cn.com.shxt.util.JdbcUtil;
import org.eclipse.wb.swt.SWTResourceManager;


public class UpdateShowRoomSeatDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private String name2;
	private String type2;
	private int row2;
	private int id2;
	private Text text;
	private Text text_1;
	private Text text_2;
	private MessageBox box;
	private JdbcUtil ju=new JdbcUtil();
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdateShowRoomSeatDialog(Shell parent, int style) {
		super(parent, style);
		setText("座位浏览");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String name2,String type2,int row2,int id2) {
		this.name2=name2;
		this.type2=type2;
		this.row2=row2;
		this.id2=id2;
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
		shell.setBounds(350,200, 791, 507);
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u653E\u6620\u5385\u540D\u79F0\uFF1A");
		label.setBounds(47, 40, 73, 17);
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text.setText("<dynamic>");
		text.setBounds(126, 34, 73, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u653E\u6620\u5385\u7C7B\u578B\uFF1A");
		label_1.setBounds(235, 40, 73, 17);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_1.setText("<dynamic>");
		text_1.setBounds(337, 37, 73, 23);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u653E\u6620\u5385\u6392\u6570\uFF1A");
		label_2.setBounds(463, 40, 73, 17);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_2.setText("<dynamic>");
		text_2.setBounds(579, 37, 73, 23);
		
		/*显示放映厅信息*/	
		text.setText(name2);
		text_1.setText(type2);
		text_2.setText(String.valueOf(row2));
		
		int label_x = 27;
		int label_y = 77;
		
		int sp_x = 80;
		int sp_y = 71;
		
		String sql="select * from showroom_info  where showroom_name='"+name2+"' and showroom_type='"+type2+"' and showroom_row="+row2;
	 	List<Map<String,Object>>list=ju.query(sql);
	 	
	 	System.out.println(ju.query(sql).size());
		if(list.size()==0){//放映厅信息修改了，座位表根据排数的新值自动重新显示			
			for(int i = 1; i <=row2 ; i++){
				Label label_ = new Label(shell, SWT.NONE);
				label_.setBounds(label_x, label_y, 50, 17);
				label_.setText("第"+i+"排:");
				
				Spinner spinner_ = new Spinner(shell, SWT.BORDER | SWT.NONE);
				spinner_.setMaximum(20);
				spinner_.setBounds(sp_x, sp_y, 73, 23);
				spinner_.setData(i);
				spinner_.setMinimum(5);
				spinner_.setMaximum(20);
				spinner_.setSelection(8);
				if(i%3==0){
					label_x = 27;
					sp_x = 80;
					label_y += 35;
					sp_y += 35;
				}else{
					label_x += 150;
					sp_x += 150;
				}
			}
		}else if(list.size()==1){//放映厅名称类型和排数都没改，座位显示原值			
			for(int i = 1; i <=row2 ; i++){				
				String sql2="select showroomseat_list from showroomseat_info where showroom_id="+id2;
				List<Map<String,Object>>list2=ju.query(sql2);
				int j=(Integer) list2.get(i-1).get("showroomseat_list");
				
				Label label_ = new Label(shell, SWT.NONE);
				label_.setBounds(label_x, label_y, 50, 17);
				label_.setText("第"+i+"排:");
				
				Spinner spinner_ = new Spinner(shell, SWT.BORDER | SWT.NONE);
				spinner_.setMaximum(20);
				spinner_.setBounds(sp_x, sp_y, 73, 23);
				spinner_.setData(i);
				spinner_.setMinimum(5);
				spinner_.setMaximum(20);
				spinner_.setSelection(j);
				if(i%3==0){
					label_x = 27;
					sp_x = 80;
					label_y += 35;
					sp_y += 35;
				}else{
					label_x += 150;
					sp_x += 150;
				}
			}
		}		
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/* 确认修改*/
			public void widgetSelected(SelectionEvent e) {
				
				 String s="delete from showroomseat_info where showroom_id="+id2;
				 ju.update(s);
				Control[] cons =  shell.getChildren();		
				for(Control con : cons){					
					 if(con instanceof Spinner){						 
						
						 int list = Integer.parseInt(((Spinner) con).getText());//列值
						 int row_place = Integer.parseInt(con.getData().toString());//行值 1 2 3 4 5 6 7 8
						 
						 String sql1="insert into showroomseat_info (showroomseat_row,showroomseat_list,showroom_id) values("+row_place+","+list+","+id2+")";  
						 ju.update(sql1);				 
					 }
				}
				String sql4="select sum(showroomseat_list) as num from showroomseat_info where showroom_id="+id2+"";
				String sql3="update  showroom_info set showroom_name='"+name2+"',showroom_num="+ju.query(sql4).get(0).get("num")+",showroom_type='"+type2+"',showroom_row="+row2+" where showroom_id="+id2;
				ju.update(sql3);
				/*if(ju.update(sql3)>0){
					box = new MessageBox(shell, SWT.ICON_INFORMATION);
					box.setText("提示信息");
					box.setMessage("影厅更新成功");
					box.open();   
				   shell.dispose();
				}else{
					box = new MessageBox(shell, SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("影厅更新失败");
					box.open(); 
				}*/	
				box = new MessageBox(shell, SWT.ICON_INFORMATION);
				box.setText("提示信息");
				box.setMessage("影厅更新成功");
				box.open();   
			   shell.dispose();
			}
		});
		button.setText("\u786E  \u8BA4");
		button.setBounds(498, 430, 113, 39);

	}

}
