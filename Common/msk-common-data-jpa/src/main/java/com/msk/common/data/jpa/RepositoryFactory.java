package com.msk.common.data.jpa;

import com.msk.common.data.jpa.impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class RepositoryFactory <R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {
    /**
     *
     * <p>
     * Create a repository factory by an entity manager.
     * </p>
     *
     * @param entityManager an entity manager
     * @return a repository factory
     * @see JpaRepositoryFactoryBean#createRepositoryFactory(EntityManager)
     */
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory<T, I>(entityManager);
    }

    /**
     *
     * <p>
     * The base repository factory.
     * </p>
     *
     * @param <T> the entity type
     * @param <I> the entity's PK type
     *
     * @author zhou_ke
     */
    private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        /**
         *
         * <p>
         * The Constructors Method.
         * </p>
         *
         * @param entityManager an entity manager
         *
         * @author zhou_ke
         */
        public BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        /**
         *
         * <p>
         * Get the target repository.
         * </p>
         *
         * @param metadata the meta data
         * @return the target repository
         * @see JpaRepositoryFactory#getTargetRepository(RepositoryMetadata)
         *
         * @author zhou_ke
         */
        @SuppressWarnings("unchecked")
        protected Object getTargetRepository(RepositoryMetadata metadata) {
            return new BaseRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
        }

        /**
         *
         * <p>
         * Get the repository class's base class type.
         * </p>
         *
         * @param metadata the meta data
         * @return base class type
         * @see JpaRepositoryFactory#getRepositoryBaseClass(RepositoryMetadata)
         *
         * @author zhou_ke
         */
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }
}
