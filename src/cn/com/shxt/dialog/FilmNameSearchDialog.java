package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cn.com.shxt.util.JdbcUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class FilmNameSearchDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private JdbcUtil ju = new JdbcUtil();
	public static String name="";
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FilmNameSearchDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		shell.setSize(909, 413);
		shell.setText("\u7535\u5F71\u6D4F\u89C8");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 36, 859, 349);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(112);
		tableColumn.setText("      \u540D\u79F0");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(87);
		tableColumn_1.setText("    \u56FD\u5BB6");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(91);
		tableColumn_2.setText("     \u5BFC\u6F14");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(115);
		tableColumn_3.setText("       \u4E3B\u6F14");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(82);
		tableColumn_4.setText("    \u65F6\u957F");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(71);
		tableColumn_5.setText("    \u8BED\u8A00");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(96);
		tableColumn_6.setText("      \u7C7B\u578B");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("     \u4E0A\u7EBF\u65F6\u95F4");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("     \u4E0B\u7EBF\u65F6\u95F4");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem [] items=table.getSelection();
				for(TableItem item1:items){
					name =  item1.getText(0);
				}
				shell.dispose();
			}
		});
		menuItem.setText("\u6DFB\u52A0");
		table.removeAll();
		String sql = "select * from film_info";
		List<Map<String,Object>> list = ju.query(sql);
		for (int i = 0; i < list.size(); i++) {
	         TableItem item = new TableItem(table, SWT.NONE);
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
		
	}
}
