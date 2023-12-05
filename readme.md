The Eclipse compiler has issues with sealed classes and type parameters.


This project is setup to use the Eclipse compiler instead of the Maven compiler
and has the preferences stored in `etc/eea/prefs`.

Compiler:

```xml
          <dependency>
            <groupId>org.eclipse.jdt</groupId>
            <artifactId>ecj</artifactId>
            <version>${ecj.version}</version>
          </dependency>
```

In version 3.35.0 there was no issue.

In version 3.36.0 the following error shoes up:

```
[WARNING] /Users/agent/projects/jdt-null-issue/src/main/java/bug/Blah.java:[6,87] Null constraint mismatch: The type 'E extends java.lang.Exception' is not a valid substitute for the type parameter 'E extends java.lang.@NonNull Exception'
```

This was found in JStachio: https://github.com/jstachio/jstachio
which uses ECJ for analysis. ECJ is actually a fantastic linting tool.

Run:

```
mvn clean compile
```

And the output will probably be something like:

```
[INFO] Scanning for projects...
[INFO]
[INFO] --------------------< com.adamgent:jdt-null-issue >---------------------
[INFO] Building jdt-null-issue 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- clean:3.2.0:clean (default-clean) @ jdt-null-issue ---
[INFO] Deleting /Users/agent/projects/jdt-null-issue/target
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ jdt-null-issue ---
[INFO] skip non existing resourceDirectory /Users/agent/projects/jdt-null-issue/src/main/resources
[INFO]
[INFO] --- compiler:3.11.0:compile (default-compile) @ jdt-null-issue ---
[WARNING]  Parameter 'compilerArguments' is deprecated: use compilerArgs instead.
[INFO] Changes detected - recompiling the module! :source
[INFO] Compiling with eclipse [debug deprecation target 21] to target/classes
[INFO] -------------------------------------------------------------
[WARNING] COMPILATION WARNING :
[INFO] -------------------------------------------------------------
[WARNING] /Users/agent/projects/jdt-null-issue/src/main/java/bug/Blah.java:[6,87] Null constraint mismatch: The type 'E extends java.lang.Exception' is not a valid substitute for the type parameter 'E extends java.lang.@NonNull Exception'
[INFO] 1 warning
[INFO] -------------------------------------------------------------
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] [ecj] The compiler reported an error but has not written it to its logging
[ERROR] [ecj] The following line(s) might indicate the issue:
	                                                                                      ^
Null constraint mismatch: The type 'E extends Exception' is not a valid substitute for the type parameter 'E extends @NonNull Exception'
----------
1 problem (1 warning)
error: warnings found and -failOnWarning specified

[INFO] 2 errors
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.732 s
[INFO] Finished at: 2023-12-05T12:19:56-05:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (default-compile) on project jdt-null-issue: Compilation failure: Compilation failure:
[ERROR] [ecj] The compiler reported an error but has not written it to its logging
[ERROR] [ecj] The following line(s) might indicate the issue:
[ERROR] 	                                                                                      ^
[ERROR] Null constraint mismatch: The type 'E extends Exception' is not a valid substitute for the type parameter 'E extends @NonNull Exception'
[ERROR] ----------
[ERROR] 1 problem (1 warning)
[ERROR] error: warnings found and -failOnWarning specified
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```
