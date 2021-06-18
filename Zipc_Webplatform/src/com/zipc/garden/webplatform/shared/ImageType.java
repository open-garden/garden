package com.zipc.garden.webplatform.shared;

/**
 * This class manages the types of images you can have in your project.
 */
public enum ImageType {
    JPEG("jpeg", "data:image/jpeg;base64,"),

    JPG("jpg", "data:image/jpeg;base64,"),

    PNG("png", "data:image/png;base64,"),

    BMP("bmp", "data:image/bmp;base64,"),

    GIF("gif", "data:image/gif;base64,"),

    TIFF("tiff", "data:image/tiff;base64,"),

    TIF("tif", "data:image/tiff;base64,");

    /** Image file extension */
    private final String extension;

    /** Image file MIME type */
    private final String mimeType;

    /**
     * constructor
     * @param extension {@link #extension}
     * @param mimeType {@link #mimeType}
     */
    ImageType(String extension, String mimeType) {
        this.extension = extension;
        this.mimeType = mimeType;
    }

    /**
     * Gets the extension of the specified enumeration constant.
     * @return Image file extension
     */
    public String getExtension() {
        return this.extension;
    }

    /**
     * Gets the MIME type of the specified enumeration constant.
     * @return Image file MIME type
     */
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Finds the enumeration constant that matches the specified extension.
     * @param extension specified extension.
     * @return Throws IllegalArgumentException if not found.
     */
    public static ImageType getByExtension(String extension) {
        for (ImageType imageType : ImageType.values()) {
            if (imageType.getExtension().equals(extension)) {
                return imageType;
            }
        }
        throw new IllegalArgumentException("The specified file format can not be selected.");
    }
}
