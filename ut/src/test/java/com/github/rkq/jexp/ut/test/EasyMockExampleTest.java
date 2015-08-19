package com.github.rkq.jexp.ut.test;

import com.github.rkq.jexp.ut.ClassUnderTest;
import com.github.rkq.jexp.ut.Collaborator;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by rick on 8/19/15.
 */
public class EasyMockExampleTest extends EasyMockSupport {
    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @Mock
    private Collaborator collaborator;

    @TestSubject
    private final ClassUnderTest classUnderTest = new ClassUnderTest();

    @Test
    public void addDocument() {
        collaborator.documentAdded("New Document 1");
        collaborator.documentAdded("New Document 2");
        EasyMock.replay(collaborator);
        classUnderTest.addDocument("New Document 2", "content".getBytes());
        classUnderTest.addDocument("New Document 1", "content".getBytes());
        EasyMock.verify(collaborator);
    }
}
