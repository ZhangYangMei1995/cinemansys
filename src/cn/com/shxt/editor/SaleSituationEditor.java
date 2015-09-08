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
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import cn.com.shxt.util.JdbcUtil;
import org.eclipse.wb.swt.SWTResourceManager;

public class SaleSituationEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.SaleSituationEditor"; //$NON-NLS-1$
	private Table table;
	private JdbcUtil ju = new JdbcUtil();
	String film_name ;
	String showroom_name ;
	String sell_time ;
	String language ;
	String sell_price ;
	String client_grade  ;
	String sell_seat ;
	private int pageSize = 13;
	private int page = 1;
	private String sql ;
	private String start_date ; 
	private String end_date ;
	private MessageBox box ;
	public SaleSituationEditor() {
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
		
		Label label = new Label(container, SWT.NONE);
		label.setText("\u67E5\u8BE2\u8D77\u59CB\u65E5\u671F\uFF1A");
		label.setBounds(31, 33, 84, 17);
		
		final DateTime dateTime = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN | SWT.CALENDAR);
		dateTime.setBackgroundMode(SWT.INHERIT_DEFAULT);
		dateTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		dateTime.setBounds(147, 45, 215, 161);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u67E5\u8BE2\u7EC8\u6B62\u65E5\u671F\uFF1A");
		label_1.setBounds(496, 33, 84, 17);
		
		final DateTime dateTime_1 = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN | SWT.CALENDAR);
		dateTime_1.setBounds(636, 45, 215, 161);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(147, 237, 704, 302);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("    \u7535\u5F71\u540D\u79F0");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("     \u5F71\u5385\u540D\u79F0");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("      \u65F6\u95F4");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("      \u8BED\u8A00");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("     \u4EF7\u683C");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("    \u7528\u6237\u7EA7\u522B");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("     \u5EA7\u4F4D\u53F7");
		
		
		
		Link link = new Link(container, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
		/*首页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				if(page>1){
					page = 1 ;
					sql = "select * from selltable_info where sell_time BETWEEN '"+start_date+"' AND '"+end_date+"' limit  "+(page - 1)* pageSize + "," + pageSize;
					updTable( sql);
				}else {
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("已经是第一页");
					box.open();
				}
			}
		});
		link.setBounds(147, 607, 53, 17);
		link.setText("<a>\u9996\u9875</a>");
		
		Link link_1 = new Link(container, 0);
		link_1.addSelectionListener(new SelectionAdapter() {
			/*上一页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				if (page > 1) {
					page --;
					sql = "select * from selltable_info where sell_time BETWEEN '"+start_date+"' AND '"+end_date+"' limit  "+(page - 1)* pageSize + "," + pageSize;
					updTable( sql);
				} else {
					box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
					box.setText("提示信息");
					box.setMessage("已经是第一页");
					box.open();
				}					
				
			}
		});
		link_1.setText("<a>\u4E0A\u4E00\u9875</a>");
		link_1.setBounds(354, 607, 53, 17);
		
		Link link_2 = new Link(container, 0);
		link_2.addSelectionListener(new SelectionAdapter() {
			/*下一页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				sql = "select count(*) as totalCount from selltable_info where sell_time BETWEEN '"+start_date+"' AND '"+end_date+"'";
				int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
				if((totalCount%pageSize) == 0){
					if(page < totalCount/pageSize){//如果当前页码小于最后一页的页码
						page ++;
						sql = "select * from selltable_info where sell_time BETWEEN '"+start_date+"' AND '"+end_date+"' limit  "+(page - 1)* pageSize + "," + pageSize;
						updTable( sql);			
					}else{
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("已经是最后一页");
						box.open();
					}
				}else{
					if(page < totalCount/pageSize+1){
						 page ++;
						 sql = "select * from selltable_info where sell_time BETWEEN '"+start_date+"' AND '"+end_date+"' limit  "+(page - 1)* pageSize + "," + pageSize;
						updTable( sql);						 							 
					}else{
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("已经是最后一页");
						box.open();
					}
				}
			}
		});
		link_2.setText("<a>\u4E0B\u4E00\u9875</a>");
		link_2.setBounds(557, 607, 53, 17);
		
		Link link_3 = new Link(container, 0);
		link_3.addSelectionListener(new SelectionAdapter() {
			/* 尾页单击事件*/
			public void widgetSelected(SelectionEvent e) {
				sql = "select count(*) as totalCount from selltable_info where sell_time BETWEEN '"+start_date+"' AND '"+end_date+"'";
				int totalCount = Integer.parseInt(ju.query(sql).get(0).get("totalCount").toString());
				if((totalCount%pageSize) == 0){
					if(page < totalCount/pageSize){//如果当前页码小于最后一页的页码
						page = totalCount/pageSize;
						sql = "select * from selltable_info where sell_time BETWEEN '"+start_date+"' AND '"+end_date+"' limit  "+(page - 1)* pageSize + "," + pageSize;
						updTable( sql);			
					}else{
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("已经是最后一页");
						box.open();
					}
				}else{
					if(page < totalCount/pageSize+1){
						 page = totalCount/pageSize+1;
						 sql = "select * from selltable_info where sell_time BETWEEN '"+start_date+"' AND '"+end_date+"' limit  "+(page - 1)* pageSize + "," + pageSize;
						updTable( sql);						 							 
					}else{
						box = new MessageBox(container.getShell(), SWT.ICON_ERROR);
						box.setText("提示信息");
						box.setMessage("已经是最后一页");
						box.open();
					}
				}
			
			}
		});
		link_3.setText("<a>\u5C3E\u9875</a>");
		link_3.setBounds(798, 607, 53, 17);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/*销售明细搜索*/
			public void widgetSelected(SelectionEvent e) {
				start_date = dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
				end_date =  dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-"+dateTime_1.getDay();
				sql = "select * from selltable_info where sell_time BETWEEN '"+start_date+"' AND '"+end_date+"' limit  "+(page - 1)* pageSize + "," + pageSize;
				updTable( sql);
			}
		});
		button.setBounds(883, 33, 80, 27);
		button.setText("\u641C\u7D22");

	}
	public TableItem updTable(String sql){
		table.removeAll();
		TableItem item = null;
		List<Map<String,Object>> list = ju.query(sql);
		for(int i = 0 ; i < list.size() ; i ++){
			int onshow_id = Integer.parseInt(list.get(i).get("onshow_id").toString());//放映计划编号
			int user_id = Integer.parseInt(list.get(i).get("user_id").toString());//用户编号
			String sql1 =	"select osfilm_id,osroom_id,film_language from onshow_info where onshow_id = "+onshow_id+"";
			int film_id = Integer.parseInt(ju.query(sql1).get(0).get("osfilm_id").toString());//电影编号
			int showroom_id = Integer.parseInt(ju.query(sql1).get(0).get("osroom_id").toString());//影厅编号
			film_name = ju.query("select film_name from film_info where film_id = "+film_id+"").get(0).get("film_name").toString();// 电影名称
			showroom_name = ju.query("select showroom_name from showroom_info where showroom_id = "+showroom_id+"").get(0).get("showroom_name").toString();//影厅名称
			sell_time = list.get(i).get("sell_time").toString();//售票时间
			language = ju.query(sql1).get(0).get("film_language").toString();//影片放映语言
			sell_price = list.get(i).get("sell_price").toString();//影片售票价格
			client_grade  = ju.query("select client_grade from client_info where client_id = "+user_id+"").get(0).get("client_grade").toString();//用户等级
			sell_seat = list.get(i).get("sell_seat").toString();
			item = new TableItem(table, SWT.NONE);
			item.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			item.setText(new String[] {
					film_name,showroom_name,sell_time,language,sell_price,client_grade,sell_seat });
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
		return "SaleSituationEditorName";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "SaleSituationEditorToolTipText";
	}
}
