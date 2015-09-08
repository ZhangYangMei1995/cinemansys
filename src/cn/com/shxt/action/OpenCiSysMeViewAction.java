package cn.com.shxt.action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import cn.com.shxt.view.CinemaSystemMenuView;

public class OpenCiSysMeViewAction extends Action {
	private IWorkbenchWindow window;

	
	public OpenCiSysMeViewAction(IWorkbenchWindow window) {
		super();
		this.window = window;
	}

	@Override
	public void run() {
		try {
			window.getActivePage().showView(CinemaSystemMenuView.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
