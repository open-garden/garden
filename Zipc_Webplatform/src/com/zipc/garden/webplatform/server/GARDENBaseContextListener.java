package com.zipc.garden.webplatform.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zipc.garden.core.EditManager;
import com.zipc.garden.webplatform.dao.DAOUtils;

/**
 * This class is start or end webApp. contextInitialized start one time this webApp is started. contextDestroyed end one time
 * this webApp is finished.
 */
public class GARDENBaseContextListener implements ServletContextListener {

    /**
     * You will be notified that the web application initialization process has started.<br>
     * An instance of EditManager is created.
     * @param arg0 ServletContextEvent containing the initialized ServletContext
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        EditManager.createInstance();
    }

    /**
     * You will be notified that the ServletContext is about to shut down.<br>
     * Close the session factory.
     * @param arg0 ServletContextEvent containing the destroyed ServletContext
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        DAOUtils.closeSessionFactory();
    }

}
