import static org.mockito.Mockito.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import butov.pro.Provider;

public class ProviderTest extends TestCase {
	@Mock
	HttpServletRequest req, req1, req2;
	@Mock
	HttpServletResponse res, res1, res2;
	@Mock
	HttpSession session;
	@Mock
	RequestDispatcher dispatcher;

	@Before
	protected void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void test() throws Exception {
		when(req.getParameter("user")).thenReturn("Анатолий");
		when(req.getParameter("red")).thenReturn("255");
		when(req.getParameter("green")).thenReturn("0");
		when(req.getParameter("blue")).thenReturn("255");
		when(req.getSession()).thenReturn(session);

		when(req1.getParameter("user")).thenReturn("Иван");
		when(req1.getParameter("red")).thenReturn("255");
		when(req1.getParameter("green")).thenReturn("");
		when(req1.getParameter("blue")).thenReturn("255");
		when(req1.getRequestDispatcher("Error.jsp")).thenReturn(dispatcher);
		when(req1.getSession()).thenReturn(session);

		when(req2.getParameter("user")).thenReturn("Иван");
		when(req2.getParameter("red")).thenReturn("-200");
		when(req2.getParameter("green")).thenReturn("1500");
		when(req2.getParameter("blue")).thenReturn("255");
		when(req2.getRequestDispatcher("Error.jsp")).thenReturn(dispatcher);
		when(req2.getSession()).thenReturn(session);
		
	
		Provider pr = new Provider();

		pr.doPost(req, res);
		verify(session).setAttribute("user", "Анатолий");
		assertEquals(null, req.getAttribute("error"));
		verify(res).sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/Result.jsp"));

		pr.doPost(req1, res1);
		verify(session).setAttribute("error", "missedField");
		verify(dispatcher).forward(req1, res1);
		verify(res1, times(0)).sendRedirect(res1.encodeRedirectURL(req1.getContextPath() + "/Result.jsp"));

		pr.doPost(req2, res2);
		verify(session).setAttribute("error", "wrongColorNumber");
		verify(dispatcher).forward(req2, res2);
		verify(res2, times(0)).sendRedirect(res2.encodeRedirectURL(req2.getContextPath() + "/Result.jsp"));

	}
}
