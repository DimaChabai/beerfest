package by.beerfest.tag;

import by.beerfest.entity.UserRole;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static by.beerfest.command.PageParameter.ROLE_NAME;

@SuppressWarnings("serial")
public class CustomTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();

            UserRole role = (UserRole) pageContext.getSession().getAttribute(ROLE_NAME);
        try {
            writer.write("<span class=\"navbar-text\">" + role.toString() + " </span>");
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
