package cn.com.shxt.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.editor.AddFilmInfoEditor;
import cn.com.shxt.editor.AddManagerInfoEditor;
import cn.com.shxt.editor.AddOnShowInfoEditor;
import cn.com.shxt.editor.AddShowRoomEditor;
import cn.com.shxt.editor.AddUserInfoEditor;
import cn.com.shxt.editor.MoviePercentEditor;
import cn.com.shxt.editor.QueryFilmInfoEditor;
import cn.com.shxt.editor.QueryOnShowInfoEditor;
import cn.com.shxt.editor.QueryShowRoomEditor;
import cn.com.shxt.editor.QueryUserManagerInfoEditor;
import cn.com.shxt.editor.SalePercentEditor;
import cn.com.shxt.editor.SaleSituationEditor;
import cn.com.shxt.editor.SeatPercentEditor;
import cn.com.shxt.editor.WelcomPageEditor;


public class CinemaManagermentMenuView extends ViewPart {
	public static final String ID = "cn.com.shxt.view.CinemaManagermentMenuView"; //$NON-NLS-1$
	public CinemaManagermentMenuView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		//直接显示欢迎界面
		final IWorkbenchPage page = this.getViewSite().getPage();
		WelcomPageEditor bte=new WelcomPageEditor();
		try {
			page.openEditor(bte, WelcomPageEditor.ID);
		} catch (PartInitException e2) {
			e2.printStackTrace();
		}
		Composite container = new Composite(parent, SWT.NONE);		
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		container.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
		ExpandBar expandBar = new ExpandBar(container, SWT.BORDER | SWT.V_SCROLL);
		expandBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		expandBar.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		expandBar.setBounds(26, 10, 195, 543);
		
		ExpandItem expandItem = new ExpandItem(expandBar, SWT.NONE);
		expandItem.setExpanded(true);
		expandItem.setText("\u7528\u6237\u7BA1\u7406");
		
