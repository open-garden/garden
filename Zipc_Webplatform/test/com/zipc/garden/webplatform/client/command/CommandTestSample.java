package com.zipc.garden.webplatform.client.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;

import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMFactory;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMRoot;

import junit.framework.TestCase;

public class CommandTestSample extends TestCase {

    public void test_moveNodes() {

        FMNode node = createRoot().getNode();
        List<FMNode> nodeList = new ArrayList<>();
        nodeList.add(node);
        CompoundCommand cmd = FMEditorCommandProvider.getInstance().moveNodes(nodeList, 15, 25);

        cmd.execute();
        assertEquals(node.getTop(), 200 + 15);
        assertEquals(node.getLeft(), 20 + 25);

        nodeList.clear();

        for (int i = 0; i < node.getChildren().size(); i++) {
            nodeList.add(node.getChildren().get(i));
        }

        CompoundCommand cmd2 = FMEditorCommandProvider.getInstance().moveNodes(nodeList, -20, -50);

        cmd2.execute();

        assertEquals(node.getChildren().get(0).getTop(), 50 - 20);
        assertEquals(node.getChildren().get(0).getLeft(), 120 - 50);
        assertEquals(node.getChildren().get(1).getTop(), 150 - 20);
        assertEquals(node.getChildren().get(1).getLeft(), 120 - 50);

        cmd2.undo();

        assertEquals(node.getChildren().get(0).getTop(), 50);
        assertEquals(node.getChildren().get(0).getLeft(), 120);
        assertEquals(node.getChildren().get(1).getTop(), 150);
        assertEquals(node.getChildren().get(1).getLeft(), 120);

        cmd.undo();
        assertEquals(node.getTop(), 200);
        assertEquals(node.getLeft(), 20);
    }

    private FMRoot createRoot() {
        FMRoot root = FMFactory.eINSTANCE.createFMRoot();

        FMNode node1 = makeNode("NODE1", 20, 200, 80, 40, ChildType.AND);
        FMNode node2 = makeNode("NODE2", 120, 50, 80, 40, ChildType.AND);
        FMNode node3 = makeNode("NODE3", 120, 150, 80, 40, ChildType.XOR);
        FMNode node4 = makeNode("NODE4", 220, 50, 80, 40, ChildType.AND);
        FMNode node5 = makeNode("NODE5", 220, 100, 80, 40, ChildType.AND);
        FMNode node6 = makeNode("NODE6", 220, 150, 80, 40, ChildType.AND);
        FMNode node7 = makeNode("NODE7", 220, 200, 80, 40, ChildType.AND);

        root.setNode(node1);
        node1.getChildren().add(node2);
        node1.getChildren().add(node3);
        node2.getChildren().add(node4);
        node2.getChildren().add(node5);
        node3.getChildren().add(node6);
        node3.getChildren().add(node7);

        return root;
    }

    private FMNode makeNode(String name, int left, int top, int width, int height, ChildType type) {
        FMNode ret = FMFactory.eINSTANCE.createFMNode();
        ret.setName(name);
        ret.setLeft(left);
        ret.setTop(top);
        ret.setWidth(width);
        ret.setHeight(height);
        ret.setChildType(type);
        return ret;
    }

}
