package cn.com.shxt.view;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import cn.com.shxt.core.Activator;
import cn.com.shxt.editor.BuyTicketsEditor;
import cn.com.shxt.editor.UserInfoShowEditor;
import cn.com.shxt.editor.WelcomPageEditor;
import cn.com.shxt.input.InputFactory;
import org.eclipse.wb.swt.SWTResourceManager;

public class CinemaSystemMenuView extends ViewPart {

	public static final String ID = "cn.com.shxt.view.CinemaSystemMenuView"; //$NON-NLS-1$
	public CinemaSystemMenuView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final IWorkbenchPage page = this.getViewSite().getPage();
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		
		//找到图片路径
		URL url=Activator.getDefault().getBundle().getResource("image");
		String str="";
		try {
			str=FileLocator.toFileURL(url).toString().substring(6);
		} catch (Exception e2) {					
			e2.printStackTrace();
		}
		
		//直接显示editor
		WelcomPageEditor bte=new WelcomPageEditor();
		try {
			page.openEditor(bte, WelcomPageEditor.ID);
		} catch (PartInitException e2) {
			e2.printStackTrace();
		}
		
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage(str+"11.jpg"));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			/*首页*/
			WelcomPageEditor bte=new WelcomPageEditor();
			public void widgetSelected(SelectionEvent e) {		
				try {
					page.openEditor(bte, WelcomPageEditor.ID);
				} catch (PartInitException e2) {
					e2.printStackTrace();
				}
				/*InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("WelcomPageEditor");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,WelcomPageEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}*/
			}
		});
		btnNewButton.setBounds(60, 51, 142, 78);
		
		Button button = new Button(container, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(str+"zizhu.jpg"));
		button.addSelectionListener(new SelectionAdapter() {
			/*自助购票*/
			//BuyTicketsEditor bte=new BuyTicketsEditor();
			public void widgetSelected(SelectionEvent e) {
				/*try {
					page.openEditor(bte, BuyTicketsEditor.ID);
				} catch (PartInitException e2) {
					e2.printStackTrace();
				}*/
				InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("购票界面");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,BuyTicketsEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(60, 210, 142, 78);
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		button_1.setBackgroundImage(null);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		button_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		button_1.setImage(SWTResourceManager.getImage(str+"gerenxinxi.jpg"));
		button_1.addSelectionListener(new SelectionAdapter() {
			/*个人信息显示*/
			UserInfoShowEditor sse = new UserInfoShowEditor();
			public void widgetSelected(SelectionEvent e) {
				try {
					page.openEditor(sse, UserInfoShowEditor.ID);
					
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(60, 365, 142, 78);

		createActions();
		initializeToolBar();
		initializeMenu();	
		
		
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
   private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
