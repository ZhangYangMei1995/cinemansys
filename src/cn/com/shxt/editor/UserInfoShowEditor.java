package cn.com.shxt.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
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

import cn.com.shxt.dialog.LoginDialog;
import cn.com.shxt.dialog.ModifyUserInfoDialog;
import cn.com.shxt.util.JdbcUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UserInfoShowEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.UserInfoShowEditor"; //$NON-NLS-1$
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_6;
	private Text text_7;
    private JdbcUtil ju = new JdbcUtil();
    private MessageBox box ;
    private  List<Map<String,Object>>list ;
	public UserInfoShowEditor() {
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
		group.setText("\u7528\u6237\u4FE1\u606F");
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		group.setBounds(82, 112, 727, 465);
		
		Label label = new Label(group, SWT.BORDER);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label.setBounds(71, 44, 130, 162);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("\u7167\u7247");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_1.setFont(SWTResourceManager.getFont("ÐÂËÎÌå", 9, SWT.BOLD));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(148, 212, 24, 17);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u59D3       \u540D\uFF1A");
		label_2.setForeground(SWTResourceManager.getColor(0, 128, 0));
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setBounds(365, 44, 72, 20);
		
		text = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(454, 44, 94, 23);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("\u5BC6        \u7801\uFF1A");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_3.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setBounds(365, 202, 72, 17);
		
		text_1 = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.PASSWORD);
		text_1.setBounds(454, 199, 94, 23);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("\u6027      \u522B\uFF1A");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_4.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setBounds(365, 100, 61, 17);
		
		final Button button = new Button(group, SWT.RADIO);
		button.setText("\u7537");
		button.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button.setBounds(454, 100, 33, 17);
		
		final Button button_1 = new Button(group, SWT.RADIO);
		button_1.setText("\u5973");
		button_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		button_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		button_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_1.setBounds(507, 100, 33, 17);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("\u89D2      \u8272\uFF1A");
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_5.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_5.setBounds(365, 315, 61, 17);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("\u90AE      \u7BB1\uFF1A");
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_6.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_6.setBounds(365, 254, 61, 17);
		
		text_2 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(454, 251, 94, 23);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u51FA\u751F\u65E5\u671F\uFF1A");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_7.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_7.setBounds(365, 149, 61, 17);
		
		text_3 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_3.setBounds(454, 149, 94, 23);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("\u7B49       \u7EA7\uFF1A");
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_8.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_8.setBounds(365, 378, 61, 17);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setText(" \u8054\u7CFB\u65B9\u5F0F\uFF1A");
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_9.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 9, SWT.BOLD));
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_9.setBounds(359, 435, 61, 17);
		
		text_4 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_4.setBounds(453, 435, 94, 23);
		
		text_6 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_6.setBounds(454, 315, 94, 23);
		
		text_7 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_7.setBounds(454, 378, 94, 23);
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			/*ÐÞ¸Ä*/
			public void widgetSelected(SelectionEvent e) {
				int client_id = Integer.parseInt(list.get(0).get("client_id").toString());
				ModifyUserInfoDialog uuid = new ModifyUserInfoDialog(container.getShell(), SWT.NONE);
				uuid.open(client_id);
				//±¾½çÃæÖØÐÂ¸³Öµ
				String sql = "select * from client_info where client_id = "+client_id+"";
				List<Map<String,Object>> list = ju.query(sql);
				text.setText(list.get(0).get("client_name").toString());
				text_3.setText(list.get(0).get("client_age").toString());
				text_1.setText(list.get(0).get("client_password").toString());
				text_2.setText(list.get(0).get("client_mail").toString());
				text_4.setText(list.get(0).get("client_telephone").toString());
				String str = "";
				if(list.get(0).get("client_sex").toString().equals("ÄÐ")){
					button.setSelection(true);
				}else{
					button_1.setSelection(true);
				}
			}
		});
		button_2.setBounds(608, 435, 80, 27);
		button_2.setText("\u4FEE\u6539");

		String sql = "select * from client_info where client_name = "+LoginDialog.userName+"";
		 list = ju.query(sql);
		if(list.size()==0){
			box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
			box.setText("ÌáÊ¾ÐÅÏ¢");
			box.setMessage("¸ÃÓÃ»§Òì³£!");
			box.open();		
		}else {
			text.setText(LoginDialog.userName);
			text_3.setText(list.get(0).get("client_age").toString());
			text_1.setText(list.get(0).get("client_password").toString());
			text_2.setText(list.get(0).get("client_mail").toString());
			text_6.setText(list.get(0).get("client_role").toString());
			text_7.setText(list.get(0).get("client_grade").toString());
			text_4.setText(list.get(0).get("client_telephone").toString());
			String str = "";
			if(list.get(0).get("client_sex").toString().equals("ÄÐ")){
				button.setSelection(true);
			}else{
				button_1.setSelection(true);
			}
			label.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\"+list.get(0).get("client_image").toString()));
		}
		
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
		return "UserInfoShowEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "UserInfoShowEditor";
	}
}
