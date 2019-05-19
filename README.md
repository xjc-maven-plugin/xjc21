# XJC 2.1 Maven Plugin

This is a XJC 2.1 specific version of the `xjc2-maven-plugin`.  
Please visit [`xjc2-maven-plugin`](https://github.com/xjc-maven-plugin/xjc21) for the actual version and documentation.

You should use this plugin only if you want to compile your schemas specifically with XJC 2.1.

## Quick start

* Put your schemas (`*.xsd`) and bindings (`*.xjb`) into the `src/main/resources` folder.
* Add the plugin to your `pom.xml`:

```xml
<project ...>
	...
	<build>
		<plugins>
			...
			<plugin>
				<groupId>org.hisrc.xjc</groupId>
				<artifactId>xjc21-maven-plugin</artifactId>
				<version><!-- Put the actual version here --></version>
				<executions>
					<execution>
						<goals>
							<goal>generate</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
			...
		</plugins>
	</build>
	...
</project>
```

## Disclaimer

This project is not developed, supported or in any other way affiliated with Apache. The `org.hisrc.xjc:xjc21-maven-plugin` is not an Apache product (and does not pretend to be one), it is a completely independent development.

This project is also *not* developed by or affiliated with Oracle or Sun. 

**This plugin is in no way _official_ JAXB/XJC Maven plugin by Sun or Oracle.**

This is a completely indepentent development. [I](https://github.com/highsource) am *not* an Oracle employee.