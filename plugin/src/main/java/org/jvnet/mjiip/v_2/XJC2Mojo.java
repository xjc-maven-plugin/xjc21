package org.jvnet.mjiip.v_2;

import org.jfrog.maven.annomojo.annotations.MojoGoal;
import org.jfrog.maven.annomojo.annotations.MojoPhase;
import org.jvnet.mjiip.v_2_2.XJC22Mojo;

/**
 * JAXB 2.x Mojo.
 * 
 * @author Aleksei Valikov (valikov@gmx.net)
 */
@MojoGoal("generate")
@MojoPhase("generate-sources")
public class XJC2Mojo extends XJC22Mojo {
}