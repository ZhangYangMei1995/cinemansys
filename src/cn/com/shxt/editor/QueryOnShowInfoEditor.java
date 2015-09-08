package cn.com.shxt.editor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import cn.com.shxt.dialog.UpdateOnShowDialog1;
import cn.com.shxt.util.JdbcUtil;
import org.eclipse.wb.swt.SWTResourceManager;

public class QueryOnShowInfoEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.QueryOnShowInfoEditor"; //$NON-NLS-1$
	private Table table;
	private JdbcUtil ju=new JdbcUtil();
	private String mouth;
	private String day;
	private String mouth1;
	private String day1;
	public QueryOnShowInfoEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		table.setBounds(28, 240, 1043, 250);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("   \u7535\u5F71\u540D\u79F0");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(112);
		tableColumn_7.setText("    \u5F71\u5385\u540D\u79F0");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("     \u56FD\u5BB6");
		
		final DateTime dateTime = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN | SWT.CALENDAR);
		dateTime.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		dateTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		dateTime.setBounds(142, 59, 215, 161);
		
		final DateTime dateTime_1 = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN | SWT.CALENDAR);
		dateTime_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		dateTime_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		dateTime_1.setBounds(727, 59, 215, 161);
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("      \u5BFC\u6F14");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(126);
		tableColumn_3.setText("       \u4E3B\u6F14");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("       \u65F6\u957F");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("      \u8BED\u8A00");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("    \u7C7B\u578B");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			/*删除*/
			public void widgetSelected(SelectionEvent e) {
				
				TableItem [] items=table.getSelection();
				for(TableItem item:items){
					String s = "select film_id from film_info where film_name = '"+item.getText(0)+"'";
					List<Map<String,Object>> l = ju.query(s);
					ju.update("delete from onshow_info where osfilm_id='"+l.get(0).get("film_id")+"' and onshow_date = '"+item.getText(8)+"' and filmstart_time = '"+item.getText(9)+"'");
					table.removeAll();	
					//刷新表格
					int []date=new int []{dateTime.getYear(),(dateTime.getMonth()+1),dateTime.getDay()};
					int []date1=new int []{dateTime_1.getYear(),(dateTime_1.getMonth()+1),dateTime_1.getDay()};
					if(date[1]<10){
						mouth="0"+date[1];
					}else{
						mouth=String.valueOf(date[1]);
					}			
					if(date[2]<10){
						day="0"+date[2];
					}else{
						day=String.valueOf(date[2]);
					}
					
					if(date1[1]<10){
						mouth1="0"+date1[1];
					}else{
						mouth1=String.valueOf(date1[1]);
					}
					if(date1[2]<10){
						day1="0"+date1[2];
					}else{
						day1=String.valueOf(date1[2]);
					}
					String date_1=date[0]+"-"+mouth+"-"+day;
					String date_2=date1[0]+"-"+mouth1+"-"+day1;
					//把date_1和date_2转化成Date对象				
					try {
						String sql="select * from onshow_info ";
						List<Map<String,Object>>list=ju.query(sql);
						long date_seconds=new SimpleDateFormat("yyyy-MM-dd").parse(date_1).getTime();
						long date1_seconds=new SimpleDateFormat("yyyy-MM-dd").parse(date_2).getTime();
						
						for(int i=0;i<list.size();i++){						 
							 //Date onshow_date = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).get("onshow_date").toString());
							long time=new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).get("onshow_date").toString()).getTime();
							
							String sql1="select * from film_info where film_id="+list.get(i).get("osfilm_id");
							List<Map<String,Object>>list1=ju.query(sql1);
							String sql2="select * from showroom_info where showroom_id="+list.get(i).get("osroom_id")+"";
							List<Map<String,Object>>list2=ju.query(sql2);
							if(time>date_seconds&&time<date1_seconds){	
						    	TableItem item1 = new TableItem(table, SWT.NONE);
								item1.setText(new String[] {
										list1.get(0).get("film_name").toString(),	
										list2.get(0).get("showroom_name").toString(),
										list1.get(0).get("film_country").toString(),
										list1.get(0).get("film_director").toString(),
										list1.get(0).get("film_protagonist").toString(),
										list1.get(0).get("film_duration").toString(),
										list.get(i).get("film_language").toString(),
										list1.get(0).get("film_type").toString(),
										list.get(i).get("onshow_date").toString(),
										list.get(i).get("filmstart_time").toString()
										});
						    }
						}
					} catch (ParseException e2) {
						e2.printStackTrace();
					}
				}	
			}
		});
		menuItem.setText("\u5220\u9664");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			/*上映计划修改*/
			public void widgetSelected(SelectionEvent e) {
				TableItem [] items=table.getSelection();
				for(TableItem item:items){
					String f_name = item.getText(0);
					String r_name = item.getText(1);
					String sql = "select film_id from film_info where film_name = '"+item.getText(0)+"'";
					String sql1 = "select showroom_id from showroom_info where showroom_name ='"+item.getText(1)+"'";
				    int f_id = Integer.parseInt(ju.query(sql).get(0).get("film_id").toString());
				    int r_id = Integer.parseInt(ju.query(sql1).get(0).get("showroom_id").toString());
					String s = "select onshow_id from onshow_info where osfilm_id = "+f_id+" and osroom_id = "+r_id+" " +
							"and onshow_date = '"+item.getText(8)+"' and filmstart_time = '"+item.getText(9)+"' " +
									"and film_language = '"+item.getText(6)+"' ";
					List<Map<String,Object>> l = ju.query(s);
					int onshow_id = Integer.parseInt(l.get(0).get("onshow_id").toString());
					
					
					/*UpdateOnShowDialog upd = new UpdateOnShowDialog(container.getShell(), SWT.NONE);
					upd.open(onshow_id, f_name, r_name);*/
					UpdateOnShowDialog1 upd = new UpdateOnShowDialog1(container.getShell(), SWT.NONE);
					upd.open(onshow_id, f_name, r_name);
					//刷新表格

					table.removeAll();				
					int []date=new int []{dateTime.getYear(),(dateTime.getMonth()+1),dateTime.getDay()};
					int []date1=new int []{dateTime_1.getYear(),(dateTime_1.getMonth()+1),dateTime_1.getDay()};
					if(date[1]<10){
						mouth="0"+date[1];
					}else{
						mouth=String.valueOf(date[1]);
					}			
					if(date[2]<10){
						day="0"+date[2];
					}else{
						day=String.valueOf(date[2]);
					}
					
					if(date1[1]<10){
						mouth1="0"+date1[1];
					}else{
						mouth1=String.valueOf(date1[1]);
					}
					if(date1[2]<10){
						day1="0"+date1[2];
					}else{
						day1=String.valueOf(date1[2]);
					}
					String date_1=date[0]+"-"+mouth+"-"+day;
					String date_2=date1[0]+"-"+mouth1+"-"+day1;
					//把date_1和date_2转化成Date对象				
					try {
						String sql_1="select * from onshow_info ";
						List<Map<String,Object>>list=ju.query(sql_1);
						long date_seconds=new SimpleDateFormat("yyyy-MM-dd").parse(date_1).getTime();
						long date1_seconds=new SimpleDateFormat("yyyy-MM-dd").parse(date_2).getTime();
						
						for(int i=0;i<list.size();i++){						 
							// Date onshow_date = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).get("onshow_date").toString());
							long time=new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).get("onshow_date").toString()).getTime();
							String sql1_1="select * from film_info where film_id="+list.get(i).get("osfilm_id");
							List<Map<String,Object>>list1=ju.query(sql1_1);
							String sql2="select * from showroom_info where showroom_id="+list.get(i).get("osroom_id")+"";
							List<Map<String,Object>>list2=ju.query(sql2);
							if(time>date_seconds&&time<date1_seconds){	
						    	TableItem item1 = new TableItem(table, SWT.NONE);
								item1.setText(new String[] {
										list1.get(0).get("film_name").toString(),	
										list2.get(0).get("showroom_name").toString(),
										list1.get(0).get("film_country").toString(),
										list1.get(0).get("film_director").toString(),
										list1.get(0).get("film_protagonist").toString(),
										list1.get(0).get("film_duration").toString(),
										list.get(i).get("film_language").toString(),
										list1.get(0).get("film_type").toString(),
										list.get(i).get("onshow_date").toString(),
										list.get(i).get("filmstart_time").toString()
										});
						    }
						}
					} catch (ParseException e2) {
						e2.printStackTrace();
					}

				
				}
			}
		});
		menuItem_1.setText("\u4FEE\u6539");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("   \u653E\u6620\u65E5\u671F");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("    \u5F00\u59CB\u65F6\u95F4");
		
		Label label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label.setBounds(26, 47, 84, 17);
		label.setText("\u67E5\u8BE2\u8D77\u59CB\u65E5\u671F\uFF1A");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_1.setBounds(587, 47, 84, 17);
		label_1.setText("\u67E5\u8BE2\u7EC8\u6B62\u65E5\u671F\uFF1A");
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/*上映计划查询*/
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();				
				int []date=new int []{dateTime.getYear(),(dateTime.getMonth()+1),dateTime.getDay()};
				int []date1=new int []{dateTime_1.getYear(),(dateTime_1.getMonth()+1),dateTime_1.getDay()};
				if(date[1]<10){
					mouth="0"+date[1];
				}else{
					mouth=String.valueOf(date[1]);
				}			
				if(date[2]<10){
					day="0"+date[2];
				}else{
					day=String.valueOf(date[2]);
				}
				
				if(date1[1]<10){
					mouth1="0"+date1[1];
				}else{
					mouth1=String.valueOf(date1[1]);
				}
				if(date1[2]<10){
					day1="0"+date1[2];
				}else{
					day1=String.valueOf(date1[2]);
				}
				String date_1=date[0]+"-"+mouth+"-"+day;
				String date_2=date1[0]+"-"+mouth1+"-"+day1;
				//把date_1和date_2转化成Date对象				
				try {
					String sql="select * from onshow_info ";
					List<Map<String,Object>>list=ju.query(sql);
					long date_seconds=new SimpleDateFormat("yyyy-MM-dd").parse(date_1).getTime();
					long date1_seconds=new SimpleDateFormat("yyyy-MM-dd").parse(date_2).getTime();
					
					for(int i=0;i<list.size();i++){						 
						// Date onshow_date = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).get("onshow_date").toString());
						long time=new SimpleDateFormat("yyyy-MM-dd").parse(list.get(i).get("onshow_date").toString()).getTime();
						
						String sql1="select * from film_info where film_id="+list.get(i).get("osfilm_id");
						List<Map<String,Object>>list1=ju.query(sql1);
						String sql2="select * from showroom_info where showroom_id="+list.get(i).get("osroom_id")+"";
						List<Map<String,Object>>list2=ju.query(sql2);
						if(time>date_seconds&&time<date1_seconds){	
							
					    	TableItem item1 = new TableItem(table, SWT.NONE);
							item1.setText(new String[] {
									list1.get(0).get("film_name").toString(),	
									list2.get(0).get("showroom_name").toString(),
									list1.get(0).get("film_country").toString(),
									list1.get(0).get("film_director").toString(),
									list1.get(0).get("film_protagonist").toString(),
									list1.get(0).get("film_duration").toString(),
									list.get(i).get("film_language").toString(),
									list1.get(0).get("film_type").toString(),
									list.get(i).get("onshow_date").toString(),
									list.get(i).get("filmstart_time").toString()
									});
					    }
					}
				} catch (ParseException e2) {
					e2.printStackTrace();
				}

			}
		});
		button.setBounds(977, 192, 80, 27);
		button.setText("\u786E\u5B9A");
		
		
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
		this.setInput(input);
		this.setSite(site);
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
		return "QueryOnShowInfoEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "QueryOnShowInfoEditor";
	}
}
