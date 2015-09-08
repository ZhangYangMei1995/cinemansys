package cn.com.shxt.core;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import cn.com.shxt.action.OpenCiManMeViewAction;
import cn.com.shxt.action.OpenCiSysMeViewAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
	private OpenCiManMeViewAction ocma;
	private OpenCiSysMeViewAction ocsa;
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
    
    
    protected void makeActions(IWorkbenchWindow window) {
    	ocma=new OpenCiManMeViewAction(window);
    	ocma.setText("��ӰԺ����");
    	ocsa=new OpenCiSysMeViewAction(window);
    	ocsa.setText("��ӰԺ��Ʊ");
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	IMenuManager menu=new MenuManager("�˵�");
		menu.add(ocsa);
    	menuBar.add(menu);

    }
    
}
