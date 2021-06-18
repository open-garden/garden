package com.zipc.garden.fsm.simulator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.zipc.garden.model.arc.ARCPackage;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bps.BPSPackage;
import com.zipc.garden.model.cb.CBPackage;
import com.zipc.garden.model.cgen.CGENPackage;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.csc.CSCPackage;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fmc.FMCPackage;
import com.zipc.garden.model.fmcs.FMCSPackage;
import com.zipc.garden.model.fsm.FSMPackage;
import com.zipc.garden.model.lgen.LGENPackage;
import com.zipc.garden.model.lsc.LSCPackage;
import com.zipc.garden.model.scd.SCDPackage;
import com.zipc.garden.model.scs.SCSPackage;
import com.zipc.garden.model.spql.SPQLPackage;
import com.zipc.garden.model.tc.TCPackage;
import com.zipc.garden.model.tp.TPPackage;
import com.zipc.garden.model.tps.TPSPackage;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.logic.ProjectServiceLogic;
import com.zipc.garden.webplatform.server.logic.UserServiceLogic;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

public class DBUtil {

    public static DBUtil INSTANCE = new DBUtil();

    public DBUtil() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("arc", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bp", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bps", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("cb", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("cgen", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("core", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("csc", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fm", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fmc", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fmcs", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fsm", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lgen", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lsc", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("scd", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("scs", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("spql", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("tc", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("tp", new BinaryResourceImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("tps", new BinaryResourceImpl());
        EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ARCPackage.eNS_URI, ARCPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(BPPackage.eNS_URI, BPPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(BPSPackage.eNS_URI, BPSPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(CBPackage.eNS_URI, CBPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(CGENPackage.eNS_URI, CGENPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(COREPackage.eNS_URI, COREPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(CSCPackage.eNS_URI, CSCPackage.eINSTANCE);
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
    }

    public boolean createUser(String path) {
        Path file = Paths.get(path);
        try {
            List<String> lines = Files.readAllLines(file);
            String userName = null;
            String displayName = null;
            String mail = null;
            String password = null;
            int role = 0;
            for (String line : lines) {
                List<String> text = Arrays.asList(line.split(":"));
                if (2 != text.size()) {
                    continue;
                }
                String key = text.get(0);
                String value = text.get(1);
                if (key.equals("userName")) {
                    userName = value;
                } else if (key.equals("displayName")) {
                    displayName = value;
                } else if (key.equals("mail")) {
                    mail = value;
                } else if (key.equals("password")) {
                    password = value;
                } else if (key.equals("role")) {
                    role = Integer.valueOf(value);
                }
            }
            if ((null != userName) && (null != displayName) && (null != mail) && (null != password)) {
                String result = UserServiceLogic.createUser(userName, displayName, mail, password, role, -1L, true);
                if (result.contentEquals("")) {
                    return true;
                } else {
                    System.out.println(result);
                    return false;
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Long getUserId(String path) {
        Path file = Paths.get(path);
        List<UserInfo> userInfos = UserServiceLogic.getUserInfoList(1L);
        try {
            List<String> lines = Files.readAllLines(file);
            String userName = null;
            String displayName = null;
            String mail = null;
            String password = null;
            int role = 0;
            for (String line : lines) {
                List<String> text = Arrays.asList(line.split(":"));
                if (2 != text.size()) {
                    continue;
                }
                String key = text.get(0);
                String value = text.get(1);
                if (key.equals("userName")) {
                    userName = value;
                } else if (key.equals("displayName")) {
                    displayName = value;
                } else if (key.equals("mail")) {
                    mail = value;
                } else if (key.equals("password")) {
                    password = value;
                } else if (key.equals("role")) {
                    role = Integer.valueOf(value);
                }
            }
            if ((null != userName) && (null != displayName) && (null != mail) && (null != password)) {
                for (UserInfo userInfo : userInfos) {
                    if (!userInfo.getUserName().equals(userName)) {
                        continue;
                    } else if (!userInfo.getDisplayName().equals(displayName)) {
                        continue;
                    } else if (!userInfo.getMail().equals(mail)) {
                        continue;
                    } else if (userInfo.getRole() != role) {
                        continue;
                    } else {
                        return userInfo.getUserId();
                    }

                }
                return null;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeUser(String path) {
        Path file = Paths.get(path);
        List<UserInfo> userInfos = UserServiceLogic.getUserInfoList(1L);
        try {
            List<String> lines = Files.readAllLines(file);
            String userName = null;
            String displayName = null;
            String mail = null;
            String password = null;
            int role = 0;
            for (String line : lines) {
                List<String> text = Arrays.asList(line.split(":"));
                if (2 != text.size()) {
                    continue;
                }
                String key = text.get(0);
                String value = text.get(1);
                if (key.equals("userName")) {
                    userName = value;
                } else if (key.equals("displayName")) {
                    displayName = value;
                } else if (key.equals("mail")) {
                    mail = value;
                } else if (key.equals("password")) {
                    password = value;
                } else if (key.equals("role")) {
                    role = Integer.valueOf(value);
                }
            }
            if ((null != userName) && (null != displayName) && (null != mail) && (null != password)) {
                for (UserInfo userInfo : userInfos) {
                    if (!userInfo.getUserName().equals(userName)) {
                        continue;
                    } else if (!userInfo.getDisplayName().equals(displayName)) {
                        continue;
                    } else if (!userInfo.getMail().equals(mail)) {
                        continue;
                    } else if (userInfo.getRole() != role) {
                        continue;
                    } else {
                        List<Long> userIds = new ArrayList<>();
                        userIds.add(userInfo.getUserId());
                        UserServiceLogic.removeUsers(userIds);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean createProject(String path, Long userId) {
        Path file = Paths.get(path);
        try {
            List<String> lines = Files.readAllLines(file);
            String projectName = null;
            for (String line : lines) {
                List<String> text = Arrays.asList(line.split(":"));
                if (2 != text.size()) {
                    continue;
                }
                String key = text.get(0);
                String value = text.get(1);
                if (key.equals("projectName")) {
                    projectName = value;
                }
            }
            if (null != projectName) {
                ProjectServiceLogic.createProject(projectName, userId);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Long getProjectId(String path, Long userId) {
        Path file = Paths.get(path);
        List<ProjectInfo> projectInfos = ProjectServiceLogic.getProjects(userId);
        try {
            List<String> lines = Files.readAllLines(file);
            String projectName = null;
            for (String line : lines) {
                List<String> text = Arrays.asList(line.split(":"));
                if (2 != text.size()) {
                    continue;
                }
                String key = text.get(0);
                String value = text.get(1);
                if (key.equals("projectName")) {
                    projectName = value;
                }
            }
            if (null != projectName) {
                for (ProjectInfo prj : projectInfos) {
                    if (prj.getName().equals(projectName)) {
                        return prj.getId();
                    }
                }
                return null;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean removeProject(String path, Long userId) {
        Path file = Paths.get(path);
        List<ProjectInfo> projectInfos = ProjectServiceLogic.getProjects(userId);
        try {
            List<String> lines = Files.readAllLines(file);
            String projectName = null;
            for (String line : lines) {
                List<String> text = Arrays.asList(line.split(":"));
                if (2 != text.size()) {
                    continue;
                }
                String key = text.get(0);
                String value = text.get(1);
                if (key.equals("projectName")) {
                    projectName = value;
                }
            }
            if (null != projectName) {
                for (ProjectInfo prj : projectInfos) {
                    if (prj.getName().equals(projectName)) {
                        List<Long> projectIds = new ArrayList<>();
                        projectIds.add(prj.getId());
                        ProjectServiceLogic.removeProject(projectIds);
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Long uploadFile(String path, Long userId, Long projectId) {
        Path filePath = Paths.get(path);
        File file = filePath.toFile();
        int point = file.getName().lastIndexOf(".");
        String fileName = file.getName().substring(0, point);
        String fileExtension = file.getName().substring(point + 1);

        VMFile vmFile = new VMFile();
        vmFile.setName(fileName);
        vmFile.setExtensionStr(fileExtension);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);

        ProjectInfo prjInfo = ProjectServiceLogic.getProject(projectId);
        long parentId = prjInfo.getRootDirId();

        EObject eObject = load(path);
        if (eObject instanceof AbstractRootElement) {
            byte[] content = EditResourceUtil.INSTANCE.convertToBinary((AbstractRootElement) eObject);
            Long result = EditResourceUtil.INSTANCE.uploadFile(parentId, vmFile, content, userInfo);
            return result;
        } else {
            System.out.println(path + " is not AbstractRootElement!!");
            return -1L;
        }
    }

    private EObject load(String path) {
        try {
            byte[] contents = Files.readAllBytes(Paths.get(path));
            return EditResourceUtil.INSTANCE.convertToRootElement(contents);
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }
}
