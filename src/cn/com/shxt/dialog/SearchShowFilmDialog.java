package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cn.com.shxt.util.JdbcUtil;

public class SearchShowFilmDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private int [] film_id ; 
	private int [] onshow_id ;
	private Table table;
	private JdbcUtil ju = new JdbcUtil();
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SearchShowFilmDialog(Shell parent, int style) {
		super(parent, style);
		setText("影厅放映计划");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(int [] film_id , int [] onshow_id) {
		this.film_id = film_id ;
		this.onshow_id = onshow_id ;
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(465, 336);
		shell.setText(getText());
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(27, 32, 408, 244);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u7535\u5F71\u540D\u79F0");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u653E\u6620\u65E5\u671F");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u5F00\u59CB\u65F6\u95F4");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u7ED3\u675F\u65F6\u95F4");
		table.removeAll();
		for(int i = 0 ; i < film_id.length ; i ++){
			String sql = "select * from film_info where film_id = "+film_id[i]+"";
			List<Map<String , Object >> list = ju.query(sql);
			String sql_1 = "select * from onshow_info where onshow_id = "+onshow_id[i]+"";
			List<Map<String , Object >> list_1 = ju.query(sql_1);
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String []{
					list.get(0).get("film_name").toString() , 
					list_1.get(0).get("onshow_date").toString() , 
					list_1.get(0).get("filmstart_time").toString() , 
					list_1.get(0).get("filmend_time").toString() 
			});
		}
		
		
	}
}
