package cn.com.shxt.editor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.dialog.UpdateFilmInfoDialog;
import cn.com.shxt.util.JdbcUtil;

public class QueryFilmInfoEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.QueryFilmInfoEditor"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private Text text_1;
	private JdbcUtil ju=new JdbcUtil();
	private MessageBox box;
	private int page = 1; // 当前页书
	private int pageSize = 6; // 每页显示条数
	private String sql;
	public QueryFilmInfoEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		composite.setBounds(0, 0, 1073, 712);
		
		final Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		combo.setFont(SWTResourceManager.getFont("Vrinda", 9, SWT.BOLD | SWT.ITALIC));
		combo.setItems(new String[] {"    \u5168\u90E8", "    \u540D\u79F0", "    \u5BFC\u6F14 ", "    \u56FD\u5BB6", "    \u8BED\u8A00"});
		combo.setBounds(62, 46, 88, 25);
		combo.select(0);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(238, 45, 102, 23);
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setBounds(32, 434, 24, 45);
		lblNewLabel_1.setText("\u4ECB\r\n\u7ECD");
		final Label lblNewLabel = new Label(composite, SWT.BORDER);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setBounds(768, 10, 153, 182);

		text_1 = new Text(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_1.setBounds(62, 431, 859, 142);
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/*搜索 */
			public void widgetSelected(SelectionEvent e) {									
					lblNewLabel.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\2013010910482.gif"));
					text_1.setText("");					
					String condition=combo.getText().trim();
					if(condition.equals("全部")){
						sql = "select * from film_info limit 0,6";
						updTable(sql);
					}else if(condition.equals("名称")){
						if(text.getText().trim().equals("")){						
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("请添加名称");
							box.open();
						}else{
							sql = "select * from film_info where film_name='"+text.getText().trim()+"' limit 0,6";
							updTable(sql);
							List<Map<String,Object>> list1 = ju.query(sql);
							if(list1.size()==1){
								String image=list1.get(0).get("film_image").toString();
								lblNewLabel.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\"+image));
								text_1.setText(list1.get(0).get("film_introduction").toString());
							}						
						}
					}else if(condition.equals("导演")){
						if(text.getText().trim().equals("")){
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("请添加导演名称");
							box.open();
						}else{
							String sql = "select * from film_info where film_director='"+text.getText().trim()+"' limit 0,4";
							updTable(sql);
							List<Map<String,Object>> list1 = ju.query(sql);
								if(list1.size()==1){
									String image=list1.get(0).get("film_image").toString();
									lblNewLabel.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\"+image));
									text_1.setText(list1.get(0).get("film_introduction").toString());
								}								
							}
						}else if(condition.equals("国家")){
						  if(text.getText().trim().equals("")){
							 box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							 box.setText("提示信息");
							 box.setMessage("请添加国家名称");
							 box.open();
						  }else{
							 String sql = "select * from film_info where film_country='"+text.getText().trim()+"' limit 0,4";
							 updTable(sql);
								List<Map<String,Object>> list1 = ju.query(sql);
									if(list1.size()==1){
										String image=list1.get(0).get("film_image").toString();
										lblNewLabel.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\"+image));
										text_1.setText(list1.get(0).get("film_introduction").toString());
									}								
								}
						}else if(condition.equals("语言")){
							if(text.getText().trim().equals("")){
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("请添加语言");
								box.open();
							}else{
								sql = "select * from film_info where film_language = '" + text.getText().trim()+"' limit 0,4";
								updTable(sql);
								List<Map<String,Object>> list1 = ju.query(sql);
									if(list1.size()==1){
										String image=list1.get(0).get("film_image").toString();
										lblNewLabel.setImage(SWTResourceManager.getImage("E:\\javaworkplace\\cinemamanagerment.system\\icons\\"+image));
										text_1.setText(list1.get(0).get("film_introduction").toString());
									}								
								}																
							}
						}													
		});
		button.setFont(SWTResourceManager.getFont("Vani", 9, SWT.BOLD | SWT.ITALIC));
		button.setBounds(430, 46, 80, 27);
		button.setText("\u641C\u7D22");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		table.setBounds(62, 197, 859, 169);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(112);
		tableColumn.setText("         \u540D\u79F0");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(87);
		tblclmnNewColumn.setText("     \u56FD\u5BB6");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(91);
		tableColumn_1.setText("     \u5BFC\u6F14");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(115);
		tableColumn_2.setText("        \u4E3B\u6F14");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(82);
		tableColumn_3.setText("     \u65F6\u957F");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(71);
		tableColumn_4.setText("     \u8BED\u8A00");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(96);
		tblclmnNewColumn_1.setText("         \u7C7B\u578B");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			/*删除*/
			public void widgetSelected(SelectionEvent e) {
				TableItem [] items=table.getSelection();		
				for(TableItem item:items){
					//String name=item.getText(0).toString();
					String sq="select film_id from film_info where film_name='"+item.getText(0)+"'";					
					String sql="select onshow_date from onshow_info where osfilm_id="+ju.query(sq).get(0).get("film_id")+"";					
					List<Map<String,Object>>list=ju.query(sql);
					int count=0;
					if(list.size()!=0){						
						Date date=new Date();						
						for(int i=0;i<list.size();i++){							
							try{
								//System.out.println(00);
								Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).get("onshow_date").toString()); 			
								while(date.getTime()<date1.getTime()){
									count++;//该电影将要放映的次数
									box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
									 box.setMessage("该电影正准备上映，请勿删除");
									 box.setText("提示信息");
									 box.open();
									 break;
								}
							}catch (Exception e1){
								e1.printStackTrace();
							}
							if(count!=list.size()){
								ju.update("delete from film_info where film_name='"+item.getText(0)+"'");
								/*刷新表格*/
								table.removeAll();						
								String sql1 = "select * from film_info limit "+(page-1)*pageSize+","+pageSize;
								List<Map<String,Object>> list1 = ju.query(sql1);								
								for (int j = 0; j < list1.size(); j++) {
									TableItem item1 = new TableItem(table, SWT.NONE);
									item1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
									item1.setText(new String[] {
											list1.get(i).get("film_name").toString(),								
											list1.get(i).get("film_country").toString(),
											list1.get(i).get("film_director").toString(),
											list1.get(i).get("film_protagonist").toString(),
											list1.get(i).get("film_duration").toString(),
											list1.get(i).get("film_language").toString(),
											list1.get(i).get("film_type").toString(),
											list1.get(i).get("film_update").toString(),
											list1.get(i).get("film_downdate").toString()});
								}
							}
						}	
						
					}else{
						ju.update("delete from film_info where film_name='"+item.getText(0)+"'");
						/*刷新表格*/
						table.removeAll();						
						String sql1 = "select * from film_info limit "+(page-1)*pageSize+","+pageSize;
						List<Map<String,Object>> list1 = ju.query(sql1);
						
						for (int i = 0; i < list1.size(); i++) {
							TableItem item1 = new TableItem(table, SWT.NONE);
							item1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
							item1.setText(new String[] {
									list1.get(i).get("film_name").toString(),								
									list1.get(i).get("film_country").toString(),
									list1.get(i).get("film_director").toString(),
									list1.get(i).get("film_protagonist").toString(),
									list1.get(i).get("film_duration").toString(),
									list1.get(i).get("film_language").toString(),
									list1.get(i).get("film_type").toString(),
									list1.get(i).get("film_update").toString(),
									list1.get(i).get("film_downdate").toString()});
						}	
					}				
							
				}
			}
		});
		menuItem.setText("\u5220\u9664");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			/*修改*/
			public void widgetSelected(SelectionEvent e) {
				TableItem [] items=table.getSelection();				
				for(TableItem item:items){
					UpdateFilmInfoDialog ubd=new UpdateFilmInfoDialog(container.getShell(),SWT.NONE);
					//获得当前行的第一列的值			
					String sql="select film_id from film_info where film_name='"+item.getText(0)+"'";
					List<Map<String,Object>>list=ju.query(sql);
					ubd.open(list.get(0).get("film_id").toString());//不在往下执行，进入dialog，直到shell.dispose();
					/*刷新表格*/
					table.removeAll();						
					String sql1 = "select * from film_info limit "+(page-1)*pageSize+","+pageSize;
					List<Map<String,Object>> list1 = ju.query(sql1);					
					for (int i = 0; i < list1.size(); i++) {
						TableItem item1 = new TableItem(table, SWT.NONE);
						item1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
						item1.setText(new String[] {
								
								list1.get(i).get("film_name").toString(),								
								list1.get(i).get("film_country").toString(),
								list1.get(i).get("film_director").toString(),
								list1.get(i).get("film_protagonist").toString(),
								list1.get(i).get("film_duration").toString(),
								list1.get(i).get("film_language").toString(),
								list1.get(i).get("film_type").toString(),
								list1.get(i).get("film_update").toString(),
								list1.get(i).get("film_downdate").toString()});
					}					    
				}
			}
		});
		menuItem_1.setText("\u4FEE\u6539");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("    \u4E0A\u7EBF\u65F6\u95F4");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("    \u4E0B\u7EBF\u65F6\u95F4");
		
		Label label = new Label(composite, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(927, 157, 47, 45);
		label.setText("\u56FE\r\n\u7247");
		Link link = new Link(composite, SWT.NONE);
		link.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		link.addSelectionListener(new SelectionAdapter() {
			/*单击首页信息*/
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("全部")){
					if (page > 1) {
						page = 1;
						sql = "select * from film_info limit " + (page - 1)* pageSize + "," + pageSize;
						updTable(sql);
					} else {
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("已经是第一页");
						box.open();
					}					
				}else if(combo.getText().trim().equals("国家")){
					if(!text.getText().trim().equals("")){
						if (page > 1) {
							page = 1;
							sql = "select * from film_info  where film_country='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize ;
							updTable(sql);
						} else {
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是第一页");
							box.open();
						}										
					}else{
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("请添加国家");
						box.open();
					}
				}else if(combo.getText().trim().equals("语言")){
					if(!text.getText().trim().equals("")){
		 				   if (page > 1) {
								page = 1;
								sql = "select * from film_info  where film_language='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize + "";
								System.out.println(sql);
								updTable(sql);
							} else {
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是第一页");
								box.open();
							}																	
						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("请添加语言");
							box.open();
						}
				}			
			}
		});
		link.setBounds(73, 372, 24, 17);
		link.setText("<a>\u9996\u9875</a>");
		
		Link link_1 = new Link(composite, SWT.NONE);
		link_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		link_1.addSelectionListener(new SelectionAdapter() {
			/*上一页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("全部")){
					if (page > 1) {
						page --;
						sql = "select * from film_info limit " + (page - 1)* pageSize + "," + pageSize;
						updTable(sql);
					} else {
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("已经是第一页");
						box.open();
					}					
				}else if(combo.getText().trim().equals("国家")){
					if(!text.getText().trim().equals("")){
						if (page > 1) {
							page --;
							sql = "select * from film_info  where film_country='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize + "";
							updTable(sql);
						} else {
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是第一页");
							box.open();
						}										
					}else{
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("请添加国家");
						box.open();
					}
				}else if(combo.getText().trim().equals("语言")){
					if(!text.getText().trim().equals("")){
						if (page > 1) {
							page --;
							sql = "select * from film_info limit " + (page - 1)* pageSize + "," + pageSize;
							updTable(sql);
						} else {
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是第一页");
							box.open();
						}					
					}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("请添加语言");
							box.open();
						}
				}			
			}
		});
		link_1.setBounds(205, 372, 36, 17);
		link_1.setText("<a>\u4E0A\u4E00\u9875</a>");
		
		Link link_2 = new Link(composite, SWT.NONE);
		link_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		link_2.addSelectionListener(new SelectionAdapter() {
			/*下一页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("全部")){
					sql = "select count(*) as totalCount from film_info";
					int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
					if((totalCount%pageSize) == 0){
						if(page < totalCount/pageSize){//如果当前页码小于最后一页的页码
							page ++;
							sql = "select * from film_info limit " + (page - 1)* pageSize + "," + pageSize;
					        updTable(sql);					
						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是最后一页");
							box.open();
						}
					}else{
						if(page < totalCount/pageSize+1){
							 page ++;
							 sql = "select * from film_info limit " + (page - 1)* pageSize + "," + pageSize;
							 updTable(sql);								 							 
						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是最后一页");
							box.open();
						}
					}
				}else if(combo.getText().trim().equals("语言")){
					if(!text.getText().trim().equals("")){
						sql = "select count(*) as totalCount from film_info where film_language='"+text.getText().trim()+"'";
						int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
						if((totalCount%pageSize) == 0){
							if(page < totalCount/pageSize){//如果当前页码小于最后一页的页码
								page ++;
								sql = "select * from film_info where film_language='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize;
								updTable(sql);							
							}else{
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是最后一页");
								box.open();
							}
						}else{
							if(page < totalCount/pageSize+1){
								 page ++;
								 String sql = "select * from film_info where film_language='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize;
								 updTable(sql);								 							 
							}else{
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是最后一页");
								box.open();
							}
						}
					}else{
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("请添加查询语言类型");
						box.open();
					}
				}else if(combo.getText().trim().equals("国家")){
					if(!text.getText().trim().equals("")){
						sql = "select count(*) as totalCount from film_info where film_country='"+text.getText().trim()+"'";
						int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
						if((totalCount%pageSize) == 0){
							if(page < totalCount/pageSize){//如果当前页码小于最后一页的页码
								page ++;
								sql = "select * from film_info where film_country='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize;
								updTable(sql);							
							}else{
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是最后一页");
								box.open();
							}
						}else{
							if(page < totalCount/pageSize+1){
								 page ++;
								 sql = "select * from film_info where film_country='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize;
								 updTable(sql);							 							 
							}else{
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是最后一页");
								box.open();
							}
						}
					}
				}
			}
		});
		link_2.setBounds(333, 372, 36, 17);
		link_2.setText("<a>\u4E0B\u4E00\u9875</a>");
		
		Link link_3 = new Link(composite, SWT.NONE);
		link_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		link_3.addSelectionListener(new SelectionAdapter() {
			/*尾页*/
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("全部")){
					sql = "select count(*) as totalCount from film_info";
					int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
					if((totalCount%pageSize) == 0){
						if(page != totalCount/pageSize){//如果当前页码小于最后一页的页码
							page = totalCount/pageSize;
							sql = "select * from film_info limit " + (page - 1)* pageSize + "," + pageSize;
					        updTable(sql);					
						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是最后一页");
							box.open();
						}
					}else{
						if(page < totalCount/pageSize+1){
							 page = totalCount/pageSize+1;
							 sql = "select * from film_info limit " + (page - 1)* pageSize + "," + pageSize;
							 updTable(sql);								 							 
						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是最后一页");
							box.open();
						}
					}
				}else if(combo.getText().trim().equals("语言")){
					if(!text.getText().trim().equals("")){
						sql = "select count(*) as totalCount from film_info where film_language='"+text.getText().trim()+"'";
						int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
						if((totalCount%pageSize) == 0){
							if(page < totalCount/pageSize){//如果当前页码小于最后一页的页码
								page = totalCount/pageSize;
								sql = "select * from film_info where film_language='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize;
								updTable(sql);							
							}else{
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是最后一页");
								box.open();
							}
						}else{
							if(page < totalCount/pageSize+1){
								 page = totalCount/pageSize+1;
								 sql = "select * from film_info where film_language='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize;
								 updTable(sql);								 							 
							}else{
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是最后一页");
								box.open();
							}
						}
					}else{
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("请添加查询语言类型");
						box.open();
					}
				}else if(combo.getText().trim().equals("国家")){
					if(!text.getText().trim().equals("")){
						sql = "select count(*) as totalCount from film_info where film_country='"+text.getText().trim()+"'";
						int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
						if((totalCount%pageSize) == 0){
							if(page < totalCount/pageSize){//如果当前页码小于最后一页的页码
								page = totalCount/pageSize;
								sql = "select * from film_info where film_country='"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize;
								updTable(sql);							
							}else{
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是最后一页");
								box.open();
							}
						}else{
							if(page < totalCount/pageSize+1){
								 page = totalCount/pageSize+1;
								 sql = "select * from film_info where film_country = '"+text.getText().trim()+"' limit " + (page - 1)* pageSize + "," + pageSize;
								 updTable(sql);							 							 
							}else{
								box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
								box.setText("提示信息");
								box.setMessage("已经是最后一页");
								box.open();
							}
						}
					}
				}
			}
		});
		link_3.setBounds(473, 372, 24, 17);
		link_3.setText("<a>\u5C3E\u9875</a>");
		JLabel jl=new JLabel("演示程序" );
		jl.setOpaque(false);
	}
	
	public TableItem updTable(String sql){
		table.removeAll();
		TableItem item = null;
		List<Map<String,Object>> list = ju.query(sql);
		for (int i = 0; i < list.size(); i++) {
	          item = new TableItem(table, SWT.NONE);
	          item.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
	          item.setText(new String[] {
			     list.get(i).get("film_name").toString(),
			     list.get(i).get("film_country").toString(),
			     list.get(i).get("film_director").toString(),
			     list.get(i).get("film_protagonist").toString(),
			     list.get(i).get("film_duration").toString(),
			     list.get(i).get("film_language").toString(),
			     list.get(i).get("film_type").toString(),
			     list.get(i).get("film_update").toString(),
			     list.get(i).get("film_downdate").toString()});
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
		return "QueryFilmInfoEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "QueryFilmInfoEditor";
	}
}
