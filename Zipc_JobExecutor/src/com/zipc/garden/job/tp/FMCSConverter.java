package com.zipc.garden.job.tp;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.zipc.garden.model.fmc.FMCConstraint;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.model.fmcs.FMCSConstraint;
import com.zipc.garden.model.fmcs.FMCSFactory;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.parser.fmc.constraint.FMConstraintParser;
import com.zipc.garden.parser.fmc.constraint.Node;
import com.zipc.garden.webplatform.shared.NodeUtil;

/**
 * This class summarizes the process of converting FMC (text) to FMCS (syntax tree).
 */
public class FMCSConverter {

    /** The class used to search for the full path. */
    private TCRoot tc;

    /** The class from which to convert */
    private FMCRoot fmc;

    /** the error message when the conversion fails. */
    private String errorMessage;

    /**
     * constructor
     * @param tc Default value for {@link #tc}.
     * @param fmc Default value for {@link #fmc}.
     */
    public FMCSConverter(TCRoot tc, FMCRoot fmc) {
        this.tc = tc;
        this.fmc = fmc;
    }

    /**
     * Convert FMC (text) to FMCS (syntax tree)
     * @return syntax tree
     */
    public FMCSRoot convert() {
        FMCSRoot root = FMCSFactory.eINSTANCE.createFMCSRoot();
        for (FMCConstraint fmConstraint : fmc.getConstraints()) {
            if (!fmConstraint.isEnabled()) {
                continue; // 無効な制約式は無視する
            }
            try (Reader in = new StringReader(fmConstraint.getConstraint())) {
                FMConstraintParser parser = new FMConstraintParser(in);
                Node parseRoot = parser.Root();
                FMCSConstraint constraint = (FMCSConstraint) parseRoot.jjtAccept(new ASTRoot2FmcsConvertVisitor(), root);
                constraint.setRef(fmConstraint);
                List<FMCSODElement> odElements = TCUtil.getAllContentsOfType(constraint, FMCSODElement.class);
                for (FMCSODElement odElement : odElements) {
                    String fullName = convertFullName(odElement);
                    if (null == fullName) {
                        setErrorMessage(".fmc constraint index[" + fmc.getConstraints().indexOf(fmConstraint) + "] Node[" + odElement.getFullName() + "] is not exists");
                        return null;
                    }
                    odElement.setFullName(fullName);
                }
                root.getConstraints().add(constraint);
            } catch (Throwable e) {
                e.printStackTrace();
                setErrorMessage(".fmc constraint index[" + fmc.getConstraints().indexOf(fmConstraint) + "]\"" + fmConstraint.getConstraint() + "\" is wrong");
                return null;
            }
        }
        return root;
    }

    /**
     * <pre>
     * Converts the full path of ODElement to the expanded full path.
     * 
     * ODElementが持つ参照付フルパス名を展開済フルパス名に変換する
     * </pre>
     * 
     * @param odElement
     * @return Full path
     */
    private String convertFullName(FMCSODElement odElement) {
        String srcName = odElement.getFullName();
        TCRoot targetRoot = tc;
        TCNode node = findNode(targetRoot, srcName);
        if (null != node) {
            String fullName = NodeUtil.getInstance().getTCNodeFullPath(node, false);
            return fullName;
        } else {
            return null;
        }
    }

    /**
     * From within TCRoot, go to find the node with the specified path.
     * @param tc TCRoot to be searched
     * @param pathsInFile Search value (node ​​path)
     * @return search results
     */
    private TCNode findNode(TCRoot tc, String pathsInFile) {
        List<TCNode> tcNodes = TCUtil.getAllContentsOfType(tc, TCNode.class);
        List<String> names = NodeUtil.getInstance().splitNode(pathsInFile);
        Collections.reverse(names);
        for (TCNode tcNode : tcNodes) {
            Iterator<String> itr = names.iterator();
            if (isMatch(tcNode, itr)) {
                return tcNode;
            }
        }
        return null;
    }

    /**
     * Checks if the path of the specified node matches the path of the iterator.
     * @param tcNode specified node
     * @param itr Iterator with split paths (reverse order).
     * @return True if they match. False if they do not match.
     */
    private boolean isMatch(TCNode tcNode, Iterator<String> itr) {
        while (itr.hasNext()) {
            if (!tcNode.getName().equals(itr.next())) {
                return false;
            }
            if (tcNode.eContainer() instanceof TCNode) {
                tcNode = (TCNode) tcNode.eContainer();
            } else {
                if (!itr.hasNext()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get the error message.
     * @return {@link #errorMessage}
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set the error message when the conversion fails.
     * @param errorMessage error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
