package cn.com.shxt.editor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import cn.com.shxt.util.DataCompare;
import cn.com.shxt.util.JdbcUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class MoviePercentEditor extends EditorPart implements IEditorInput {

	public static final String ID = "cn.com.shxt.editor.MoviePercentEditor"; //$NON-NLS-1$
	private SimpleDateFormat de = new SimpleDateFormat("yyyy-MM-dd");
	private Date da = new Date();
	private String today  = de.format(da);
	private String start_date;
	private String end_date;
	private DataCompare compare = new DataCompare();
	private long subTime;
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private ProgressBar progressBar;
	private MessageBox box;
	private JdbcUtil db = new JdbcUtil();
	private int all_count;//一部影片被放映所在影厅的总座位
	private Group group;
	private Label label_2;
	public MoviePercentEditor() {
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
		label.setBounds(31, 35, 61, 17);
		label.setText("\u8D77\u59CB\u65F6\u95F4\uFF1A");
		
		 label_2 = new Label(container, SWT.NONE);
			label_2.setText("*\u5728\u6B64\u65F6\u95F4\u672A\u6709\u552E\u51FA\u7EAA\u5F55");
			label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			label_2.setBounds(31, 65, 183, 27);
			
			group = new Group(container, SWT.NONE);
			group.setText("\u5F71\u7247\u4E0A\u5EA7\u7387");
			group.setBounds(31, 98, 837, 829);
			group.setVisible(false);
			
		final DateTime dateTime = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		dateTime.setBackgroundMode(SWT.INHERIT_DEFAULT);
		dateTime.setBounds(98, 35, 119, 24);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(277, 35, 61, 17);
		label_1.setText("\u622A\u6B62\u65F6\u95F4\uFF1A");
		
		final DateTime dateTime_1 = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime_1.setBounds(344, 35, 119, 24);
		

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/**
			 * 按时间搜索
			 * */
			public void widgetSelected(SelectionEvent e) {
				
				
				changeTime(dateTime,dateTime_1);
				if(subTime < 0){
					box = new MessageBox(container.getShell(),SWT.ICON_ERROR);
					box.setText("错误信息");
					box.setMessage("请注意查询时间！！");
					box.open();
				}else{
					percent(container);
				}
			
				
				String [] today_again = today.split(" ")[0].split("-");
				dateTime.setDate(Integer.parseInt(today_again[0]), Integer.parseInt(today_again[1])-1, Integer.parseInt(today_again[2]));
				dateTime_1.setDate(Integer.parseInt(today_again[0]), Integer.parseInt(today_again[1])-1, Integer.parseInt(today_again[2]));
			}
		});
		button.setBounds(488, 35, 80, 27);
		button.setText("\u641C\u7D22");

	}
	
	/*判断时间段是否正确*/
	public void changeTime(DateTime dateTime,DateTime dateTime_1){
		/**
		 * 获取开始结束时间
		 * */
		String start = dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
		String end = dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-"+dateTime_1.getDay();
		
		try {
			Date d = de.parse(start);
			start_date = de.format(d);
			
			Date d1 = de.parse(end);
			end_date = de.format(d1);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		subTime = compare.Sub(start_date,end_date);

	}
	
	public void percent(Composite container){
		
		int x1 = 77;
		int x2 = 88;
		int x3 = 77;
		int y1 = 53;
		int y2 = 107;
		int y3 = 365;
		
		//查询卖票数最多的五部影片
		String sql = "SELECT onshow_info.osfilm_id ,COUNT(selltable_info.onshow_id) AS num ,film_info.film_name FROM onshow_info ,selltable_info ,film_info WHERE onshow_info.onshow_id = selltable_info.onshow_id AND onshow_info.osfilm_id = film_info.film_id AND selltable_info.sell_time BETWEEN '"+start_date+"' AND '"+end_date+"' GROUP BY onshow_info.osfilm_id ORDER BY num DESC LIMIT 0,5 ";
		List<Map<String, Object>> list = db.query(sql);
		
		if(list.size() > 0){
			label_2.setVisible(false);			
		//	group.setVisible(true);为什么这句不行
			group = new Group(container, SWT.NONE);
			group.setText("\u5F71\u7247\u4E0A\u5EA7\u7387");
			group.setBounds(31, 98, 837, 829);
			
			for(int i = 0 ; i < list.size() ; i++){
				/*该时间段该电影售出的票数(从大到小)*/
				int single = Integer.parseInt(list.get(i).get("num").toString());
				String movie_name = list.get(i).get("film_name").toString();
				int movie_id = Integer.parseInt(list.get(i).get("osfilm_id").toString());
				
				/*该时间段该电影占用的放映厅座位总数*/
				String hallIdSql ="SELECT onshow_info.onshow_id,onshow_info.osroom_id   FROM onshow_info ,selltable_info  WHERE onshow_info.osfilm_id = "+movie_id+" AND selltable_info.sell_time BETWEEN '"+start_date+"' AND '"+end_date+"'   AND selltable_info.onshow_id=onshow_info.onshow_id" ;
				List<Map<String,Object>> hallIdList = db.query(hallIdSql);
				all_count = 0;
				for (int k  = 0 ; k < hallIdList.size() ; k ++){
					int hall_id = Integer.parseInt(hallIdList.get(k).get("osroom_id").toString());
					String hallSql = "SELECT showroom_num as all_count FROM showroom_info where  showroom_id = '"+hall_id+"' ";
					List<Map<String, Object>> hallNumList = db.query(hallSql);
					int seat_count = Integer.parseInt(hallNumList.get(0).get("all_count").toString());
					all_count = all_count + seat_count;
				}
				if(all_count!=0){
					int percent = single*100/all_count;
					int percent1 = (single*100)%all_count;
					lblNewLabel = new Label(group, SWT.NONE);
					lblNewLabel.setBounds(x3, y3, 61, 17);
					lblNewLabel.setText(movie_name);
					
					progressBar = new ProgressBar(group, SWT.VERTICAL);
					progressBar.setBounds(x2, y2, 17, 268);
					progressBar.setSelection(percent);
					progressBar.setMaximum(100);
					
					lblNewLabel_1 = new Label(group, SWT.NONE);
					lblNewLabel_1.setBounds(x1, y1, 61, 17);
					lblNewLabel_1.setText(percent+"."+percent1+"%");
					
					x1 +=100;
					x2 +=100 ;
					x3 +=100 ;
				}
			}
			
		}else{
			label_2.setVisible(true);
			group.dispose();
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
		return "MoviePercentEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "MoviePercentEditor";
	}

}