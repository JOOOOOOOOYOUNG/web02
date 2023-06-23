package teamProject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProjectController {
	String handleRequest(HttpServletRequest req, HttpServletResponse resp);
}
