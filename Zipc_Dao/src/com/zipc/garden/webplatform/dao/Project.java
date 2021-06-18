package com.zipc.garden.webplatform.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Data Access Object Class for Project Table
 */
@Entity
@Table
public class Project implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -2630904260492394649L;

    /** Unique ID of the File table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** The name of the project */
    @Column
    private String name;

    /** The root directory of the project. It is combined by the ID of the root directory. */
    @OneToOne
    @JoinColumn(name = "rootDirId")
    private Directory directory;

    /** A description of the project. */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** Image of the project. */
    @Column
    private Byte[] image;

    /** The name of the image of the project. */
    @Column
    private String imageName;

    /** The width of the image of the project. */
    @Column
    private int imageWidth;

    /** The height of the image of the project. */
    @Column
    private int imageHeight;

    /** Project character encoding. */
    @Column
    private String encodingType;

    /** The name of the array that summarizes the variables used to calculate the risk importance. */
    @Column
    private String allVariablesName;

    /**
     * constructor
     */
    public Project() {

    }

    /**
     * constructor <br>
     * Used when dealing with the specified project.
     * @param id specified project ID.
     */
    public Project(Long id) {
        this.id = id;
    }

    /**
     * Get the project ID.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the project ID.
     * @param id project ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the project name.
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the project name.
     * @param name project name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the root directory of the project.
     * @return {@link #directory}
     */
    public Directory getDirectory() {
        return directory;
    }

    /**
     * Set the root directory of the project.
     * @param directory The root directory of the project.
     */
    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    /**
     * Get the project description.
     * @return {@link #description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the project description.
     * @param description A description of the project.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get an image of the project.
     * @return {@link #image}
     */
    public Byte[] getImage() {
        return image;
    }

    /**
     * Set the image of the project.
     * @param image Image of the project.
     */
    public void setImage(Byte[] image) {
        this.image = image;
    }

    /**
     * Gets the name of the project image.
     * @return {@link #imageName}
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Set the name of the project image.
     * @param imageName The name of the image of the project.
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Gets the width of the project image.
     * @return {@link #imageWidth}
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Sets the width of the project image.
     * @param imageWidth The width of the image of the project.
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * Gets the height of the project image.
     * @return {@link #imageHeight}
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Sets the height of the project image.
     * @param imageHeight The height of the image of the project.
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * Gets the character encoding of the project.
     * @return {@link #encodingType}
     */
    public String getEncodingType() {
        return encodingType;
    }

    /**
     * Set the character encoding of the project.
     * @param encodingType {@link #encodingType}
     */
    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    /**
     * Gets the name of the array that summarizes the variables.
     * @return {@link #allVariablesName}
     */
    public String getAllVariablesName() {
        return allVariablesName;
    }

    /**
     * Sets the name of the array that summarizes the variables.
     * @param allVariablesName The name of the array that summarizes the variables
     */
    public void setAllVariablesName(String allVariablesName) {
        this.allVariablesName = allVariablesName;
    }

}
