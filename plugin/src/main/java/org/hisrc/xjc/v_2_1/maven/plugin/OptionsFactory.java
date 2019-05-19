package org.hisrc.xjc.v_2_1.maven.plugin;

import java.text.MessageFormat;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.hisrc.xjc.maven.OptionsConfiguration;
import org.hisrc.xjc.maven.util.StringUtils;
import org.xml.sax.InputSource;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Language;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.api.SpecVersion;

public class OptionsFactory implements
		org.hisrc.xjc.maven.OptionsFactory<Options> {
	/**
	 * Creates and initializes an instance of XJC options.
	 *
	 */
	public Options createOptions(OptionsConfiguration optionsConfiguration)
			throws MojoExecutionException {
		final Options options = new Options();

		options.verbose = optionsConfiguration.isVerbose();
		options.debugMode = optionsConfiguration.isDebugMode();

		options.classpaths.addAll(optionsConfiguration.getPlugins());

		options.target = SpecVersion.V2_1;

		options.setSchemaLanguage(createLanguage(optionsConfiguration
				.getSchemaLanguage()));

		options.entityResolver = optionsConfiguration.getEntityResolver();

		for (InputSource grammar : optionsConfiguration.getGrammars()) {
			options.addGrammar(grammar);
		}

		for (InputSource bindFile : optionsConfiguration.getBindFiles()) {
			options.addBindFile(bindFile);
		}

		// Setup Other Options

		options.defaultPackage = optionsConfiguration.getGeneratePackage();
		options.targetDir = optionsConfiguration.getGenerateDirectory();

		options.strictCheck = optionsConfiguration.isStrict();
		options.readOnly = optionsConfiguration.isReadOnly();
		options.packageLevelAnnotations = optionsConfiguration
				.isPackageLevelAnnotations();
		options.noFileHeader = optionsConfiguration.isNoFileHeader();
		options.enableIntrospection = optionsConfiguration
				.isEnableIntrospection();
		// options.disableXmlSecurity =
		// optionsConfiguration.isDisableXmlSecurity();
		if (optionsConfiguration.getAccessExternalSchema() != null) {
			System.setProperty("javax.xml.accessExternalSchema",
					optionsConfiguration.getAccessExternalSchema());
		}
		if (optionsConfiguration.getAccessExternalDTD() != null) {
			System.setProperty("javax.xml.accessExternalDTD",
					optionsConfiguration.getAccessExternalDTD());
		}
		if (optionsConfiguration.isEnableExternalEntityProcessing()) {
			System.setProperty("enableExternalEntityProcessing", Boolean.TRUE.toString());
		}
		options.contentForWildcard = optionsConfiguration
				.isContentForWildcard();

		if (optionsConfiguration.isExtension()) {
			options.compatibilityMode = Options.EXTENSION;
		}

		final List<String> arguments = optionsConfiguration.getArguments();
		try {
			options.parseArguments(arguments.toArray(new String[arguments
					.size()]));
		}

		catch (BadCommandLineException bclex) {
			throw new MojoExecutionException("Error parsing the command line ["
					+ arguments + "]", bclex);
		}

		return options;
	}

	private Language createLanguage(String schemaLanguage)
			throws MojoExecutionException {
		if (StringUtils.isEmpty(schemaLanguage)) {
			return null;
		} else if ("AUTODETECT".equalsIgnoreCase(schemaLanguage))
			return null; // nothing, it is AUTDETECT by default.
		else if ("XMLSCHEMA".equalsIgnoreCase(schemaLanguage))
			return Language.XMLSCHEMA;
		else if ("DTD".equalsIgnoreCase(schemaLanguage))
			return Language.DTD;
		else if ("RELAXNG".equalsIgnoreCase(schemaLanguage))
			return Language.RELAXNG;
		else if ("RELAXNG_COMPACT".equalsIgnoreCase(schemaLanguage))
			return Language.RELAXNG_COMPACT;
		else if ("WSDL".equalsIgnoreCase(schemaLanguage))
			return Language.WSDL;
		else {
			throw new MojoExecutionException(MessageFormat.format(
					"Unknown schemaLanguage [{0}].", schemaLanguage));
		}
	}
}
