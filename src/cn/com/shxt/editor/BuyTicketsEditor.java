package cn.com.shxt.editor;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.core.Activator;
import cn.com.shxt.dialog.BuyTicketsDialog1;
import cn.com.shxt.util.JdbcUtil;

public class BuyTicketsEditor extends EditorPart implements IEditorInput {
	

	public static final String ID = "cn.com.shxt.editor.BuyTicketsEditor"; //$NON-NLS-1$
	private JdbcUtil ju=new JdbcUtil();
	private TabItem tabItem_1;
	public BuyTicketsEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBounds(0, 0, 1000, 800);
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		tabFolder.setBackgroundMode(SWT.INHERIT_DEFAULT);
		tabFolder.setBounds(0, 10, 1046, 713);
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u6B63\u5728\u70ED\u6620\u7CBE\u5F69\u5F71\u7247");
		
		Group group = new Group(tabFolder, SWT.NONE);
		group.setBackgroundMode(SWT.INHERIT_DEFAULT);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		group.setText("\u6B63\u5728\u70ED\u6620");
		group.setBounds(0, 0, 1000, 800);
		tabItem.setControl(group);
		
		DateTime dateTime = new DateTime(group, SWT.BORDER);
		dateTime.setBounds(70, 10, 93, 24);
		
	
		tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u660E\u5929\u4E0A\u6620\u7CBE\u5F69\u5F71\u7247");
		
		Group group_1 = new Group(tabFolder, SWT.NONE);
		group_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		group_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		group_1.setText("\u5373\u5C06\u4E0A\u6620\u7CBE\u5F69\u5F71\u7247");
		group_1.setBounds(0, 0, 1000, 800);
		tabItem_1.setControl(group_1);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("\u540E\u5929\u4E0A\u6620\u7CBE\u5F69\u5F71\u7247");
		
