package com.zipc.garden.core;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;

import com.zipc.garden.model.arc.ARCPackage;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bps.BPSPackage;
import com.zipc.garden.model.cb.CBPackage;
import com.zipc.garden.model.cgen.CGENPackage;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.csc.CSCPackage;
import com.zipc.garden.model.cscs.CSCSPackage;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fmc.FMCPackage;
import com.zipc.garden.model.fmcs.FMCSPackage;
import com.zipc.garden.model.fsm.FSMPackage;
import com.zipc.garden.model.lgen.LGENPackage;
import com.zipc.garden.model.lsc.LSCPackage;
import com.zipc.garden.model.scd.SCDPackage;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scs.SCSPackage;
import com.zipc.garden.model.spql.SPQLPackage;
import com.zipc.garden.model.tc.TCPackage;
import com.zipc.garden.model.tp.TPPackage;
import com.zipc.garden.model.tps.TPSPackage;

/**
 * The history of commands operated on the screen is managed.
 */
public class EditManager {

    /** Initialization flag of EditManager class */
    private static boolean initialized;

    /** Command stack that can be undone */
    private BasicCommandStack commandStack;

    /** The number of times the command has been executed */
    private int stackCount;

    /** The location where the command was saved */
    private int savedPosition;

    /**
     * constructor.
     */
    private EditManager() {
        commandStack = new BasicCommandStack();
        stackCount = 0;
        savedPosition = 0;
    }

    /**
     * Register the created EMF class in "Resource.Factory" and "EPackage".
     * @return
     */
    public static synchronized EditManager createInstance() {
        if (!initialized) {
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("arc", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bp", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bps", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("cb", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("cgen", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("core", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("csc", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("cscs", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fm", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fmc", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fmcs", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fsm", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lgen", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lsc", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("scd", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("scs", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("spql", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("tc", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("tp", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("tps", new ZGResourceFactory());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("scenario", new ZGResourceFactory());

            EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(ARCPackage.eNS_URI, ARCPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(BPPackage.eNS_URI, BPPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(BPSPackage.eNS_URI, BPSPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(CBPackage.eNS_URI, CBPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(CGENPackage.eNS_URI, CGENPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(COREPackage.eNS_URI, COREPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(CSCPackage.eNS_URI, CSCPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(CSCSPackage.eNS_URI, CSCSPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(FMPackage.eNS_URI, FMPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(FMCPackage.eNS_URI, FMCPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(FMCSPackage.eNS_URI, FMCSPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(FSMPackage.eNS_URI, FSMPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(LGENPackage.eNS_URI, LGENPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(LSCPackage.eNS_URI, LSCPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(SCDPackage.eNS_URI, SCDPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(SCSPackage.eNS_URI, SCSPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(SPQLPackage.eNS_URI, SPQLPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(TCPackage.eNS_URI, TCPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(TPPackage.eNS_URI, TPPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(TPSPackage.eNS_URI, TPSPackage.eINSTANCE);
            EPackage.Registry.INSTANCE.put(ScenarioPackage.eNS_URI, ScenarioPackage.eINSTANCE);
        }

        return new EditManager();
    }

    /**
     * Execute a command.
     * @param command
     */
    public void execute(Command command) {
        commandStack.execute(command);
        if (stackCount < savedPosition) {
            savedPosition = -1;
        }
        stackCount++;
    }

    /**
     * Undo the operation you performed
     */
    public void undo() {
        if (commandStack.canUndo()) {
            commandStack.undo();
            stackCount--;
        }
    }

    /**
     * Redo the operation once canceled with the Undo function
     */
    public void redo() {
        if (commandStack.canRedo()) {
            commandStack.redo();
            stackCount++;
        }
    }

    /**
     * Gets the number of executed commands.
     * @return
     */
    public int getStackCoount() {
        return stackCount;
    }

    /**
     * The command execution position is set.
     * @param savedPosition
     */
    public void setSavedPosition(int savedPosition) {
        this.savedPosition = savedPosition;
    }

    /**
     * Get the location where the executed command is saved
     * @return
     */
    public int getSavedPosition() {
        return savedPosition;
    }
}
