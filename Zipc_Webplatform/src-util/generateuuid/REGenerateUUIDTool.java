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

public class REGenerateUUIDTool {

    private Map<Long, String> idToUUID = new HashMap<>();

    private Map<String, AbstractRootElement> oldUUIDtoRoot = new HashMap<>();

    private Map<String, AbstractRootElement> newUUIDtoRoot = new HashMap<>();

    private Map<String, String> oldUUIDtoNewUUID = new HashMap<>();

    private Map<String, String> newUUIDtoOldUUID = new HashMap<>();

    private Map<Path, AbstractRootElement> inputPathToRoot = new HashMap<>();

    private List<Path> inputFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new REGenerateUUIDTool().execute();

    }

    private void execute() throws IOException {
        // register EMF package
        EditManager.createInstance();

        Path inputFilePath = Paths.get(".", "REGenerateUUIDTool", "from");
        Path outputFilePath = Paths.get(".", "REGenerateUUIDTool", "to");

        System.out.println("input file directory : " + inputFilePath);
        System.out.println("putput file directory : " + outputFilePath);

        if (Files.notExists(inputFilePath)) {
            Files.createDirectories(inputFilePath);
        }
        if (Files.notExists(outputFilePath)) {
            Files.createDirectories(outputFilePath);
        }

        //
        Files.list(inputFilePath).forEach(p -> load(p));

        inputFiles.forEach(p -> convert(p, outputFilePath.resolve(p.getFileName())));

    }

    private void convert(Path inputPath, Path outPath) {
        System.out.println("input : " + inputPath);
        System.out.println("output : " + outPath);

        AbstractRootElement root = inputPathToRoot.get(inputPath);

        String oldUUID = root.getId();
        String newUUID = oldUUIDtoNewUUID.get(oldUUID);

        root.setId(newUUID);

        root.eAllContents().forEachRemaining(obj -> convertChildelement(obj));

        try {
            save(root, outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertChildelement(EObject obj) {
        if (obj instanceof Reference) {
            Reference element = (Reference) obj;
            String oldUUID = element.getRefid();
            String newUUID = oldUUIDtoNewUUID.get(oldUUID);
            element.setRefid(newUUID);
        } else if (obj instanceof FMNode) {

            FMNode element = (FMNode) obj;
            System.out.println(" FMNode#getName    : " + element.getName());
            System.out.println("       #getRef     : " + element.getRef());
            System.out.println("       #getRefuuid : " + element.getRefuuid());

            String oldUUID = element.getRefuuid();
            String newUUID = oldUUIDtoNewUUID.get(oldUUID);
            if (newUUID == null) {
                oldUUID = String.valueOf(element.getRef());
                newUUID = oldUUIDtoNewUUID.get(oldUUID);
            }
            if (newUUID == null) {
                oldUUID = idToUUID.get(element.getRef());
                newUUID = oldUUIDtoNewUUID.get(oldUUID);
            }
            System.out.println(" newUUID : " + newUUID);

            element.setRefuuid(newUUID);
        }

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
            String oldUUID = rootElement.getId();
            // idが設定されていない場合、uuidをセットする
            if (oldUUID == null) {
                oldUUID = generateUUID();
                rootElement.setId(oldUUID);
            }
            String newUUID = generateUUID();

            oldUUIDtoRoot.put(oldUUID, rootElement);
            newUUIDtoRoot.put(newUUID, rootElement);
            oldUUIDtoNewUUID.put(oldUUID, newUUID);
            newUUIDtoOldUUID.put(newUUID, oldUUID);

            System.out.println(" rootElement#getId    : " + rootElement.getId());
            System.out.println("            newUUID   : " + newUUID);

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
