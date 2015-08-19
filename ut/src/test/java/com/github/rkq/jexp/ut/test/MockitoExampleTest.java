package com.github.rkq.jexp.ut.test;

import com.github.rkq.jexp.ut.ClassUnderTest;
import com.github.rkq.jexp.ut.Collaborator;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by rick on 8/19/15.
 */
public class MockitoExampleTest {
    @Test
    public void testAddDocument() {
        Collaborator collaborator = Mockito.mock(Collaborator.class);
        ClassUnderTest classUnderTest = new ClassUnderTest();
        classUnderTest.setListener(collaborator);
        classUnderTest.addDocument("New Document 1", "content".getBytes());
        classUnderTest.addDocument("New Document 2", "content".getBytes());
        Mockito.verify(collaborator).documentAdded("New Document 1");
        Mockito.verify(collaborator).documentAdded("New Document 2");
    }
}
