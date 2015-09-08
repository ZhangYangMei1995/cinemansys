package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.util.JdbcUtil;

public class UpdateShowRoomDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private String name1;
	private String type1;
	private int row1;
	private int id1;
	private MessageBox box;
	private JdbcUtil ju=new JdbcUtil();
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdateShowRoomDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String name1,String type1,int row1,int id1) {
		this.name1=name1;
		this.type1=type1;
		this.row1=row1;
		this.id1=id1;
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
		shell.setText("\u5F71\u5385\u4FE1\u606F");
		shell.setBounds(500, 50, 520, 446);
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("\u5F71\u5385\u4FE1\u606F\u6DFB\u52A0");
		group.setBounds(34, 41, 470, 377);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("\u540D       \u79F0\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(31, 61, 79, 17);
		
		text = new Text(group, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text.setBounds(138, 61, 125, 23);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("\u7C7B        \u578B\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(31, 147, 79, 17);
		
		final Button button = new Button(group, SWT.RADIO);
		button.setText("3D\u5F71\u5385");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button.setBounds(138, 186, 97, 38);
		
		final Button button_1 = new Button(group, SWT.RADIO);
		button_1.setText("IMAX\u5F71\u5385");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button_1.setBounds(138, 246, 97, 17);
		
		final Button button_2 = new Button(group, SWT.RADIO);
		button_2.setText("\u666E\u901A\u5F71\u5385");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button_2.setBounds(138, 147, 97, 17);
		
		final Spinner spinner = new Spinner(group, SWT.BORDER);
		spinner.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		spinner.setMaximum(20);
		spinner.setMinimum(5);
		spinner.setSelection(row1);
		spinner.setBounds(138, 309, 79, 23);
		
		Button button_3 = new Button(group, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			/*座位浏览*/
			public void widgetSelected(SelectionEvent e) {

				String name1=text.getText().trim();
				String type1="";
				if(button.getSelection()==true){
					type1="3D影厅";
				}else if(button_1.getSelection()==true){
					type1="IMAX影厅";
				}else if(button_2.getSelection()){
					type1="普通影厅";
				}
				int row1=Integer.parseInt(spinner.getText());
				
				if(type1.equals("")){
					box = new MessageBox(shell, SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("请选择影厅类型");
					box.open();
				}else{
					UpdateShowRoomSeatDialog asrd=new UpdateShowRoomSeatDialog(shell,SWT.NONE);										
				    asrd.open(name1,type1,row1,id1);//不在往下执行，进入dialog，直到shell.dispose();
					shell.dispose();
					
				}				
			
			}
		});
		button_3.setText("\u5EA7\u4F4D\r\n\u6D4F\u89C8");
		button_3.setBounds(276, 305, 97, 31);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u6392       \u6570\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(27, 309, 83, 23);		
		
		text.setText(name1);
		if(type1.equals("普通影厅")){
			button_2.setSelection(true);
		}else if(type1.equals("3D影厅")){
			button.setSelection(true);
		}else if(type1.equals("IMAX影厅")){
			button_1.setSelection(true);
		}
	
	}

}
