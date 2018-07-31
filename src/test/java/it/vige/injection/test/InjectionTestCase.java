package it.vige.injection.test;

import static java.util.logging.Logger.getLogger;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import static org.jboss.shrinkwrap.api.asset.EmptyAsset.INSTANCE;
import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.vige.injection.injection.CommentWriter;
import it.vige.injection.injection.Writer;

@RunWith(Arquillian.class)
public class InjectionTestCase {

	private static final Logger logger = getLogger(InjectionTestCase.class.getName());

	@Deployment
	public static JavaArchive createCDIDeployment() {
		final JavaArchive jar = create(JavaArchive.class, "injection-cdi-test.jar");
		jar.addPackage(Writer.class.getPackage());
		jar.addAsManifestResource(INSTANCE, "beans.xml");
		return jar;
	}

	@Inject
	private Writer writer;

	@Test
	public void testOk() {
		logger.info("Start Ok test");
		assertTrue("it takes the default annotated writer", writer instanceof CommentWriter);
	}
}
