package cn.com.shxt.dialog;

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

public class AddShowRoomSeatDialog extends Dialog {

	private MessageBox box;
	private JdbcUtil ju=new JdbcUtil();
	private String type;
	private String row;
	private String name;
	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_2;
	private Text text_1;
	private int room_id;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddShowRoomSeatDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
		
	/**
	 * Open the dialog.
	 * @param row 
	 * @return the result
	 */
	public Object open(String name,String type, String row) {		
		this.name=name;
		this.type=type;
		this.row=row;
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.APPLICATION_MODAL);
		shell.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\image\\denglu.jpg"));
		shell.setSize(746, 494);
		shell.setText("\u653E\u6620\u5385\u5EA7\u4F4D\u6570");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblNewLabel.setBounds(27, 27, 73, 17);
		lblNewLabel.setText("\u653E\u6620\u5385\u540D\u79F0\uFF1A");
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		text.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		text.setBounds(106, 21, 73, 23);
		
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_1.setForeground(SWTResourceManager.getColor(0, 255, 0));
		label_1.setText("\u653E\u6620\u5385\u6392\u6570\uFF1A");
		label_1.setBounds(443, 27, 73, 17);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		text_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		text_1.setBounds(317, 24, 73, 23);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		text_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		text_2.setBounds(559, 24, 73, 23);

		int label_x = 27;
		int label_y = 77;
		
		int sp_x = 80;
		int sp_y = 71;
		for(int i = 1; i <= Integer.parseInt(row) ; i++){
			Label label_ = new Label(shell, SWT.NONE);
			label_.setBounds(label_x, label_y, 50, 17);
			label_.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
			label_.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			label_.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
			label_.setText("第"+i+"排:");
			
			Spinner spinner_ = new Spinner(shell, SWT.BORDER | SWT.NONE);
			spinner_.setMaximum(20);
			spinner_.setBounds(sp_x, sp_y, 73, 23);
			spinner_.setData(i);
			spinner_.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
			spinner_.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			spinner_.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
			spinner_.setMinimum(5);
			spinner_.setMaximum(20);
			spinner_.setSelection(8);
			if(i%3==0){
				label_x = 27;
				sp_x = 80;
				label_y += 35;
				sp_y += 35;
			}else{
				label_x += 230;
				sp_x += 230;
			}
		}
			
		/*显示已添加放映厅信息*/	

		text.setText(name);
		text_1.setText(type);
		text_2.setText(row);
	
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			/*每排座位数确定事件*/
			public void widgetSelected(SelectionEvent e) {
				 String sql="insert into showroom_info (showroom_name,showroom_type,showroom_row) values ('"+name+"','"+type+"',"+row+")";
			 	 ju.update(sql);
			 	 
				Control[] cons =  shell.getChildren();		
				for(Control con : cons){					
					 if(con instanceof Spinner){
						 int list = Integer.parseInt(((Spinner) con).getText());//列值
						 int row = Integer.parseInt(con.getData().toString());//行值
						 
						 String sql2="select showroom_id from showroom_info where showroom_name='"+name+"'";
						 room_id = Integer.parseInt(ju.query(sql2).get(0).get("showroom_id").toString());
						 String sql1="insert into showroomseat_info (showroomseat_row,showroomseat_list,showroom_id) values("+row+","+list+","+room_id+")";  
						 ju.update(sql1);
						 
					 }
				}
				String sql4="select sum(showroomseat_list) as num from showroomseat_info where showroom_id="+room_id+"";
				String sql3="update  showroom_info set showroom_num="+ju.query(sql4).get(0).get("num");
				if(ju.update(sql3)>0){
					box = new MessageBox(shell, SWT.ICON_INFORMATION);
					box.setText("提示信息");
					box.setMessage("影厅添加成功");
					box.open();   
				   shell.dispose();
				}else{
					box = new MessageBox(shell, SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("影厅添加失败");
					box.open(); 
				}
				
				
			}
		});
		btnNewButton.setBounds(478, 417, 113, 39);
		btnNewButton.setText("\u786E  \u8BA4");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		label.setText("\u653E\u6620\u5385\u7C7B\u578B\uFF1A");
		label.setBounds(215, 27, 73, 17);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\image\\310.jpg"));
		label_2.setBounds(0, 0, 740, 466);
		
		
		
	}
}