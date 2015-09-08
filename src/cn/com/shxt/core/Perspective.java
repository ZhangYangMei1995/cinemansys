package cn.com.shxt.core;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import cn.com.shxt.dialog.LoginDialog;
import cn.com.shxt.view.CinemaManagermentMenuView;
import cn.com.shxt.view.CinemaSystemMenuView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		if(LoginDialog.role.equals("�û�")){
			layout.addView(CinemaSystemMenuView.ID, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		}else if(LoginDialog.role.equals("����Ա")||LoginDialog.role.equals("ά��Ա")){			
			layout.addView(CinemaManagermentMenuView.ID, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		}
	
	}
}
