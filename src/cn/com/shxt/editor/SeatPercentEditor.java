package cn.com.shxt.editor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import cn.com.shxt.util.JdbcUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class SeatPercentEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.SeatPercentEditor"; //$NON-NLS-1$
	private Date d = new Date();
	@SuppressWarnings("deprecation")
	private String date = d.toLocaleString().split(" ")[0];
	private SimpleDateFormat de = new SimpleDateFormat("yyyy-MM-dd");
	private String date_now;
	private JdbcUtil db = new JdbcUtil();
	private Label hallLbl;
	private ProgressBar progressBar;
	private Label percentLbl;
	private  Group group;
	public SeatPercentEditor() {
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
		
		final DateTime dateTime = new DateTime(container, SWT.BORDER | SWT.SHORT);
		dateTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		dateTime.setBackgroundMode(SWT.INHERIT_DEFAULT);
		dateTime.setBounds(221, 29, 93, 24);
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(49, 29, 139, 17);
		label.setText("\u8BF7\u9009\u62E9\u65E5\u671F\uFF08\u5E74\u3001\u6708\uFF09\uFF1A");

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/**
			 * 搜索
			 * */
			public void widgetSelected(SelectionEvent e) {
				group.dispose();
				//group.setVisible(false);
				percent(dateTime,container);
				
				String [] set_date = date.split("-");
				dateTime.setDate(Integer.parseInt(set_date[0]), Integer.parseInt(set_date[1])-1, Integer.parseInt(set_date[2]));
			//	label_1.setVisible(true);
			}
		});
		button.setBounds(393, 29, 80, 27);
		button.setText("\u641C\u7D22");

		percent(dateTime,container);

	}	
	
	public void percent(DateTime dateTime,Composite  container){
		
		group = new Group(container, SWT.NONE);
		group.setText("\u5404\u653E\u6620\u5385\u4E0A\u5EA7\u7387");
		group.setBounds(49, 71, 800, 600);
		
		String da = dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
		Date ds;
		try {
			ds = de.parse(da);//把字符串类型的时间转换成Date对象
			String change_date = de.format(ds);//把Date对象转换成字符串类型
			date_now = change_date.split("-")[0]+"-"+change_date.split("-")[1];
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int x1 = 77;
		int x2 = 88;
		int x3 = 77;
		int y1 = 53;
		int y2 = 107;
		int y3 = 365;
		//查询指定时间 各放映厅放映次数
		String sql = "SELECT DISTINCT(onshow_info.osroom_id),onshow_info.onshow_id,COUNT(selltable_info.onshow_id) AS num ,showroom_info.showroom_id FROM onshow_info ,selltable_info ,showroom_info WHERE onshow_info.onshow_id=selltable_info.onshow_id AND onshow_info.osroom_id=showroom_info.showroom_id AND selltable_info.sell_time LIKE '"+date_now+"%' GROUP BY onshow_info.osroom_id";

		List<Map<String , Object>> saleList = db.query(sql);
		if(saleList.size() > 0){
			for(int i = 0 ; i < saleList.size() ; i++){
				
				/*该月份该放映厅售出的票数*/
				int single = Integer.parseInt(saleList.get(i).get("num").toString());
				String hall_id = saleList.get(i).get("showroom_id").toString();
				String hall_name = db.query("select showroom_name from showroom_info where showroom_id="+hall_id+"").get(0).get("showroom_name").toString();
				
				/*该放映厅的座位数*/
				String allSql = "SELECT sum(showroomseat_list) as num from showroomseat_info  where showroom_id = '"+hall_id+"'group by showroom_id";
				List<Map<String , Object>> allList = db.query(allSql);
				int all = Integer.parseInt(allList.get(0).get("num").toString());
				
				/*该放映厅在该月共有几场电影*/
				String allNumSql = "SELECT COUNT(*) AS num FROM onshow_info WHERE osroom_id = "+hall_id+" AND onshow_date LIKE  '"+date_now+"%'";
				List<Map<String, Object>> allNumList = db.query(allNumSql);
			   int m =Integer.parseInt( allNumList.get(0).get("num").toString());
				
				int percent = single*100/(all*m);
				
				hallLbl = new Label(group, SWT.NONE);
				hallLbl.setBounds(x3, y3, 61, 17);
				hallLbl.setText(hall_name);
				
				progressBar = new ProgressBar(group, SWT.VERTICAL);
				progressBar.setBounds(x2, y2, 17, 268);
				progressBar.setSelection(percent);
				progressBar.setMaximum(100);
				
				percentLbl = new Label(group, SWT.NONE);
				percentLbl.setText(percent+"%");
				percentLbl.setBounds(x1, y1, 61, 17);
				x1 +=80;
				x2 +=80 ;
				x3 +=80 ;
				
			}
		}else{
			group.dispose();
			//label_1.setVisible(true);
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
		return "SeatPercentEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "SeatPercentEditor";
	}
}
