package com.minseokism.anything;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.minseokism.App;
import com.nhncorp.lucy.security.xss.XssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;
import com.nhncorp.lucy.security.xss.XssSaxFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class LucyXssFilterTest {
	//XssPreventer
	@Test
	public void testXssPreventer() {
	    String dirty = "\"><script>alert('xss');</script>";
	    String clean = XssPreventer.escape(dirty);

	    Assert.assertEquals(clean, "&quot;&gt;&lt;script&gt;alert(&#39;xss&#39;);&lt;/script&gt;");
	    Assert.assertEquals(dirty, XssPreventer.unescape(clean));
	}
	
	//XssFilter : DOM
	@Test
	public void pairQuoteCheckOtherCase() {
	    XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
	    String dirty = "<img src=\"<img src=1\\ onerror=alert(1234)>\" onerror=\"alert('XSS')\">";
	    String expected = "<img src=\"\"><!-- Not Allowed Attribute Filtered ( onerror=alert(1234)) --><img src=1\\>\" onerror=\"alert('XSS')\"&gt;";
	    String clean = filter.doFilter(dirty);
	    Assert.assertEquals(expected, clean);

	    dirty = "<img src='<img src=1\\ onerror=alert(1234)>\" onerror=\"alert('XSS')\">";
	    expected = "<img src=''><!-- Not Allowed Attribute Filtered ( onerror=alert(1234)) --><img src=1\\>\" onerror=\"alert('XSS')\"&gt;";
	    clean = filter.doFilter(dirty);
	    Assert.assertEquals(expected, clean);
	}
	
	//XssFilter : SAX
	@Test
	public void testSuperSetFix() {
	    XssSaxFilter filter = XssSaxFilter.getInstance("lucy-xss-superset-sax.xml");
	    String clean = "<TABLE class=\"NHN_Layout_Main\" style=\"TABLE-LAYOUT: fixed\" cellSpacing=\"0\" cellPadding=\"0\" width=\"743\">" + "</TABLE>" + "<SPAN style=\"COLOR: #66cc99\"></SPAN>";
	    String filtered = filter.doFilter(clean);
	    Assert.assertEquals(clean, filtered);
	}
	
}
