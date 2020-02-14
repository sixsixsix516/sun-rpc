package com.sixsixsix516.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sun 2020/2/14 18:40
 */
@Slf4j
public class HTTPTransportServer implements TransportServer {

    private RequestHandler requestHandler;
    private Server server;

    public void init(int port, RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.server = new Server(port);

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        server.setHandler(servletContextHandler);
        ServletHolder servletHolder = new ServletHolder(new RequestServlet());
        servletContextHandler.addServlet(servletHolder, "/*");

    }

    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }


    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ServletInputStream inputStream = req.getInputStream();
            ServletOutputStream outputStream = resp.getOutputStream();
            if (requestHandler != null) {
                requestHandler.onRequest(inputStream, outputStream);
            }

            outputStream.flush();
        }
    }
}
