package com.zipc.garden.job.tp;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCNodeState;
import com.zipc.garden.model.tc.TCRoot;

/**
 * Optional resolution class for TCNode.
 */
public class TCResolver {

    /**
     * A class that creates and expand a duplicate of an optional node.
     */
    public static class TCCopier extends EcoreUtil.Copier {

        /** serialVersionUID. */
        private static final long serialVersionUID = 6555641143178811295L;

        /**
         * A copy of the specified node is made and the optional node expand is executed.
         * @param eObject specified node
         * @return Copy of the specified node
         */
        @Override
        public EObject copy(EObject eObject) {
            EObject copyEObject = super.copy(eObject);

            // Optionalノード と OFFノードを作成する
            if (copyEObject instanceof TCNode && ((TCNode) copyEObject).isOptional()) {
                TCNode OptionalNode = TCFactory.eINSTANCE.createTCNode();
                OptionalNode.setName("Optional");
                OptionalNode.setChecked(true);
                OptionalNode.setInherited(true);
                OptionalNode.setChildType(ChildType.XOR);
                OptionalNode.setState(TCNodeState.UNCHANGED);
                OptionalNode.setTemporary(true);

                TCNode offNode = TCFactory.eINSTANCE.createTCNode();
                offNode.setName("OFF");
                offNode.setChecked(true);
                offNode.setInherited(true);
                offNode.setChildType(ChildType.AND);
                offNode.setState(TCNodeState.UNCHANGED);
                offNode.setTemporary(true);

                OptionalNode.getChildren().add(offNode);
                OptionalNode.getChildren().add((TCNode) copyEObject);

                return OptionalNode;
            }

            if (copyEObject instanceof TCNode) {
                // childrenにoptional:trueがあるか確認
                boolean hasOptional = ((TCNode) copyEObject).getChildren().stream().filter(TCNode::isTemporary)
                        .filter(f -> f.getChildren().stream().filter(TCNode::isOptional).findFirst().isPresent()).findFirst().isPresent();
                if (hasOptional) {
                    return createNormalNode((TCNode) copyEObject);
                }
            }

            return copyEObject;
        }

        /**
         * <pre>
         * Create a node that is not optional.
         * The child nodes, child types, and cardinality of the specified node will be inherited by the node created here.
         * </pre>
         * 
         * @param copyEObject specified node
         * @return specified node (copyEObject)
         */
        private EObject createNormalNode(TCNode copyEObject) {
            TCNode NormalNode = TCFactory.eINSTANCE.createTCNode();
            NormalNode.setName("Normal");
            NormalNode.setChecked(true);
            NormalNode.setInherited(true);
            NormalNode.setChildType(copyEObject.getChildType());
            NormalNode.setMin(copyEObject.getMin());
            NormalNode.setMax(copyEObject.getMax());
            NormalNode.setTemporary(true);

            copyEObject.setChildType(ChildType.AND);
            copyEObject.setMin(-1);
            copyEObject.setMax(-1);

            // Childrenから、Temporary以外のノードを取得
            List<TCNode> moveChildren = copyEObject.getChildren().stream().filter(n -> !n.isTemporary()).collect(Collectors.toList());
            moveChildren.forEach(NormalNode.getChildren()::add);

            copyEObject.getChildren().add(NormalNode);

            return copyEObject;
        }

        /**
         * Finds the value that matches the argument and gets the key.
         * @param <T>
         * @param value
         * @return key
         */
        public <T extends EObject> T getKey(T value) {
            for (Map.Entry<EObject, EObject> entry : this.entrySet()) {
                if (entry.getValue().equals(value)) {
                    EObject result = entry.getKey();
                    @SuppressWarnings("unchecked")
                    T t = (T) result;
                    return t;
                }
            }
            return null;
        }
    }

    /**
     * A class that inherits SimpleEntry that manages the copied TPRoot and copy class.
     * @param <K> TCRoot
     * @param <V> TCCopier
     */
    public static class TCResolveResult<K, V> extends AbstractMap.SimpleEntry<K, V> {

        /** serialVersionUID. */
        private static final long serialVersionUID = 6411527075103472113L;

        /**
         * constructor
         * @param key TCRoot
         * @param value TCCopier
         */
        public TCResolveResult(final K key, final V value) {
            super(key, value);
        }
    }

    /**
     * Makes a copy of the specified TCRoot and resolves the options.
     * @param eObject specified TCNode
     * @return An entry class with the copied TPRoot as the key and the copy class as the value.
     */
    public static TCResolveResult<TCRoot, TCCopier> copy(TCRoot eObject) {
        TCCopier copier = new TCCopier();
        EObject result = copier.copy(eObject);
        copier.copyReferences();

        TCRoot t = (TCRoot) result;
        return new TCResolveResult<TCRoot, TCCopier>(t, copier);
    }
}
