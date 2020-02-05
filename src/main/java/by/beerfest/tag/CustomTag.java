package by.beerfest.tag;

import by.beerfest.constant.PageParameter;
import by.beerfest.entity.Participant;
import by.beerfest.entity.UserRole;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

import static by.beerfest.constant.PageParameter.ROLE_NAME;

@SuppressWarnings("serial")
public class CustomTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            UserRole role = (UserRole) pageContext.getSession().getAttribute(ROLE_NAME);
            writer.write("<span class=\"navbar-text\">"+role.toString()+" </span>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }


}