		Group group_2 = new Group(tabFolder, SWT.NONE);
		group_2.setBackgroundMode(SWT.INHERIT_DEFAULT);
		group_2.setText(" \u7CBE\u5F69\u5F71\u7247\u9884\u544A");
		tbtmNewItem.setControl(group_2);
		group_2.setBounds(0, 0, 1000, 800);
		group_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));

		//当天即将热映
		Date date=new Date();
		String now=date.toLocaleString().split(" ")[0];
		String now_now = date.toLocaleString().split(" ")[1];
        String sql="select * from onshow_info where onshow_date='"+now+"' and filmstart_time > '"+now_now+"'";
        List<Map<String,Object>>list=ju.query(sql);
        int label_x=48;
		int label_y=45;
		int label1_x=48;
		int label1_y=217;
	    int button_x=200;
		int button_y=280;
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				String sql1="select * from film_info where film_id="+list.get(i).get("osfilm_id")+"";
				List<Map<String,Object>>list1=ju.query(sql1);
				//获取影片信息
				String image=list1.get(0).get("film_image").toString();
				final String name=list1.get(0).get("film_name").toString();
				String director=list1.get(0).get("film_director").toString();
				String protagonist=list1.get(0).get("film_protagonist").toString();
				String language = list1.get(0).get("film_language").toString();

				final String start_time=list.get(i).get("filmstart_time").toString();
				final String end_time=list.get(i).get("filmend_time").toString();
				Label lblNewLabel_1 = new Label(group, SWT.BORDER);
				lblNewLabel_1.setBounds(label_x, label_y, 150, 160);
				lblNewLabel_1.setImage(SWTResourceManager.getImage(getIcorns()+image));
				
				Label lblNewLabel_3 = new Label(group, SWT.NONE);
				lblNewLabel_3.setBounds(label1_x, label1_y, 160, 100);
				lblNewLabel_3.setText("名称："+name+"\n"+"导演："+director+"\n"+"主演:"+protagonist+"\n"+"语言："+language+"\n"+"开始时间："+start_time+"\n"+"结束时间："+end_time);
				 //放映编号
				final int onshow_id = Integer.parseInt(list.get(i).get("onshow_id").toString());
				Button button = new Button(group, SWT.NONE);
				button.setBounds(button_x, button_y, 50, 27);
				button.setText("\u8D2D\u7968");
				button.addSelectionListener(new SelectionAdapter() {
					/*购票 */
					public void widgetSelected(SelectionEvent e) {			
						BuyTicketsDialog1 btd = new BuyTicketsDialog1(container.getShell(),SWT.NONE);
						btd.open(name,start_time,end_time,onshow_id);
					}
				});
				if((i+1)%3==0){//换行。x不变，y变
					label_x=48;
					label1_x=48;
					button_x=107;
					label_y+=300;
					label1_y+=300;
					button_y+=300;
				}else{//同行，x变
					label_x+=280 ;
					label1_x+=280;
					button_x+=280;
				}
		}
		
		}
		
		
		//明天即将上映的电影
		label_x=48;
		label_y=45;
		label1_x=48;
		label1_y=217;
	    button_x=200;
		button_y=280;
		//把时间转化成毫秒，在转化成时间
		Date date_1 = new Date ();
		long forward = (date_1.getTime()+24*3600*1000);
		date_1.setTime(forward);
        @SuppressWarnings("deprecation")
		String sql_1="select * from onshow_info where onshow_date='"+date_1.toLocaleString().split(" ")[0]+"'";
        List<Map<String,Object>>list_1=ju.query(sql_1);
		
		if(list_1.size()!=0){
			for(int i=0;i<list_1.size();i++){
				String sql1="select * from film_info where film_id="+list_1.get(i).get("osfilm_id")+"";
				List<Map<String,Object>>list1=ju.query(sql1);
				//获取影片信息
				String image=list1.get(0).get("film_image").toString();
				final String name=list1.get(0).get("film_name").toString();
				String director=list1.get(0).get("film_director").toString();
				String protagonist=list1.get(0).get("film_protagonist").toString();
				String language = list1.get(0).get("film_language").toString();

				final String start_time=list_1.get(i).get("filmstart_time").toString();
				final String end_time=list_1.get(i).get("filmend_time").toString();
				Label lblNewLabel_1 = new Label(group_1, SWT.BORDER);
				lblNewLabel_1.setBounds(label_x, label_y, 150, 160);
				lblNewLabel_1.setImage(SWTResourceManager.getImage(getIcorns()+image));
				
				Label lblNewLabel_3 = new Label(group_1, SWT.NONE);
				lblNewLabel_3.setBounds(label1_x, label1_y, 140, 100);
				lblNewLabel_3.setText("名称："+name+"\n"+"导演："+director+"\n"+"主演:"+protagonist+"\n"+"语言："+language+"\n"+"开始时间："+start_time+"\n"+"结束时间："+end_time);
				lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
				 //放映编号
				final int onshow_id = Integer.parseInt(list_1.get(i).get("onshow_id").toString());
				Button button = new Button(group_1, SWT.NONE);
				button.setBounds(button_x, button_y, 50, 27);
				button.setText("\u8D2D\u7968");
				button.addSelectionListener(new SelectionAdapter() {
					/*购票 */
					public void widgetSelected(SelectionEvent e) {			
						BuyTicketsDialog1 btd = new BuyTicketsDialog1(container.getShell(),SWT.NONE);
						btd.open(name,start_time,end_time,onshow_id);
					}
				});
				if((i+1)%3==0){//换行。x不变，y变
					label_x=48;
					label1_x=48;
					button_x=107;
					label_y+=300;
					label1_y+=300;
					button_y+=300;
				}else{//同行，x变
					label_x+=280 ;
					label1_x+=280;
					button_x+=280;
				}
		}
		
			
			//后天上映预告
			label_x=48;
			label_y=45;
			label1_x=48;
			label1_y=217;
		    button_x=200;
			button_y=280;
			//把时间转化成毫秒，在转化成时间
			Date date_11 = new Date ();
			long forward1 = (date_11.getTime()+48*3600*1000);
			date_11.setTime(forward1);
	        @SuppressWarnings("deprecation")
			String sql_11="select * from onshow_info where onshow_date='"+date_11.toLocaleString().split(" ")[0]+"'";
	        List<Map<String,Object>>list_11=ju.query(sql_11);
			
			if(list_11.size()!=0){
				for(int i=0;i<list_11.size();i++){
					String sql1="select * from film_info where film_id="+list_11.get(i).get("osfilm_id")+"";
					List<Map<String,Object>>list1=ju.query(sql1);
					//获取影片信息
					String image=list1.get(0).get("film_image").toString();
					final String name=list1.get(0).get("film_name").toString();
					String director=list1.get(0).get("film_director").toString();
					String protagonist=list1.get(0).get("film_protagonist").toString();
					String language = list1.get(0).get("film_language").toString();

					final String start_time=list_11.get(i).get("filmstart_time").toString();
					final String end_time=list_11.get(i).get("filmend_time").toString();
					Label lblNewLabel_1 = new Label(group_2, SWT.BORDER);
					lblNewLabel_1.setBounds(label_x, label_y, 150, 160);
					lblNewLabel_1.setImage(SWTResourceManager.getImage(getIcorns()+image));
					
					Label lblNewLabel_3 = new Label(group_2, SWT.NONE);
					lblNewLabel_3.setBounds(label1_x, label1_y, 128, 100);
					lblNewLabel_3.setText("名称："+name+"\n"+"导演："+director+"\n"+"主演:"+protagonist+"\n"+"语言："+language+"\n"+"开始时间："+start_time+"\n"+"结束时间："+end_time);
					lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));

					 //放映编号
					final int onshow_id = Integer.parseInt(list_11.get(i).get("onshow_id").toString());
					Button button = new Button(group_2, SWT.NONE);
					button.setBounds(button_x, button_y, 50, 27);
					button.setText("\u8D2D\u7968");
					button.addSelectionListener(new SelectionAdapter() {
						/*购票 */
						public void widgetSelected(SelectionEvent e) {			
							BuyTicketsDialog1 btd = new BuyTicketsDialog1(container.getShell(),SWT.NONE);
							btd.open(name,start_time,end_time,onshow_id);
						}
					});
					if((i+1)%3==0){//换行。x不变，y变
						label_x=48;
						label1_x=48;
						button_x=107;
						label_y+=300;
						label1_y+=300;
						button_y+=300;
					}else{//同行，x变
						label_x+=280 ;
						label1_x+=280;
						button_x+=280;
					}
			}
			}
		}
		
	}

	public String getIcorns(){
		String str = "";
		URL url=Activator.getDefault().getBundle().getResource("icons");
		try {
			str=FileLocator.toFileURL(url).toString().substring(6);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return str;
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
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return "BuyTicketsEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "BuyTicketsEditortool";
	}
}