		Composite composite = new Composite(expandBar, SWT.NONE);
		expandItem.setControl(composite);
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		button.addSelectionListener(new SelectionAdapter() {
			AddManagerInfoEditor amie = new AddManagerInfoEditor();
			/*管理员信息添加*/
			public void widgetSelected(SelectionEvent e) {
				try {
					page.openEditor(amie, AddManagerInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(23, 0, 129, 27);
		button.setText("\u5458\u5DE5\u6CE8\u518C");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		button_1.addSelectionListener(new SelectionAdapter() {
			/*客户注册*/
			AddUserInfoEditor auie = new AddUserInfoEditor();
			public void widgetSelected(SelectionEvent e) {
				try {
					page.openEditor(auie, AddUserInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				/*InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("管理员信息添加");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,AddUserInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}*/
			}
		});
		button_1.setBounds(23, 27, 129, 27);
		button_1.setText("\u5BA2\u6237\u6CE8\u518C");
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button_2.addSelectionListener(new SelectionAdapter() {
			/*个人信息查询*/
			QueryUserManagerInfoEditor qum=new QueryUserManagerInfoEditor();
			public void widgetSelected(SelectionEvent e) {
				try {
					page.openEditor(qum, QueryUserManagerInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				/*InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("管理员信息添加");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,QueryUserManagerInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}*/
				
			}
		});
		button_2.setBounds(23, 52, 129, 27);
		button_2.setText("\u4E2A\u4EBA\u4FE1\u606F\u67E5\u8BE2");
		expandItem.setHeight(80);
		
		ExpandItem expandItem_1 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_1.setExpanded(true);
		expandItem_1.setText("\u5F71\u7247\u7BA1\u7406");
		
		Composite composite_1 = new Composite(expandBar, SWT.NONE);
		expandItem_1.setControl(composite_1);
		
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			/*添加新电影*/
			AddFilmInfoEditor afie=new AddFilmInfoEditor();
			public void widgetSelected(SelectionEvent e) {
				try {
					page.openEditor(afie, AddFilmInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				/*InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("电影信息添加");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,AddFilmInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}*/
				
			}
		});
		btnNewButton_1.setBounds(23, 0, 127, 27);
		btnNewButton_1.setText("\u7535\u5F71\u5B58\u50A8");
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		btnNewButton.setBounds(23, 32, 127, 27);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			/*电影库存*/
			QueryFilmInfoEditor qfi = new QueryFilmInfoEditor();
			public void widgetSelected(SelectionEvent e) {
				try {
					page.openEditor(qfi, QueryFilmInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				/*InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("电影信息添加");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,QueryFilmInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}*/
				
			}
		});
		btnNewButton.setText("\u7535\u5F71\u5E93\u5B58");
		expandItem_1.setHeight(60);
		
		ExpandItem expandItem_2 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_2.setExpanded(true);
		expandItem_2.setText("\u5F71\u5385\u7BA1\u7406");
		
		Composite composite_2 = new Composite(expandBar, SWT.NONE);
		expandItem_2.setControl(composite_2);
		
		Button button_5 = new Button(composite_2, SWT.NONE);
		button_5.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		button_5.addSelectionListener(new SelectionAdapter() {
			/*影厅添加*/
			AddShowRoomEditor asr = new AddShowRoomEditor();
			public void widgetSelected(SelectionEvent e) {
				try {
					page.openEditor(asr, AddShowRoomEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				/*InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("影厅添加");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,AddShowRoomEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}		*/		
			}
		});
		button_5.setBounds(23, 0, 127, 27);
		button_5.setText("\u5F71\u5385\u6DFB\u52A0");
		
		Button button_7 = new Button(composite_2, SWT.NONE);
		button_7.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button_7.addSelectionListener(new SelectionAdapter() {
			/*影厅修改*/
			QueryShowRoomEditor qsr = new QueryShowRoomEditor();
			public void widgetSelected(SelectionEvent e) {
				try {
					page.openEditor(qsr, QueryShowRoomEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				/*InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("影厅信息修改");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,QueryShowRoomEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}	*/
				
			}
		});
		button_7.setBounds(23, 29, 127, 27);
		button_7.setText("\u5F71\u5385\u67E5\u8BE2");
		expandItem_2.setHeight(60);
		
		ExpandItem expandItem_3 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_3.setExpanded(true);
		expandItem_3.setText("\u5F71\u7247\u4E0A\u6620\u8BA1\u5212");
		
		Composite composite_4 = new Composite(expandBar, SWT.NONE);
		expandItem_3.setControl(composite_4);
		
		Button button_6 = new Button(composite_4, SWT.NONE);
		button_6.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button_6.addSelectionListener(new SelectionAdapter() {
			/*影片上映*/
			AddOnShowInfoEditor aos = new AddOnShowInfoEditor();
			public void widgetSelected(SelectionEvent e) {
				try {
					page.openEditor(aos, AddOnShowInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				/*InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("影片上映");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,AddOnShowInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}		*/			
			}
		});
		button_6.setBounds(23, 0, 127, 27);
		button_6.setText("\u5F71\u7247\u4E0A\u6620");
		
		Button button_3 = new Button(composite_4, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button_3.addSelectionListener(new SelectionAdapter() {
			/*上映计划查询*/
			QueryOnShowInfoEditor qos = new QueryOnShowInfoEditor();
			public void widgetSelected(SelectionEvent e) {		
				try {
					page.openEditor(qos, QueryOnShowInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				/*InputFactory input=new InputFactory();
				input.setName("name");
				input.setToolTipText("影片上映");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input,QueryOnShowInfoEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}*/
			}
		});
		button_3.setBounds(23, 33, 127, 27);
		button_3.setText("\u4E0A\u6620\u8BA1\u5212\u67E5\u8BE2");
		expandItem_3.setHeight(60);
		
		ExpandItem expandItem_4 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_4.setExpanded(true);
		expandItem_4.setText("\u7EDF\u8BA1\u6A21\u5757");
		
		Composite composite_5 = new Composite(expandBar, SWT.NONE);
		expandItem_4.setControl(composite_5);
		
		Button button_8 = new Button(composite_5, SWT.NONE);
		button_8.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button_8.addSelectionListener(new SelectionAdapter() {
		/*销售明细*/
			public void widgetSelected(SelectionEvent e) {
				SaleSituationEditor sse = new SaleSituationEditor();
				try {
					page.openEditor(sse, SaleSituationEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_8.setText("\u9500\u552E\u67E5\u8BE2");
		button_8.setBounds(26, 0, 127, 27);
		
		Button button_9 = new Button(composite_5, SWT.NONE);
		button_9.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button_9.addSelectionListener(new SelectionAdapter() {
			/*影厅上座率*/
			public void widgetSelected(SelectionEvent e) {
				SeatPercentEditor srr = new SeatPercentEditor();
				try {
					page.openEditor(srr, SeatPercentEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button_9.setText("\u5F71\u5385\u4E0A\u5EA7\u7387");
		button_9.setBounds(26, 27, 127, 27);
		
		Button button_10 = new Button(composite_5, SWT.NONE);
		button_10.setFont(SWTResourceManager.getFont("新宋体", 9, SWT.BOLD));
		button_10.addSelectionListener(new SelectionAdapter() {
		/*影片上座率*/
			public void widgetSelected(SelectionEvent e) {
				MoviePercentEditor fre = new MoviePercentEditor();
				try {
					page.openEditor(fre, MoviePercentEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button_10.setText("\u5F71\u7247\u4E0A\u5EA7\u7387");
		button_10.setBounds(26, 54, 127, 27);
		
		Button button_4 = new Button(composite_5, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			//全年销售情况
			public void widgetSelected(SelectionEvent e) {
				SalePercentEditor spe = new SalePercentEditor();
				try {
					page.openEditor(spe, SalePercentEditor.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_4.setBounds(26, 78, 127, 27);
		button_4.setText("\u5168\u5E74\u9500\u552E\u60C5\u51B5");
		expandItem_4.setHeight(110);

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
