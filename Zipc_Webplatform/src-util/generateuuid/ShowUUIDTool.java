package generateuuid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.fm.FMNode;

public class ShowUUIDTool {

    private Map<Path, AbstractRootElement> inputPathToRoot = new HashMap<>();

    private List<Path> inputFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new ShowUUIDTool().execute();

    }

    private void execute() throws IOException {
        // register EMF package
        EditManager.createInstance();

        Path inputFilePath = Paths.get(".", "REGenerateUUIDTool", "to");

        System.out.println("input file directory : " + inputFilePath);

        if (Files.notExists(inputFilePath)) {
            Files.createDirectories(inputFilePath);
        }

        //
        Files.list(inputFilePath).forEach(p -> load(p));

        inputFiles.forEach(p -> convert(p));

    }

    private void convert(Path inputPath) {
        System.out.println("input : " + inputPath);

        AbstractRootElement root = inputPathToRoot.get(inputPath);
        
        System.out.println(inputPath);
        System.out.println("\t" + root.eClass().getName());
        System.out.println("\t\t#getId():" + root.getId());
        root.eAllContents().forEachRemaining(obj -> convertChildelement(obj));

    }

    private void convertChildelement(EObject obj) {
        System.out.print("\t\t" + obj.eClass().getName());
        if (obj instanceof Reference) {
            Reference element = (Reference) obj;
            System.out.print("\t#getRefid():" + element.getRefid());
        } else if (obj instanceof FMNode) {
            FMNode element = (FMNode) obj;
            System.out.print("\t#getName():" + element.getName());
            System.out.print("\t#getRef():" + element.getRef());
            System.out.print("\t#getRefuuid():" + element.getRefuuid());
        }
        System.out.println("");
    }

    private void load(Path path) {
        try {
            if (Files.isDirectory(path)) {
                return;
            }
            byte[] filecontent = Files.readAllBytes(path);
            EObject root = convertToEMFObject(filecontent);
            if (!(root instanceof AbstractRootElement)) {
                return;
            }

            inputFiles.add(path);

            AbstractRootElement rootElement = (AbstractRootElement) root;

            inputPathToRoot.put(path, rootElement);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private EObject convertToEMFObject(byte[] result) throws IOException {
        BinaryResourceImpl r = new BinaryResourceImpl();
        ByteArrayInputStream bi = new ByteArrayInputStream(result);
        r.load(bi, null);
        return r.getContents().get(0);

    }

    private void save(EObject root, Path out) throws IOException {
        BinaryResourceImpl r = new BinaryResourceImpl();
        r.getContents().add(root);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        r.save(outputStream, null);

        Files.write(out, outputStream.toByteArray());

    }

    private String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
