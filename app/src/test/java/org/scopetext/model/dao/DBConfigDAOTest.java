package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit tests for DBConfigDAOTest.java.
 * Created by john.qualls on 9/30/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DBConfigDAOTest {
    @Mock
    SQLiteDatabase db;

    @Test
    public void itShouldVerifyScopeTextCreateTableSQL() {
        DBConfigDAO.createScopeTextTable(db);
        Mockito.verify(db).execSQL(DBConfigDAO.getCreateScoptextTable());
    }

    @Test
    public void itShouldVerifyMessageCreateTableSQL() {
        DBConfigDAO.createMessageTable(db);
        Mockito.verify(db).execSQL(DBConfigDAO.getCreateMessageTable());
    }

    @Test
    public void itShouldVerifyResponseCreateTableSQL() {
        DBConfigDAO.createResponseTable(db);
        Mockito.verify(db).execSQL(DBConfigDAO.getCreateResponseTable());
    }

    @Test
    public void itShouldVerifyContactCreateTableSQL(){
        DBConfigDAO.createContactTable(db);
        Mockito.verify(db).execSQL(DBConfigDAO.getCreateContactTable());
    }

    @Test
    public void itShouldVerifyContactAssocCreateTableSQL(){
        DBConfigDAO.createContactAssocTable(db);
        Mockito.verify(db).execSQL(DBConfigDAO.getCreateContactAssocTable());
    }
}
