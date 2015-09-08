package cn.com.shxt.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import cn.com.shxt.dialog.UpdateUserInfoDialog;
import cn.com.shxt.util.JdbcUtil;
import org.eclipse.wb.swt.SWTResourceManager;

public class QueryUserManagerInfoEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.QueryUserManagerInfoEditor"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private JdbcUtil ju=new JdbcUtil();
	private MessageBox box;
	private int page = 1; // 当前页书
	private int pageSize = 6; // 每页显示条数
	private String sql;
	public QueryUserManagerInfoEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		final Combo combo = new Combo(container, SWT.READ_ONLY);
		combo.setItems(new String[] {"     \u5168\u90E8", "     \u59D3\u540D", "     \u89D2\u8272"});
		combo.setBounds(134, 38, 88, 25);
		combo.select(0);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/*搜索*/
			public void widgetSelected(SelectionEvent e) {
				String condition = combo.getText().trim();
				if(condition.equals("全部")){
					sql="select * from client_info limit "+(page-1)*pageSize+","+pageSize+"";
					updTable(sql);
				}else if(condition.equals("姓名")){
					if(text.getText().equals("")){
						box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请填写姓名");
						 box.setText("提示信息");
						 box.open();
					}else{
						sql = "select client_name,client_sex,client_age,client_role,client_mail,client_telephone from client_info " +
								"where client_name='"+text.getText().trim()+"'";
						updTable(sql);				
					}
				}else if(condition.equals("角色")){
					if(text.getText().equals("")){
						box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("请填写角色");
						 box.setText("提示信息");
						 box.open();
					}else{
						sql = "select client_name,client_sex,client_age,client_role,client_mail,client_telephone from client_info " +
							  "where client_role='"+text.getText().trim()+"'";
						updTable(sql);		
					}
														
				}
			}
		});
		button.setText("\u641C\u7D22");
		button.setBounds(645, 36, 80, 27);
		
		text = new Text(container, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text.setBounds(356, 38, 115, 23);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(131, 141, 721, 161);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(118);
		tableColumn.setText("      \u59D3\u540D");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(116);
		tableColumn_1.setText("      \u6027\u522B");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(126);
		tableColumn_2.setText("      \u51FA\u751F\u65E5\u671F");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(112);
		tableColumn_3.setText("      \u89D2\u8272");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(120);
		tableColumn_4.setText("      \u90AE\u7BB1");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			/*删除*/
			public void widgetSelected(SelectionEvent e) {
				TableItem [] items=table.getSelection();
				for(TableItem item:items){
					ju.update("delete from client_info where client_name='"+item.getText(0)+"'");
					/*刷新表格*/
					sql = "select * from client_info limit "+(page-1)*pageSize+","+pageSize;
					updTable(sql);
				}			
			}
			
		});
		menuItem.setText("\u5220\u9664");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			/*修改*/
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = table.getSelection();
				for (TableItem item : items) {
					List<Map<String,Object>>list1=ju.query("select client_id from client_info where client_name='"+item.getText(0)+"'");					
					UpdateUserInfoDialog ud = new UpdateUserInfoDialog(container.getShell(), SWT.NONE);
					ud.open(list1.get(0).get("client_id").toString());// 获得当前行的第一列的值
					/* 刷新表格 */
					if(combo.getText().trim().equals("全部")){
						sql="select * from client_info limit 0,4";
						updTable(sql);	
					}else if(combo.getText().trim().equals("姓名")){
						sql = "select client_name,client_sex,client_age,client_role,client_mail,client_telephone from client_info  where client_name='"+text.getText().trim()+"'";
						updTable(sql);
					}else if(combo.getText().equals("角色")){
						sql = "select client_name,client_sex,client_age,client_role,client_mail,client_telephone from client_info  where client_role='"+text.getText().trim()+"' limit 0,4";
						updTable(sql);										
					}
				}
			}
		});
		menuItem_1.setText("\u4FEE\u6539");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(120);
		tableColumn_5.setText("     \u8054\u7CFB\u65B9\u5F0F");
		
		Link link = new Link(container, SWT.NONE);
		link.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		link.addSelectionListener(new SelectionAdapter() {
			/*首页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				String condition = combo.getText().trim();
				if(condition.equals("全部")){
					if(page>1){
						page=1;
						sql="select * from client_info limit "+(page-1)*pageSize+","+pageSize+"";
						updTable(sql);
					}else{
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("已经是第一页！");
						box.open();
					}
				}else if(condition.equals("角色")){
					if(text.getText().equals("")){
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("请填写角色！");
						box.open();
					}else {
						if(page>1){
							page=1;
							sql="select * from client_info where client_role='"+text.getText().trim()+"' limit "+(page-1)*pageSize+","+pageSize+"";
							updTable(sql);
						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是第一页！");
							box.open();
						}
					}
				}
			}
		});
		link.setBounds(146, 342, 24, 17);
		link.setText("<a>\u9996\u9875</a>");
		
		Link link_1 = new Link(container, SWT.NONE);
		link_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		link_1.addSelectionListener(new SelectionAdapter() {
			/*上一页*/
			public void widgetSelected(SelectionEvent e) {
				String condition = combo.getText().trim();
				if(condition.equals("全部")){
					if ( page > 1 ){
						page --;
						sql = "select * from client_info limit "+(page-1)*pageSize+","+pageSize+" ";
						updTable(sql);
					}else{
						box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("已经是首页！");
						box.open();
					}
				}else if(condition.equals("角色")){
					if(text.getText().equals("")){
						box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("请填写角色！");
						box.open();
					}else {
						if ( page > 1 ){
							page --;
							sql = "select * from client_info where client_role='"+text.getText().trim()+"' limit "+(page-1)*pageSize+","+pageSize+"";
							updTable(sql);
						}else{
							box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是首页！");
							box.open();
						}
					}
				}			
			}
		});
		link_1.setBounds(318, 342, 36, 17);
		link_1.setText("<a>\u4E0A\u4E00\u9875</a>");
		
		Link link_2 = new Link(container, SWT.NONE);
		link_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		link_2.addSelectionListener(new SelectionAdapter() {
			/*下一页*/
			public void widgetSelected(SelectionEvent e) {
				String condition = combo.getText().trim();
				if(condition.equals("全部")){
					sql = "select count(*) as totalCount from client_info ";
					int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
					if(totalCount%pageSize==0){
						if(page<totalCount/pageSize){
							page ++;
							sql = "select * from client_info limit "+(page-1)*pageSize+","+pageSize+"";
							updTable(sql);
						}else{
							box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是尾页！");
							box.open();
						}
					}else {
						if(page<totalCount/pageSize+1){
							page ++;
							sql = "select * from client_info limit "+(page-1)*pageSize+","+pageSize+"";
							updTable(sql);
						}else {
							box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是尾页！");
							box.open();
						}
					}
				}else if(condition.equals("角色")){
					if(text.getText().equals("")){
						box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("请添写角色！");
						box.open();
					}else {
						sql = "select count(*) as totalCount from client_info where client_role='"+text.getText().trim()+"'";
						int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
						if(totalCount%pageSize==0){
							if(page<totalCount/pageSize){
								page ++;
								sql = "select * from client_info where client_role='"+text.getText().trim()+"'" +
										" limit "+(page-1)*pageSize+","+pageSize+"";
								updTable(sql);
							}else{
								box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是尾页！");
								box.open();
							}
						}else {
							if(page<totalCount/pageSize+1){
								page ++;
								sql = "select * from client_info where client_role='"+text.getText().trim()+"' limit "+(page-1)*pageSize+","+pageSize+"";
								updTable(sql);
							}else {
								box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是尾页！");
								box.open();
							}
						}
					
					}
				}			
			}
			
		});
		link_2.setBounds(536, 342, 36, 17);
		link_2.setText("<a>\u4E0B\u4E00\u9875</a>");
		
		Link link_3 = new Link(container, SWT.NONE);
		link_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		link_3.addSelectionListener(new SelectionAdapter() {
			/*尾页*/
			public void widgetSelected(SelectionEvent e) {
				String condition = combo.getText().trim();
				if(condition.equals("全部")){
					sql = "select count(*) as totalCount from client_info ";
					int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
					if(totalCount%pageSize==0){
						if(page<totalCount/pageSize){
							page ++;
							sql = "select * from client_info limit "+(page-1)*pageSize+","+pageSize+"";
							updTable(sql);
						}else{
							box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是尾页！");
							box.open();
						}
					}else {
						if(page<totalCount/pageSize+1){
							page ++;
							sql = "select * from client_info limit "+(page-1)*pageSize+","+pageSize+"";
							updTable(sql);
						}else {
							box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是尾页！");
							box.open();
						}
					}
				}else if(condition.equals("角色")){
					if(text.getText().equals("")){
						box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("请添写角色！");
						box.open();
					}else {
						sql = "select count(*) as totalCount from client_info where client_role='"+text.getText().trim()+"'";
						int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
						if(totalCount%pageSize==0){
							if(page<totalCount/pageSize){
								page = totalCount/pageSize;
								sql = "select * from client_info where client_role='"+text.getText().trim()+"'" +
										" limit "+(page-1)*pageSize+","+pageSize+"";
								updTable(sql);
							}else{
								box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是尾页！");
								box.open();
							}
						}else {
							if(page<totalCount/pageSize+1){
								page = totalCount/pageSize+1;
								sql = "select * from client_info where client_role='"+text.getText().trim()+"' limit "+(page-1)*pageSize+","+pageSize+"";
								updTable(sql);
							}else {
								box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是尾页！");
								box.open();
							}
						}
					
					}
				}			
			}
		});
		link_3.setBounds(737, 342, 24, 17);
		link_3.setText("<a>\u5C3E\u9875</a>");

	}
	public TableItem updTable(String sql){
		this.sql = sql;
		table.removeAll();
		TableItem item = null;
		List<Map<String,Object>> list = ju.query(sql);
		for (int i = 0; i < list.size(); i++) {
			item = new TableItem(table, SWT.NONE);
			item.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
			item.setText(new String[] {
					list.get(i).get("client_name").toString(),
					list.get(i).get("client_sex").toString(),
					list.get(i).get("client_age").toString(),
					list.get(i).get("client_role").toString(),
					list.get(i).get("client_mail").toString(),
					list.get(i).get("client_telephone").toString()});
		}	
		return item;
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
		return "QueryUserManagerInfoEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "QueryUserManagerInfoEditor";
	}
}
