# WuxiaMine

This repository contains a Java-based Selenium Test Automation framework for the Wuxia website.

Overview
- Framework: Java + Maven + TestNG
- Browser automation: Selenium WebDriver with WebDriverManager (auto-manages browser drivers)
- Reporting: ExtentReports (Reports/index.html) and Maven Surefire/TestNG reports

Repository layout (important paths)
- `pom.xml`: Maven build and test configuration (TestNG suite configured: `TestNGXMLs/testng.xml`).
- `src/test/java`: Test classes and test components (main test code lives here under `SrvnTestAuto`).
- `src/main/java/SrvnTestAuto/resources/Environment.properties`: environment configuration (browser, baseUrl).
- `TestNGXMLs/`: TestNG suite XMLs used to run groups/collections of tests.
- `Reports/`: ExtentReports output (open `Reports/index.html` after a run).
- `target/surefire-reports` and `test-output`: Maven/TestNG generated test results.

Prerequisites
- Java 1.8 (JDK) or higher installed and `JAVA_HOME` set.
- Maven 3.x installed and on `PATH`.
- Internet access for `WebDriverManager` to download browser drivers (or pre-populate local driver cache).

Quick start — run the full test suite
From the repository root, run:

```bash
mvn clean test
```

This executes the TestNG suite configured in `pom.xml` (`TestNGXMLs/testng.xml`).

Run a single test class or method
- Run a test class:

```bash
mvn -Dtest=fully.qualified.ClassName test
```

- Run a single TestNG suite XML (override at runtime):

```bash
mvn -Dsurefire.suiteXmlFiles=TestNGXMLs/your-suite.xml test
```

Change browser or base URL
- Edit `src/main/java/SrvnTestAuto/resources/Environment.properties` and update `browser=` (chrome|firefox) and `baseUrl=`.
- The framework uses WebDriverManager so you do not normally need to manually download chromedriver/geckodriver.

Where reports and logs appear
- Extent report: `Reports/index.html` (created by the framework via `TestReporter`).
- Maven Surefire/TestNG reports: `target/surefire-reports` and `test-output` (useful for CI integration).
- Execution log file: the framework sets `Reports/execution.log` via the `logFile` system property.

Common troubleshooting
- If drivers fail to download, ensure the machine has internet access or pre-install drivers.
- Verify Java and Maven versions: `java -version` and `mvn -v`.
- If tests fail to start, check `Environment.properties` path and values.
- Check logs: `Reports/execution.log` and files under `target/surefire-reports` for stack traces.

Useful tips
- To run only a subset, create/modify a TestNG XML under `TestNGXMLs/` and point `pom.xml` or the CLI to it.
- For CI (Jenkins/GitHub Actions), use `mvn clean test` and archive `target/surefire-reports` and `Reports/` as artifacts.

CI — GitHub Actions (Windows scheduled job)
- Workflow file: `.github/workflows/windows-maven-tests.yml`
- Schedule: daily at 06:00 IST (cron: `30 0 * * *` UTC — GitHub Actions uses UTC).
- Runner: `windows-latest` (GitHub-hosted Windows VM).
- Java: Temurin Java 1.8 is installed using `actions/setup-java@v4`.
- Browser: Firefox is installed via Chocolatey (`choco install firefox --yes`).
- Caching: Maven local repository is cached to speed builds (`~/.m2/repository`).
- Command run: `mvn -B clean test` (non-interactive batch mode).
- Manual trigger: the workflow supports `workflow_dispatch` to run on demand from the Actions UI.
 - Artifacts: the workflow uploads test artifacts after every run. Uploaded artifacts and locations:
	 - `ExtentReports`: `Reports/`
	 - `SurefireReports`: `target/surefire-reports/`
	 - `TestOutput`: `test-output/`
	Each artifact is retained for 10 days and then automatically deleted by GitHub Actions (set via `retention-days`).

Notes
- The scheduled cron uses UTC time; `30 0 * * *` corresponds to 06:00 IST daily.
- If you want artifact upload re-enabled (Reports or Surefire results), I can add `actions/upload-artifact` steps back.

Contact / Next steps
- If you want, I can add Maven profiles to switch browsers or suites, or extend the workflow to run matrix builds.

# WuxiaMine
