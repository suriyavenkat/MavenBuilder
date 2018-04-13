package com.suriya;

import java.io.File;
import java.util.Collections;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationOutputHandler;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;


public class MavenCommandExecuter {
	public void  mavenExecuter(String pomLocation,String mavenLocation) {

		runCommand("test", new File(pomLocation),mavenLocation);
 
    }
 
    public void runCommand(String mavenCommand, File workingDirectory, String mavenLocation) {
        InvocationRequest request = new DefaultInvocationRequest();
       request.setPomFile(new File(workingDirectory, "pom.xml"));
        request.setGoals(Collections.singletonList(mavenCommand));
 
        Invoker invoker = new DefaultInvoker();
        final StringBuilder mavenOutput = new StringBuilder();
        invoker.setOutputHandler(new InvocationOutputHandler() {
 
            public void consumeLine(String line) {
                mavenOutput.append(line).append(System.lineSeparator());
            }
        });
        // You can find the Maven home by calling "mvn --version"
        invoker.setMavenHome(new File(mavenLocation));
        try {
            InvocationResult invocationResult = invoker.execute(request);
            // Process maven output
            System.out.println("rtyrty"+mavenOutput);
            if (invocationResult.getExitCode() != 0) {
                // handle error
            }
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }

}
