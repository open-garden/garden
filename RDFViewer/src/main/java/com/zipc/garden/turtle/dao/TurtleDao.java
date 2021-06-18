package com.zipc.garden.turtle.dao;

public class TurtleDao {

    private String[] subjectClass = new String[0];

    private String[] subjectClassNameSpace;

    private String[] subjectClassLocalName;

    private String[] objectClass = new String[0];

    private String[] objectClassNameSpace;

    private String[] objectClassLocalName;

    private boolean isSubjectAnon;

    private String subject;

    private String subjectNameSpace;

    private String subjectLocalName;
    
    private String subjectLabelName;

    private boolean isPredicateAnon;

    private String predicate;

    private String predicateNameSpace;

    private String predicateLocalName;

    private boolean isObjectAnon;

    private String object;

    private String objectNameSpace;

    private String objectLocalName;
    
    private String objectLabelName;

    public void setSubjectClass(String[] subjectClass) {
        this.subjectClass = subjectClass;
        this.subjectClassNameSpace = new String[subjectClass.length];
        this.subjectClassLocalName = new String[subjectClass.length];

        for (int i = 0; i < subjectClass.length; i++) {
            String[] str = subjectClass[i].split("#");
            if (str.length > 1) {
                this.subjectClassNameSpace[i] = str[0];
                this.subjectClassLocalName[i] = str[1];
            } else {
                this.subjectClassNameSpace[i] = "";
                this.subjectClassLocalName[i] = str[0];
            }
        }
    }

    public String[] getSubjectClass() {
        return subjectClass;
    }

    public String[] getSubjectClassNameSpace() {
        return subjectClassNameSpace;
    }

    public String[] getSubjectClassLocalName() {
        return subjectClassLocalName;
    }

    public void setObjectClass(String[] objectClass) {
        this.objectClass = objectClass;
        this.objectClassNameSpace = new String[objectClass.length];
        this.objectClassLocalName = new String[objectClass.length];

        for (int i = 0; i < objectClass.length; i++) {
            String[] str = objectClass[i].split("#");
            if (str.length > 1) {
                this.objectClassNameSpace[i] = str[0];
                this.objectClassLocalName[i] = str[1];
            } else {
                this.objectClassNameSpace[i] = "";
                this.objectClassLocalName[i] = str[0];
            }
        }
    }

    public String[] getObjectClass() {
        return objectClass;
    }

    public String[] getObjectClassNameSpace() {
        return objectClassNameSpace;
    }

    public String[] getObjectClassLocalName() {
        return objectClassLocalName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
        String[] str = subject.split("#");
        if (str.length > 1) {
            this.subjectNameSpace = str[0];
            this.subjectLocalName = str[1];
        } else {
            this.subjectNameSpace = "";
            this.subjectLocalName = str[0];
        }
    }

    public String getSubject() {
        return subject;
    }

    public String getSubjectNameSpace() {
        return subjectNameSpace;
    }

    public String getSubjectLocalName() {
        return subjectLocalName;
    }

    public String getSubjectLabelName() {
        return subjectLabelName;
    }

    public void setSubjectLabelName(String subjectLabelName) {
        this.subjectLabelName = subjectLabelName;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
        String[] str = predicate.split("#");
        if (str.length > 1) {
            this.predicateNameSpace = str[0];
            this.predicateLocalName = str[1];
        } else {
            this.predicateNameSpace = "";
            this.predicateLocalName = str[0];
        }
    }

    public String getPredicate() {
        return predicate;
    }

    public String getPredicateNameSpace() {
        return predicateNameSpace;
    }

    public String getPredicateLocalName() {
        return predicateLocalName;
    }

    public void setObject(String object) {
        this.object = object;
        String[] str = object.split("#");
        if (str.length > 1) {
            this.objectNameSpace = str[0];
            this.objectLocalName = str[1];
        } else {
            this.objectNameSpace = "";
            this.objectLocalName = str[0];
        }
    }

    public String getObject() {
        return object;
    }

    public String getObjectNameSpace() {
        return objectNameSpace;
    }

    public String getObjectLocalName() {
        return objectLocalName;
    }

    public String getObjectLabelName() {
        return objectLabelName;
    }

    public void setObjectLabelName(String objectLabelName) {
        this.objectLabelName = objectLabelName;
    }

    public boolean isSubjectAnon() {
        return isSubjectAnon;
    }

    public void setSubjectAnon(boolean isSubjectAnon) {
        this.isSubjectAnon = isSubjectAnon;
    }

    public boolean isPredicateAnon() {
        return isPredicateAnon;
    }

    public void setPredicateAnon(boolean isPredicateAnon) {
        this.isPredicateAnon = isPredicateAnon;
    }

    public boolean isObjectAnon() {
        return isObjectAnon;
    }

    public void setObjectAnon(boolean isObjectAnon) {
        this.isObjectAnon = isObjectAnon;
    }
}
