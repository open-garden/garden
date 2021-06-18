package com.zipc.garden.webplatform.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Class that manages project information
 */
public class ProjectInfo implements IsSerializable {

    /** project id */
    private Long id;

    /** project name */
    private String name;

    /** project description */
    private String description;

    /** Image of project */
    private Byte[] image;

    /** URL of the project image */
    private String imageData;

    /** Project image name */
    private String imageName;

    /** ID of the project root directory */
    private Long rootDirId;

    /** Project image extension and MIME type */
    private ImageType imageType;

    /** Project image width */
    private int imageWidth;

    /** Project image height */
    private int imageHeight;

    /** Character code of the project */
    private CharaCode encodingType;

    /**
     * constructor.
     */
    public ProjectInfo() {

    }

    /**
     * constructor.<br>
     * Used to get records in the project table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param id {@link #id}
     * @param name {@link #name}
     */
    public ProjectInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * constructor.<br>
     * Used to get records in the project table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param id {@link #id}
     * @param name {@link #name}
     * @param description {@link #description}
     * @param image {@link #image}
     * @param rootDirId {@link #rootDirId}
     * @param imageName {@link #imageName}
     * @param imageWidth {@link #imageWidth}
     * @param imageHeight {@link #imageHeight}
     * @param encodingType {@link #encodingType}
     */
    public ProjectInfo(Long id, String name, String description, Byte[] image, Long rootDirId, String imageName, int imageWidth, int imageHeight, String encodingType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.rootDirId = rootDirId;
        setImageName(imageName);
        this.imageName = imageName;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.encodingType = CharaCode.getByName(encodingType);
    }

    /**
     * Get project id
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set project id
     * @param id {@link #id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get project name
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set project name
     * @param name {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get project description
     * @return {@link #description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set project description
     * @param description {@link #description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the image of project
     * @return {@link #image}
     */
    public Byte[] getImage() {
        return image;
    }

    /**
     * Set the image of project
     * @param image {@link #image}
     */
    public void setImage(Byte[] image) {
        this.image = image;
    }

    /**
     * Gets the ID of the project root directory
     * @return {@link #rootDirId}
     */
    public Long getRootDirId() {
        return rootDirId;
    }

    /**
     * Sets the ID of the project root directory
     * @param rootDirId {@link #rootDirId}
     */
    public void setRootDirId(Long rootDirId) {
        this.rootDirId = rootDirId;
    }

    /**
     * Get URL of the project image
     * @return {@link #imageData}
     */
    public String getImageData() {
        return imageData;
    }

    /**
     * Set URL of the project image
     * @param imageData {@link #imageData}
     */
    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    /**
     * Get project image extension and MIME type
     * @return imageType {@link #imageType}
     */
    public ImageType getImageType() {
        return imageType;
    }

    /**
     * Set project image extension and MIME type
     * @param imageType {@link #imageType}
     */
    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    /**
     * Get project image name
     * @return {@link #imageName}
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Set project image name and image type.
     * @param imageName {@link #imageName}
     */
    public void setImageName(String imageName) {
        if (imageName != null && !imageName.isEmpty()) {
            int dotIndex = imageName.lastIndexOf('.');
            String extension = imageName.substring(dotIndex + 1, imageName.length());
            setImageType(ImageType.getByExtension(extension));
        }
        this.imageName = imageName;
    }

    /**
     * Get project image width
     * @return {@link #imageWidth}
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Set project image width
     * @param imageWidth {@link #imageWidth}
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * Get project image height
     * @return {@link #imageHeight}
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Set project image height
     * @param imageHeight {@link #imageHeight}
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * Get character code of project
     * @return {@link #encodingType}
     */
    public CharaCode getEncodingType() {
        return encodingType;
    }

    /**
     * Set character code of project
     * @param encodingType {@link #encodingType}
     */
    public void setEncodingType(CharaCode encodingType) {
        this.encodingType = encodingType;
    }

}
