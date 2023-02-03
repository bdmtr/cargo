package com.cargo.controller.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DateTag class is a custom JSP tag that formats the given LocalDateTime object.
 */
public class DateTag extends SimpleTagSupport {
    private LocalDateTime localDateTime;
    private String pattern;

    /**
     * This method formats the LocalDateTime object using the specified pattern and writes the result to the JspWriter.
     *
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public void doTag() throws IOException {
        String formattedDateTime = localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        JspWriter writer = getJspContext().getOut();
        writer.print(formattedDateTime);
    }

    /**
     * This method sets the value of the localDateTime property.
     *
     * @param localDateTime The LocalDateTime object to be formatted
     */
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * This method sets the value of the pattern property.
     *
     * @param pattern The pattern used to format the LocalDateTime object
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
