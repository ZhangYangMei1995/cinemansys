package cn.com.shxt.core;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import cn.com.shxt.dialog.LoginDialog;
import cn.com.shxt.view.CinemaManagermentMenuView;
import cn.com.shxt.view.CinemaSystemMenuView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		if(LoginDialog.role.equals("用户")){
			layout.addView(CinemaSystemMenuView.ID, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		}else if(LoginDialog.role.equals("管理员")||LoginDialog.role.equals("维护员")){			
			layout.addView(CinemaManagermentMenuView.ID, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		}
	
	}
}
