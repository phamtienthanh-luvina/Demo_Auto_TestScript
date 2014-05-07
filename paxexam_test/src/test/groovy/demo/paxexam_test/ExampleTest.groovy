//
// Generated from archetype; please customize.
//

package demo.paxexam_test

import javax.inject.Inject;
import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.*;
 
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.junit.runner.JUnitCore;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class ExampleTest {
	String strCmd;
	String message;
	def listCmd;
	
    public ExampleTest() {}

	@Inject
	private org.osgi.framework.BundleContext context;
	String wd = System.getProperty("user.dir");
	
    @Configuration
    public Option[] config() {
        return options(
			// felix log level
			systemProperty("felix.log.level").value("4"), // 4 = DEBUG
			// setup properties for fileinstall bundle.
			systemProperty("felix.home").value(wd),
			// Pax-exam make this test code into OSGi bundle at runtime, so 
			// we need "groovy-all" bundle to use this groovy test code.
            mavenBundle("org.codehaus.groovy", "groovy-all", "2.2.1"),
            junitBundles()
            );
    }
	
	@Before
	public void prepare() {
		strCmd = "D:/20140507wiperdog/bin/genjob.bat";
		message = "";
		listCmd = []
	}

	@After
	public void finish() {
	}

	@Test
	public void create_tmp_job_01() {
		// set list command for create job
		listCmd.add(strCmd)
		listCmd.add("-n")
		listCmd.add("TestJob01")
		// run command
		ProcessBuilder builder = new ProcessBuilder(listCmd);
		builder.redirectErrorStream(true);
		File dir = new File(wd)
		builder.directory(dir);
		Process p = builder.start();
		InputStream procOut  = p.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(procOut))
		def line = null
		StringBuffer stdin = new StringBuffer()
		while((line = br.readLine()) != null){
			stdin.append(line + "\n")
		}
		message = stdin.toString()
		assertTrue(message.contains("CREATE JOB: TestJob01"))
		def jobContent = new File(wd + "/TestJob01.job").getText()
		// get data test
		def result = (new File(wd + "/src/test/groovy/demo/data_test/TestJob01.job")).text
		assertEquals(jobContent, result)
	}
}
