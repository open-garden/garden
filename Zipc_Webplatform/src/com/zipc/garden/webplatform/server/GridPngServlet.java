package com.zipc.garden.webplatform.server;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A class that is automatically called when the canvas uses a snap grid. <br>
 * The dot image displayed on the canvas is replaced.
 */
@SuppressWarnings("serial")
public class GridPngServlet extends HttpServlet {

    /**
     * Replaces the dot image used in the canvas grid snap. <br>
     * Replace with a light gray dot.
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sizeStr = (String) req.getParameter("size");
        BufferedImage bfImg = null;
        try {
            ServletContext context = getServletContext();
            String path = context.getRealPath("WEB-INF/images/grid.png");
            BufferedImage tmpBfImg = ImageIO.read(new java.io.File(path));
            int size = Integer.parseInt(sizeStr);
            Image tmp = tmpBfImg.getScaledInstance(tmpBfImg.getWidth(), tmpBfImg.getHeight(), Image.SCALE_DEFAULT);
            bfImg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bfImg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
        } catch (Exception e) {
            e.getStackTrace();
        }

        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "inline; filename=grid;");
        try (java.io.OutputStream out = resp.getOutputStream()) {
            ImageIO.write(bfImg, "png", out);
            out.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
