package cn.com.shxt.editor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.util.JdbcUtil;
import org.eclipse.swt.widgets.Group;

public class SalePercentEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.SalePercentEditor"; //$NON-NLS-1$
	private Table table;
	private Table table_1;
	private JdbcUtil db = new JdbcUtil();
	private MessageBox box;
	private double year_sell;
	private double month_sell;
	private SimpleDateFormat de = new SimpleDateFormat("yyyy-MM-dd");
	private double month_pecent;
	private int count_i;
	private double avg_price_i;
	private List<Integer> list_pecent = new ArrayList<Integer>();
	private ProgressBar month1;
	private ProgressBar month2;
	private ProgressBar month3;
	private ProgressBar month4;
	private ProgressBar month5;
	private ProgressBar month6;
	private ProgressBar month7;
	private ProgressBar month8;
	private ProgressBar month9;
	private ProgressBar month10;
	private ProgressBar month11;
	private ProgressBar month12;
	public SalePercentEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(36, 47, 108, 17);
		lblNewLabel.setText("\u9009\u62E9\u8981\u67E5\u8BE2\u7684\u5E74\u4EFD\uFF1A");
		
		final DateTime dateTime = new DateTime(container, SWT.BORDER | SWT.SHORT);
		dateTime.setBounds(157, 47, 69, 24);
		
		final Group group = new Group(container, SWT.NONE);
		group.setText("\u6BD4\u4F8B\u67F1\u5F62\u56FE");
		group.setBounds(22, 469, 663, 211);
		
		month1 = new ProgressBar(group, SWT.VERTICAL);
		month1.setBounds(25, 48, 17, 113);
		
		month2 = new ProgressBar(group, SWT.VERTICAL);
		month2.setBounds(85, 48, 17, 113);
		
		month3= new ProgressBar(group, SWT.VERTICAL);
		month3.setBounds(144, 48, 17, 113);
		
		month4 = new ProgressBar(group, SWT.VERTICAL);
		month4.setBounds(200, 48, 17, 113);
		
		month5 = new ProgressBar(group, SWT.VERTICAL);
		month5.setBounds(258, 48, 17, 113);
		
		month6 = new ProgressBar(group, SWT.VERTICAL);
		month6.setBounds(308, 48, 17, 113);
		
		month7 = new ProgressBar(group, SWT.VERTICAL);
		month7.setBounds(363, 48, 17, 113);
		
		month8 = new ProgressBar(group, SWT.VERTICAL);
		month8.setBounds(415, 48, 17, 113);
		
		month9 = new ProgressBar(group, SWT.VERTICAL);
		month9.setBounds(469, 48, 17, 113);
		
		month10 = new ProgressBar(group, SWT.VERTICAL);
		month10.setBounds(515, 48, 17, 113);
		
		month11 = new ProgressBar(group, SWT.VERTICAL);
		month11.setBounds(559, 48, 17, 113);
		
		month12 = new ProgressBar(group, SWT.VERTICAL);
		month12.setBounds(611, 48, 17, 113);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(36, 179, 671, 284);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(91);
		tableColumn_7.setText("      \u6708\u4EFD");
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(138);
		tableColumn.setText("\u9500\u552E\u91CF(\u5F20)");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(140);
		tableColumn_1.setText("\u5E73\u5747\u4EF7\u683C(\u5143)");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(138);
		tableColumn_2.setText("\u9500\u552E\u989D(\u5143)");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(157);
		tableColumn_6.setText("\u5360\u5E74\u9500\u552E\u6BD4");
		
		table_1 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(36, 100, 649, 50);
		
		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_3.setWidth(215);
		tableColumn_3.setText("\u9500\u552E\u91CF(\u5F20)");
		
		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_4.setWidth(215);
		tableColumn_4.setText("\u5E73\u5747\u4EF7\u683C(\u5143)");
		
		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_5.setWidth(214);
		tableColumn_5.setText("\u9500\u552E\u989D(\u5143)");
		
		final Label label = new Label(container, SWT.NONE);
		label.setBounds(36, 156, 80, 17);
		label.setText("\u6708\u9500\u552E\u4FE1\u606F\uFF1A");
		
		final Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(36, 77, 80, 17);
		label_1.setText("\u5E74\u9500\u552E\u4FE1\u606F\uFF1A");
		
		final Label label_15 = new Label(container, SWT.NONE);
		label_15.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_15.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_15.setBounds(36, 10, 190, 31);
		label_15.setText("*\u8BE5\u5E74\u8FD8\u672A\u6709\u552E\u51FA\u7EAA\u5F55");
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("1\u6708");
		label_2.setBounds(25, 174, 17, 17);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("2\u6708");
		label_3.setBounds(85, 174, 17, 17);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("3\u6708");
		label_4.setBounds(140, 174, 17, 17);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("4\u6708");
		label_5.setBounds(200, 174, 17, 17);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("5\u6708");
		label_6.setBounds(258, 174, 17, 17);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("6\u6708");
		label_7.setBounds(308, 174, 17, 17);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("7\u6708");
		label_8.setBounds(363, 174, 17, 17);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setText("8\u6708");
		label_9.setBounds(415, 174, 17, 17);
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setText("9\u6708");
		label_10.setBounds(469, 174, 17, 17);
		
		Label label_11 = new Label(group, SWT.NONE);
		label_11.setText("10\u6708");
		label_11.setBounds(515, 174, 27, 17);
		
		Label label_12 = new Label(group, SWT.NONE);
		label_12.setText("11\u6708");
		label_12.setBounds(559, 174, 40, 17);
		
		Label label_13 = new Label(group, SWT.NONE);
		label_13.setText("12\u6708");
		label_13.setBounds(611, 174, 27, 17);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/**
			 * 搜索
			 * */
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				table_1.removeAll();
				data( dateTime, group,table_1,table,label,label_1, label_15, month1, month2, month3, month4, month5, month6, month7, month8, month9, month10, month11, month12);
				
			}
		});
		button.setBounds(254, 42, 80, 27);
		button.setText("\u67E5\u8BE2");
		label_15.setVisible(false);
		data( dateTime, group,table_1,table,label,label_1, label_15, month1, month2, month3, month4, month5, month6, month7, month8, month9, month10, month11, month12);
		
	}
	
	public void data(DateTime dateTime,Group group,Table table_1,Table table,Label label,Label label_1,Label label_15,ProgressBar month1,ProgressBar month2,ProgressBar month3,ProgressBar month4,ProgressBar 
			month5,ProgressBar month6,ProgressBar month7,ProgressBar month8,ProgressBar month9,ProgressBar month10,ProgressBar month11,ProgressBar month12){
		//年销售
		int year = dateTime.getYear();
		String sql = "SELECT COUNT(*) AS num ,AVG(sell_price) AS avg_price FROM selltable_info WHERE sell_time LIKE '"+year+"%' ";
		List<Map<String , Object>> list = db.query(sql);
		int count = Integer.parseInt(list.get(0).get("num").toString());  //年销售量，即卖了多少张票
		if(count > 0){
			double avg_price = Double.parseDouble(list.get(0).get("avg_price").toString());  //年销售平均价格
			year_sell = count*avg_price;  //年销售额
			TableItem tableItem = new TableItem(table_1, SWT.NONE);
			tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			tableItem.setText(new String[]{String.valueOf(count),String.valueOf (avg_price),String.valueOf(year_sell)});
			//月销售
			for(int i = 0 ; i< 12 ; i++){
				String date = dateTime.getYear()+"-"+(i+1)+"-"+1;
				try {
					Date d = de.parse(date);
					String da = de.format(d);
					String[] month_date = da.split("-");
					String month = month_date[0]+"-"+month_date[1];
					//System.out.println(month);  2013-01
					
					String sqls= "SELECT COUNT(*) AS num ,AVG(sell_price) AS avg_price FROM selltable_info WHERE sell_time LIKE '"+month+"%' ";
					List<Map<String, Object>> list_i = db.query(sqls);
					int num = Integer.parseInt(list_i.get(0).get("num").toString());  //每月销售量，即每月卖的票数
					if(num > 0){
						count_i = Integer.parseInt(list_i.get(0).get("num").toString());  //每月销售量
						avg_price_i = Double.parseDouble(list_i.get(0).get("avg_price").toString());  //每月平均价格
						month_sell = count_i*avg_price_i;  //每月总销售额
						month_pecent = month_sell/year_sell*100;  //月销售额占年销售额的百分比
					}else{
						count_i = 0;
						avg_price_i = 0;
						month_sell = 0;
						month_pecent = 0;
					}
					list_pecent.add((int)month_pecent);
					TableItem tableItem_1 = new TableItem(table, SWT.NONE);
					tableItem_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					tableItem_1.setText(new String[]{String.valueOf(i+1),String.valueOf(count_i),String.valueOf(avg_price_i),String.valueOf(month_sell),String.valueOf(month_pecent+"%")});
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
			month1.setSelection(list_pecent.get(0));
			month2.setSelection(list_pecent.get(1));
			month3.setSelection(list_pecent.get(2));
			month4.setSelection(list_pecent.get(3));
			month5.setSelection(list_pecent.get(4));
			month6.setSelection(list_pecent.get(5));
			month7.setSelection(list_pecent.get(6));
			month8.setSelection(list_pecent.get(7));
			month9.setSelection(list_pecent.get(8));
			month10.setSelection(list_pecent.get(9));
			month11.setSelection(list_pecent.get(10));
			month12.setSelection(list_pecent.get(11));
			
			label_15.setVisible(false);
			table.setVisible(true);
			table_1.setVisible(true);
			group.setVisible(true);
			label.setVisible(true);
			label_1.setVisible(true);
		}else{
			label_15.setVisible(true);
			table.setVisible(false);
			table_1.setVisible(false);
			group.setVisible(false);
			label.setVisible(false);
			label_1.setVisible(false);
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
		return "SalePercentEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "SalePercentEditor";
	}
}
