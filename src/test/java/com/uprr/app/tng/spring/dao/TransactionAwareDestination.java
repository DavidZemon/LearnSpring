package com.uprr.app.tng.spring.dao;

import com.ninja_squad.dbsetup.destination.Destination;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.jdbc.datasource.ConnectionProxy;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Nonnull;
import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A DbSetup destination which wraps a DataSource and gets its connection from a JDBC DataSource,
 * adding awareness of Spring-managed transactions.
 */
public class TransactionAwareDestination implements Destination {
    @Nonnull private final DataSource                 dataSource;
    @Nonnull private final PlatformTransactionManager transactionManager;

    /**
     * Create a new TransactionAwareDestination.
     *
     * @param dataSource         the target DataSource
     * @param transactionManager the transaction manager to use
     */
    public TransactionAwareDestination(@Nonnull final DataSource dataSource,
                                       @Nonnull final PlatformTransactionManager transactionManager) {
        this.dataSource = new TransactionAwareDataSourceProxy(dataSource);
        this.transactionManager = transactionManager;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return (Connection) Proxy.newProxyInstance(ConnectionProxy.class.getClassLoader(),
                                                   new Class[]{ConnectionProxy.class},
                                                   new TransactionAwareInvocationHandler(this.dataSource));

    }

    private class TransactionAwareInvocationHandler extends DefaultTransactionDefinition implements InvocationHandler {
        private static final long serialVersionUID = 7577417334960519637L;

        @SuppressWarnings("NonSerializableFieldInSerializableClass")
        @Nonnull
        private final DataSource targetDataSource;

        public TransactionAwareInvocationHandler(@Nonnull final DataSource targetDataSource) {
            this.targetDataSource = targetDataSource;
        }

        @Override
        public Object invoke(final Object proxy, @Nonnull final Method method, final Object[] args) throws Throwable {
            final TransactionStatus status;
            switch (method.getName()) {
                case "commit":
                    status = TransactionAwareDestination.this.transactionManager.getTransaction(this);
                    TransactionAwareDestination.this.transactionManager.commit(status);
                    return null;
                case "rollback":
                    status = TransactionAwareDestination.this.transactionManager.getTransaction(this);
                    TransactionAwareDestination.this.transactionManager.rollback(status);
                    return null;
                default:
                    try {
                        final Connection connection = this.targetDataSource.getConnection();
                        return method.invoke(connection, args);
                    } catch (final InvocationTargetException ex) {
                        throw ex.getTargetException();
                    }
            }
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final TransactionAwareDestination that = (TransactionAwareDestination) o;

        return new EqualsBuilder()
            .append(this.dataSource, that.dataSource)
            .append(this.transactionManager, that.transactionManager)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(this.dataSource)
            .append(this.transactionManager)
            .toHashCode();
    }
}
