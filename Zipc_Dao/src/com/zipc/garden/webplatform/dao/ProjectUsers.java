package com.zipc.garden.webplatform.dao;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Data Access Object Class for ProjectUsers Table
 */
@Entity
@Table
public class ProjectUsers implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = 514114335158477924L;

    /** An internal class that summarizes primary key items of the ProjectUsers table. */
    @EmbeddedId
    private ProjectUsersPK pk;

    /** @deprecated Role column. Not used. */
    @Column
    private int role;

    /**
     * Gets the inner class that summarizes the primary key item.
     * @return {@link #pk}
     */
    public ProjectUsersPK getPk() {
        return pk;
    }

    /**
     * Sets an inner class that summarizes primary key items.
     * @param pk
     */
    public void setPk(ProjectUsersPK pk) {
        this.pk = pk;
    }

    /**
     * @deprecated
     * @return {@link #role}
     */
    public int getRole() {
        return role;
    }

    /**
     * @deprecated
     * @param role The value to set in the role column.
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Inner class that summarizes primary key items
     */
    @Embeddable
    public static class ProjectUsersPK implements Serializable {

        /** A unique identifier for the Serializable class */
        private static final long serialVersionUID = 4436831145047684338L;

        /** User master information. It is joined by user ID. */
        @OneToOne
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "userId")
        private UserMaster userMaster;

        /** Project information. It is joined by project ID. */
        @OneToOne
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "projectId")
        private Project project;

        /**
         * constructor
         */
        public ProjectUsersPK() {

        }

        /**
         * constructor. <br>
         * User information and projects that users can operate are set.
         * @param userMaster The specified user.
         * @param project A project that users can handle.
         */
        public ProjectUsersPK(UserMaster userMaster, Project project) {
            this.userMaster = userMaster;
            this.project = project;
        }

        /**
         * Get user master information.
         * @return {@link #userMaster}
         */
        public UserMaster getUserMaster() {
            return userMaster;
        }

        /**
         * Set user master information.
         * @param userMaster User master information.
         */
        public void setUserMaster(UserMaster userMaster) {
            this.userMaster = userMaster;
        }

        /**
         * Get the project information.
         * @return {@link #project}
         */
        public Project getProject() {
            return project;
        }

        /**
         * Set the project information.
         * @param project project information.
         */
        public void setProject(Project project) {
            this.project = project;
        }

        /**
         * <pre>
         * Compares projects and users of this class to projects and users of the specified ProjectUsersPK class.
         * 
         * {@inheritDoc}
         * </pre>
         */
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof ProjectUsersPK))
                return false;
            ProjectUsersPK that = (ProjectUsersPK) o;
            return Objects.equals(getProject(), that.getProject()) && Objects.equals(getUserMaster(), that.getUserMaster());
        }

        /**
         * <pre>
         * Get the hashcodes for this class of projects and users.
         * 
         * {@inheritDoc}
         * </pre>
         */
        @Override
        public int hashCode() {
            return Objects.hash(getProject(), getUserMaster());
        }
    }
}
