package org.egovframe.rte.fdl.cmmn.cnamespace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/context-cnamespace.xml" })
public class CNameSpaceTest {

	@Autowired
	private Foo foo;

	@Test
	public void test() {
		assertTrue(foo.getEmail().equals("foo@bar.com"));
	}
	
}
