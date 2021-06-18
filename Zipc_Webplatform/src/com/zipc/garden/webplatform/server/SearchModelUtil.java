package com.zipc.garden.webplatform.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.SearchResult;

/**
 * This class performs the model search process of Project Explorer.
 */
public class SearchModelUtil {

    /**
     * Searches the file based on the specified keyword and gets the result.
     * @param data File list to search
     * @param keyWord Keywords used for fuzzy search of FM node name (or FSMState name)
     * @return search results
     */
    public List<SearchResult> createResultListToSearchViewData(List<File> data, String keyWord) {
        List<SearchResult> ret = new ArrayList<SearchResult>();

        for (File file : data) {
            if (file.getExtension().equals(Extension.FM.getValue())) {
                FMRoot fmRoot = (FMRoot) EditResourceUtil.INSTANCE.convertToRootElement(file.getContent());
                ret.addAll(searchFMModel(fmRoot, file, keyWord));
            } else if (file.getExtension().equals(Extension.FSM.getValue())) {
                FSMDStateMachine machine = (FSMDStateMachine) EditResourceUtil.INSTANCE.convertToRootElement(file.getContent());
                ret.addAll(searchFSMModel(machine, file, keyWord));
            }
        }

        return ret;
    }

    /**
     * Searches FSM files based on the specified keywords and gets the results.
     * @param machine FSM file contents
     * @param file FSM file
     * @param keyWord Keywords used for fuzzy search of FSMState name
     * @return search results
     */
    private List<SearchResult> searchFSMModel(FSMDStateMachine machine, File file, String keyWord) {
        List<SearchResult> ret = new ArrayList<SearchResult>();
        FSMDRegion region = machine.getRegions().get(0);
        Iterator<FSMDState> it = region.getStates().iterator();
        while (it.hasNext()) {
            FSMDState state = it.next();
            if (state.getName().indexOf(keyWord) != -1) {
                ret.add(new SearchResult(file.getId(), state.getName(), file.getFullPath(), file.getExtension(), convertToBinary(EcoreUtil.copy(state))));
            }
        }
        return ret;
    }

    /**
     * Searches FM files based on the specified keywords and gets the results.
     * @param fmRoot FM file contents
     * @param file FM file
     * @param keyWord Keywords used for fuzzy search of FM node name
     * @return search results
     */
    private List<SearchResult> searchFMModel(FMRoot fmRoot, File file, String keyWord) {
        List<SearchResult> ret = new ArrayList<SearchResult>();
        if (fmRoot != null && fmRoot.getNode() != null) {
            if (fmRoot.getNode().getName().indexOf(keyWord) != -1) {
                ret.add(new SearchResult(file.getId(), fmRoot.getNode().getName(), file.getFullPath(), file.getExtension(), convertToBinary(EcoreUtil.copy(fmRoot.getNode()))));
            }
            if (fmRoot.getNode().getChildren() != null && !fmRoot.getNode().getChildren().isEmpty()) {
                fmRoot.getNode().getChildren().forEach(childNode -> parseFMNode(ret, file, childNode, fmRoot.getNode().getName(), keyWord));
            }
        }
        return ret;
    }

    /**
     * Child nodes are searched recursively.<br>
     * If there is a node that matches the keyword, it will be added to the argument ret.
     * @param ret Storage location of FMNode that match the keyword
     * @param file FM file
     * @param childNode Child FM Node
     * @param path FM node path
     * @param keyWord Keywords used for fuzzy search of FM node name
     */
    private void parseFMNode(List<SearchResult> ret, File file, FMNode childNode, String path, String keyWord) {
        path += "." + childNode.getName();
        final String fullPath = path;
        if (childNode.getName().indexOf(keyWord) != -1) {
            ret.add(new SearchResult(file.getId(), fullPath, file.getFullPath(), file.getExtension(), convertToBinary(EcoreUtil.copy(childNode))));
        }
        if (childNode.getChildren() != null && !childNode.getChildren().isEmpty()) {
            childNode.getChildren().forEach(x -> {
                parseFMNode(ret, file, x, fullPath, keyWord);
            });
        }
    }

    /**
     * Converts EObject to binary data.
     * @param data EObject to be converted
     * @return Binary data after conversion
     */
    public byte[] convertToBinary(EObject data) {
        BinaryResourceImpl r = new BinaryResourceImpl();
        r.getContents().add(data);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            r.save(bo, EditOptions.getDefaultSaveOptions());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return bo.toByteArray();
    }
}
