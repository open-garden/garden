package com.zipc.garden.webplatform.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Data Access Object Class for VerificationToken Table
 */
@Entity
@Table(name = "VerificationToken")
public class VerificationToken implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = 1872223337664693473L;

    /** Token expiration date */
    private static final int EXPIRATION = 60 * 24;

    /** token */
    private String token;

    /** Unique ID of the VerificationToken table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** The user associated with the token. Joined by user id. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserMaster user;

    /** Token expiration date. */
    private Date expiryDate;

    /**
     * constructor. <br>
     * Used for operations on the specified token.
     * @param token token
     */
    public VerificationToken(final String token) {
        super();

        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    /**
     * constructor. <br>
     * Used to manipulate the tokens of the specified user.
     * @param token token
     * @param user user info
     */
    public VerificationToken(final String token, final UserMaster user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    /**
     * constructor
     */
    public VerificationToken() {
        super();
    }

    /**
     * Get a token.
     * @return {@link #token}
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the token.
     * @param token token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the user associated with the token.
     * @return {@link #user}
     */
    public UserMaster getUser() {
        return user;
    }

    /**
     * Sets the user associated with the token.
     * @param user The user associated with the token.
     */
    public void setUser(UserMaster user) {
        this.user = user;
    }

    /**
     * Get the token expiration date.
     * @return {@link #expiryDate}
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Set the token expiration date.
     * @param expiryDate Token expiration date.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the unique ID of the VerificationToken table.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * Returns the hashcodes of {@link #expiryDate}, {@link #token}, {@link #user}.
     * 
     * {@inheritDoc}
     * </pre>
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    /**
     * <pre>
     * Checks if the contents of this class match the contents of the specified object.
     * 
     * {@inheritDoc}
     * </pre>
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VerificationToken other = (VerificationToken) obj;
        if (expiryDate == null) {
            if (other.expiryDate != null) {
                return false;
            }
        } else if (!expiryDate.equals(other.expiryDate)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * You will get a token and an expiration date. 
     * 
     * {@inheritDoc}
     * </pre>
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiryDate).append("]");
        return builder.toString();
    }

    /**
     * The specified value is added to the current time and returned as the expiration date.
     * @param expiryTimeInMinutes specified value
     * @return expiration date.
     */
    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
