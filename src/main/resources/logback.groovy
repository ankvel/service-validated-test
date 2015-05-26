import java.nio.charset.Charset
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy

/**
 * 
 * @author Aleksey Kovalenko
 */

appender ("CONSOLE", ConsoleAppender.class) {
	encoder (PatternLayoutEncoder.class) {
		charset = Charset.forName("UTF-8")
		pattern = "%d %-4relative [%thread] %-5level %logger{35} - %msg%n"
	}
}

appender ("FILE", RollingFileAppender.class) {

	encoder (PatternLayoutEncoder.class) {		
		charset = Charset.forName("UTF-8")
		pattern = "%d %-4relative [%thread] %-5level %logger{35} - %msg%n"
	}

	file = "./logs/aaa.log"
	
	rollingPolicy (FixedWindowRollingPolicy.class) {
		fileNamePattern = "./logs/aaa.%i.log.zip"
		minIndex = 1
		maxIndex = 20
	}
	
	triggeringPolicy (SizeBasedTriggeringPolicy.class) {		
		maxFileSize = "30MB"		
	}

}

root(DEBUG, ["FILE", "CONSOLE"])
