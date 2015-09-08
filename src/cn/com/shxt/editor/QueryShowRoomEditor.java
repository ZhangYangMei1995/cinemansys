package cn.com.shxt.editor;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.dialog.SearchShowFilmDialog;
import cn.com.shxt.dialog.UpdateShowRoomDialog;
import cn.com.shxt.util.JdbcUtil;

public class QueryShowRoomEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.QueryShowRoomEditor"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private MessageBox box;
	private JdbcUtil ju=new JdbcUtil();
	private int page = 1; // 当前页书
	private int pageSize =7; // 每页显示条数
	int [] film_id ;
	int [] onshow_id ;
	public QueryShowRoomEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		container.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(315, 28, 123, 23);
		
		final Combo combo = new Combo(container, SWT.READ_ONLY);
		combo.setBackgroundMode(SWT.INHERIT_DEFAULT);
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		combo.setItems(new String[] {"   \u5168\u90E8", "   \u540D\u79F0"});
		combo.setBounds(100, 28, 88, 25);
		combo.select(0);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/*查询放映厅*/
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				if(combo.getText().trim().equals("全部")){
					String sql="select * from showroom_info limit 0,7";
					List<Map<String,Object>>list=ju.query(sql);
					for (int i = 0; i < list.size(); i++) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
						item.setText(new String[] {
								list.get(i).get("showroom_name").toString(),
								list.get(i).get("showroom_type").toString(),
								list.get(i).get("showroom_row").toString(),
								list.get(i).get("showroom_num").toString(),
								});
					}
				}else if(combo.getText().trim().equals("名称")){
					//String name=text.getText().trim();
					if(text.getText().trim().equals("")){
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("请添加放映厅名称");
						box.open();
						
					}else{
						String sql="select showroom_name,showroom_type,showroom_row,showroom_num from showroom_info where showroom_name='"+text.getText().trim()+"'";
						List<Map<String,Object>>list=ju.query(sql);
						
						if(list.size()==0){
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("不存在该放放映厅");
							box.open();
							
						}else if(list.size()==1){
							TableItem item = new TableItem(table, SWT.NONE);
							item.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
							item.setText(new String[] {
									list.get(0).get("showroom_name").toString(),
									list.get(0).get("showroom_type").toString(),
									list.get(0).get("showroom_row").toString(),
									list.get(0).get("showroom_num").toString()});
						}
					}
				}								
			}
		});
		button.setBounds(563, 28, 80, 27);
		button.setText("\u67E5\u8BE2");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(100, 123, 553, 176);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(140);
		tableColumn.setText("           \u540D\u79F0");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(145);
		tableColumn_1.setText("           \u7C7B\u578B");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(122);
		tableColumn_2.setText("           \u6392\u6570");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			/*删除事件*/
			public void widgetSelected(SelectionEvent e) {

				TableItem [] items=table.getSelection();		
				for(TableItem item:items){
					//String name=item.getText(0).toString();
					String sq="select showroom_id from showroom_info where showroom_name='"+item.getText(0)+"'";					
					String sql="select onshow_date from onshow_info where osroom_id="+ju.query(sq).get(0).get("showroom_id")+"";					
					List<Map<String,Object>>list=ju.query(sql);
					int count=0;
					if(list.size()!=0){						
						Date date=new Date();						
						for(int i=0;i<list.size();i++){							
							try{
								//System.out.println(00);
								Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).get("onshow_date").toString()); 			
								while(date.getTime()<date1.getTime()){
									count++;//将占用该影厅的次数
									box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
									 box.setMessage("有电影在该影厅即将上映,请勿删除");
									 box.setText("提示信息");
									 box.open();
									 break;
								}
							}catch (Exception e1){
								e1.printStackTrace();
							}
							System.out.println(count);
							System.out.println(list.size());
							if(count!=list.size()){
						
								String sql2="delete from showroom_info where showroom_name='"+item.getText(0)+"'";
								String sql3="delete from showroomseat_info where showroom_id="+ju.query(sq).get(0).get("showroom_id")+"";
								String []s=new String[]{sql3,sql2};
								ju.batch(s);
								/*刷新表格*/
								table.removeAll();						
								String sql1 = "select * from showroom_info limit "+(page-1)*pageSize+","+pageSize;
								List<Map<String,Object>> list1 = ju.query(sql1);								
								for (int j = 0; j < list1.size(); j++) {
									TableItem item1 = new TableItem(table, SWT.NONE);
									item1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
									item1.setText(new String[] {
											list1.get(j).get("showroom_name").toString(),
											list1.get(j).get("showroom_type").toString(),
											list1.get(j).get("showroom_row").toString(),
											list1.get(j).get("showroom_num").toString()});
								}
							}
						}	
						
					}else{
						/*多表联合删除时，用级联更新，注意先删有外键的表，再删除主键表，所以数组s{sql3,sql2}*/
						String sql2="delete from showroom_info where showroom_name='"+item.getText(0)+"'";
						String sql3="delete from showroomseat_info where showroom_id="+ju.query(sq).get(0).get("showroom_id")+"";
						String []s=new String[]{sql3,sql2};
						ju.batch(s);
						/*刷新表格*/
						table.removeAll();						
						String sql1 = "select * from showroom_info limit "+(page-1)*pageSize+","+pageSize;
						List<Map<String,Object>> list1 = ju.query(sql1);
						
						for (int i = 0; i < list1.size(); i++) {
							TableItem item1 = new TableItem(table, SWT.NONE);
							item1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
							item1.setText(new String[] {
									list1.get(i).get("showroom_name").toString(),
									list1.get(i).get("showroom_type").toString(),
									list1.get(i).get("showroom_row").toString(),
									list1.get(i).get("showroom_num").toString()});
						}	
					}											
				}				
			}
		});
		menuItem.setText("\u5220\u9664");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			/*单击查看事件*/
			public void widgetSelected(SelectionEvent e) {				
				TableItem [] items=table.getSelection();		
				for(TableItem item:items){					
					String name=item.getText(0);
					
					String type=item.getText(1);
					int row=Integer.parseInt(item.getText(2));
					String sql="select showroom_id from showroom_info where showroom_name='"+name+"'";
					int id=Integer.parseInt(ju.query(sql).get(0).get("showroom_id").toString());
					
					UpdateShowRoomDialog asrd=new UpdateShowRoomDialog(container.getShell(),SWT.NONE);									
				    asrd.open(name,type,row,id);
				    
				    table.removeAll();						
					String sql1 = "select * from showroom_info limit "+(page-1)*pageSize+","+pageSize;
					List<Map<String,Object>> list1 = ju.query(sql1);
					
					for (int i = 0; i < list1.size(); i++) {
						TableItem item1 = new TableItem(table, SWT.NONE);
						item1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
						item1.setText(new String[] {
								list1.get(i).get("showroom_name").toString(),
								list1.get(i).get("showroom_type").toString(),
								list1.get(i).get("showroom_row").toString(),
								list1.get(i).get("showroom_num").toString()});
					}
				}				
			}
		});
		menuItem_1.setText("\u67E5\u770B\u53CA\u4FEE\u6539");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			/*查看放映信息*/
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table.getSelection();
				Date date1 = new Date();
				Date date2 = new Date();
				long date_1 = date1.getTime()-72*3600*1000;//前三天
				long date_2 = date2.getTime()+72*3600*1000;//后三天
				date1.setTime(date_1);
				date2.setTime(date_2);
				for(TableItem items  : item) {
					String showroom_name = items.getText(0);
					String sql = "SELECT film_id,onshow_id FROM showroom_info , onshow_info , film_info WHERE showroom_name = '"+showroom_name+"' AND osroom_id = showroom_id AND osfilm_id = film_id AND onshow_date BETWEEN '"+date1.toLocaleString()+"' AND '"+date2.toLocaleString()+"'";
					List<Map<String , Object>> list = ju.query(sql);
					film_id = new int [list.size()];// 数组实例化
					onshow_id = new int [list.size()] ;// 数组实例化
					for(int i = 0 ; i < list.size() ; i ++){
						film_id [i] = Integer.parseInt(list.get(i).get("film_id").toString());
						onshow_id[i] = Integer.parseInt(list.get(i).get("onshow_id").toString());
					}
					 
				}
				SearchShowFilmDialog ssf = new SearchShowFilmDialog (container.getShell(),SWT.NONE);
				ssf.open(  film_id  ,  onshow_id);
			}
		});
		menuItem_2.setText("\u67E5\u770B\u653E\u6620\u4FE1\u606F");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(136);
		tableColumn_3.setText("           \u5EA7\u4F4D\u6570");
		
		
		Link link = new Link(container, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			/*首页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("全部")){
					if (page > 1) {
						page = 1;
						if(combo.getText().trim().equals("全部")){
							String sql = "select * from showroom_info limit 0,4";
							List<Map<String, Object>> list = ju.query(sql);
							table.removeAll();
							for (int i = 0; i < list.size(); i++) {
								TableItem item = new TableItem(table, SWT.NONE);
								item.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
								item.setText(new String[] {
										list.get(i).get("showroom_name").toString(),
										list.get(i).get("showroom_type").toString(),
										list.get(i).get("showroom_row").toString(),
										list.get(i).get("showroom_num").toString()});

						}
					}
				 }else{
						box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("已经是首页");
						 box.setText("提示信息");
						 box.open();
					}
				}else{
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("仅此一条记录");
					 box.setText("提示信息");
					 box.open();
				}
				
		}
		});
		link.setBounds(100, 375, 53, 17);
		link.setText("<a>\u9996\u9875</a>");
		
		Link link_1 = new Link(container, 0);
		link_1.addSelectionListener(new SelectionAdapter() {
			/*上一页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("全部")){
					if (page > 1) {
						page--;
						if(combo.getText().trim().equals("全部")){
							String sql = "select * from showroom_info limit "+(page-1)* pageSize+","+pageSize+"";
							List<Map<String, Object>> list = ju.query(sql);
							table.removeAll();
							for (int i = 0; i < list.size(); i++) {
								TableItem item = new TableItem(table, SWT.NONE);
								item.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
								item.setText(new String[] {
										list.get(i).get("showroom_name").toString(),
										list.get(i).get("showroom_type").toString(),
										list.get(i).get("showroom_row").toString(),
										list.get(i).get("showroom_num").toString()});
						}
					}			
				  }else if(page==1){
						box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
						 box.setMessage("已经是首页");
						 box.setText("提示信息");
						 box.open();
					}
				}else{
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("仅此一条记录");
					 box.setText("提示信息");
					 box.open();
				}			
			}
		});
		link_1.setText("<a>\u4E0A\u4E00\u9875</a>");
		link_1.setBounds(266, 375, 53, 17);
		
		Link link_2 = new Link(container, 0);
		link_2.addSelectionListener(new SelectionAdapter() {
			/*下一页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("全部")){
					String sql = "select count(*) as totalCount from showroom_info";
					int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
					if((totalCount%pageSize) == 0){
						if(page < totalCount/pageSize){
							 page++;
							 String sql1 = "select * from showroom_info limit "+(page-1)* pageSize+","+pageSize+"";
								List<Map<String, Object>> list = ju.query(sql1);
								table.removeAll();
								for (int i = 0; i < list.size(); i++) {
									TableItem item = new TableItem(table, SWT.NONE);
									item.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
									item.setText(new String[] {
											list.get(i).get("showroom_name").toString(),
											list.get(i).get("showroom_type").toString(),
											list.get(i).get("showroom_row").toString(),
											list.get(i).get("showroom_num").toString()});
							}
							 
						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是最后一页");
							box.open();
						}
					}else{
						if(page < totalCount/pageSize+1){
							 page++;
							 String sql1 = "select * from showroom_info limit "+(page-1)* pageSize+","+pageSize+"";
								List<Map<String, Object>> list = ju.query(sql1);
								table.removeAll();
								for (int i = 0; i < list.size(); i++) {
									TableItem item = new TableItem(table, SWT.NONE);
									item.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
									item.setText(new String[] {
											list.get(i).get("showroom_name").toString(),
											list.get(i).get("showroom_type").toString(),
											list.get(i).get("showroom_row").toString(),
											list.get(i).get("showroom_num").toString()});
							}
						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是最后一页");
							box.open();
						}
					}
				}else{
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("仅此一条记录");
					 box.setText("提示信息");
					 box.open();
				}			
			}
		});
		link_2.setText("<a>\u4E0B\u4E00\u9875</a>");
		link_2.setBounds(440, 375, 53, 17);
		
		Link link_3 = new Link(container, 0);
		link_3.addSelectionListener(new SelectionAdapter() {
			/*尾页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("全部")){
					String sqlCount = "select count(*) as totalCount from showroom_info";
					int totalCount = Integer.parseInt(ju.query(sqlCount).get(0).get("totalCount").toString());
					if (totalCount % pageSize == 0) {
						if(page < totalCount / pageSize){//如果当前页码小于最后一页的页码
							page = totalCount / pageSize ; 
							table.removeAll();
							/* 重新给表格赋值 */
							String sql = "select * from showroom_info limit " + (page - 1)
									* pageSize + "," + pageSize;
							List<Map<String, Object>> list = ju.query(sql);

							for (int i = 0; i < list.size(); i++) {
								TableItem item = new TableItem(table, SWT.NONE);
								item.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
								item.setText(new String[] {
										list.get(i).get("showroom_name").toString(),
										list.get(i).get("showroom_type").toString(),
										list.get(i).get("showroom_row").toString(),
										list.get(i).get("showroom_num").toString()});
							}

						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是最后一页");
							box.open();
						}
					}else{
						if(page < totalCount / pageSize + 1){//如果当前页码小于最后一页的页码
							page = totalCount / pageSize + 1 ; 
							table.removeAll();
							/* 重新给表格赋值 */
							String sql = "select * from showroom_info limit " + (page - 1)* pageSize + "," + pageSize;
							List<Map<String, Object>> list = ju.query(sql);

							for (int i = 0; i < list.size(); i++) {
								TableItem item = new TableItem(table, SWT.NONE);
								item.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
								item.setText(new String[] {
										list.get(i).get("showroom_name").toString(),
										list.get(i).get("showroom_type").toString(),
										list.get(i).get("showroom_row").toString(),
										list.get(i).get("showroom_num").toString()});
							}
						}else{
							box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
							box.setText("提示信息");
							box.setMessage("已经是最后一页");
							box.open();
						}
					}
				}else{
					box=new MessageBox(container.getShell(),SWT.ICON_ERROR);
					 box.setMessage("仅此一条记录");
					 box.setText("提示信息");
					 box.open();
				}		
			}
		});
		link_3.setText("<a>\u5C3E\u9875</a>");
		link_3.setBounds(600, 375, 53, 17);
		
		
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
		return "QueryShowRoomEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "QueryShowRoomEditor";
	}
}

