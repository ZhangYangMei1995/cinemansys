package cn.com.shxt.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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

import cn.com.shxt.dialog.AddShowRoomSeatDialog;
import cn.com.shxt.util.JdbcUtil;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Spinner;

public class AddShowRoomEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.AddShowRoomEditor"; //$NON-NLS-1$
	private Text text;
	private JdbcUtil ju=new JdbcUtil();
	private MessageBox box;
	public AddShowRoomEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		
		Group group = new Group(container, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		group.setVisible(true);
		group.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		group.setBackgroundImage(null);
		group.setBackgroundMode(SWT.INHERIT_DEFAULT);
		group.setText("\u5F71\u5385\u6DFB\u52A0");
		group.setBounds(249, 112, 496, 377);
		
		Label label = new Label(group, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(31, 61, 79, 17);
		label.setText("\u540D       \u79F0\uFF1A");
		
		text = new Text(group, SWT.BORDER);
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		text.setBounds(138, 61, 125, 23);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label_1.setBounds(31, 147, 79, 17);
		label_1.setText("\u7C7B        \u578B\uFF1A");
		
		final Button btnd = new Button(group, SWT.RADIO);
		btnd.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnd.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		btnd.setBounds(138, 199, 65, 19);
		btnd.setText("3D\u5F71\u5385");
		
		final Button btnImax = new Button(group, SWT.RADIO);
		btnImax.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		btnImax.setBounds(138, 246, 83, 19);
		btnImax.setText("IMAX\u5F71\u5385");
		
		final Button button = new Button(group, SWT.RADIO);
		button.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		button.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button.setBounds(138, 147, 73, 19);
		button.setText("\u666E\u901A\u5F71\u5385");
				
		final Spinner spinner = new Spinner(group, SWT.BORDER);
		spinner.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		spinner.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		spinner.setMaximum(20);
		spinner.setMinimum(5);
		spinner.setSelection(8);
		spinner.setBounds(138, 309, 79, 23);
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		button_2.addSelectionListener(new SelectionAdapter() {
			/*座位添加*/
			public void widgetSelected(SelectionEvent e) {
				String name=text.getText().trim();
				String type="";
				if(btnd.getSelection()==true){
					type="3D影厅";
				}else if(btnImax.getSelection()==true){
					type="IMAX影厅";
				}else if(button.getSelection()){
					type="普通影厅";
				}
				String row=spinner.getText().trim();
				
				if(name.equals("")){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("请添加影厅名称");
					box.open();
				}else if(type.equals("")){
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("请选择影厅类型");
					box.open();
				}else{
					String sql="select * from showroom_info where showroom_name='"+name+"'";
					List<Map<String,Object>>list=ju.query(sql);				
					if(list.size()==1){
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("影厅名称重复，请重新添加");
						box.open();
						text.setText("");
						text.setFocus();
					}else{
											   
					    AddShowRoomSeatDialog asrd=new AddShowRoomSeatDialog(container.getShell(),SWT.NONE);				
						
					    asrd.open(name,type,row);//不在往下执行，进入dialog，直到shell.dispose();
						
					    text.setText("");
						spinner.setSelection(8);
						btnd.setSelection(false);
						btnImax.setSelection(false);
						button.setSelection(false);
						
					}				
				}				
			}
		});
		button_2.setBounds(276, 305, 97, 31);
		button_2.setText("\u5EA7\u4F4D\r\n\u6DFB\u52A0");
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(27, 309, 83, 23);
		label_2.setText("\u6392       \u6570\uFF1A");
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setImage(null);
		label_3.setBounds(0, 0, 1164, 713);
		
		

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
		return "AddShowRoomEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "AddShowRoomEditor";
	}
}
