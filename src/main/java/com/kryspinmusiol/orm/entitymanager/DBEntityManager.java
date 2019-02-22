package com.kryspinmusiol.orm.entitymanager;

import com.kryspinmusiol.ioc.annotation.Inject;
import com.kryspinmusiol.orm.mappingabstractions.AbstractField;
import com.kryspinmusiol.orm.metamodel.Metamodel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class DBEntityManager implements EntityManager {

    @Inject
    private Connection connection;

    private AtomicInteger idGen = new AtomicInteger(1);

    @Override
    public <T> void persist(T t) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        Metamodel<?> metamodel = Metamodel.of(t.getClass());
        final String sql = metamodel.buildInsertStatement();
        try (PreparedStatement statement = prepareStatementWith(sql).andParameters(t)) {
            statement.executeUpdate();
        }

    }

    private PreparedStatementWrapper prepareStatementWith(String sql) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(sql);
        return new PreparedStatementWrapper(statement);
    }

    @Override
    public <T> T find(Class<T> cls, Object primaryKey) {
        return null;
    }



    private class PreparedStatementWrapper {

        public static final int COLUMN_OFFSET = 2;
        private final PreparedStatement preparedStatement;

        private PreparedStatementWrapper(PreparedStatement preparedStatement) {
            this.preparedStatement = preparedStatement;
        }


        public <T> PreparedStatement andParameters(T t) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
            Metamodel<?> metamodel = Metamodel.of(t.getClass());
            final Class<?> type = metamodel.getPrimaryKeyColumn().getType();
            if (type == int.class) {
                final int id = idGen.getAndIncrement();
                preparedStatement.setInt(1, id);
                final Field pkField = metamodel.getPrimaryKeyColumn().getField();
                pkField.setAccessible(true);
                pkField.set(t, id);
            }

            for (int columnIndex = 0; columnIndex < metamodel.getColumns().size(); columnIndex++) {
                final AbstractField abstractField = metamodel.getColumns().get(columnIndex);
                final Class<?> fieldType = abstractField.getType();
                final Field field = abstractField.getField();
                field.setAccessible(true);
                final Object valueToStore= field.get(t);
                if (fieldType == int.class) {
                    preparedStatement.setInt(columnIndex + COLUMN_OFFSET, (int) valueToStore);
                } else if (fieldType == String.class) {
                    preparedStatement.setString(columnIndex + COLUMN_OFFSET, (String) valueToStore);
                }
            }
            return preparedStatement;
        }
    }

}
