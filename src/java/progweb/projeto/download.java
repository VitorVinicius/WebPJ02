/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progweb.projeto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.FileNameMap;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vitor
 */
@WebServlet(name = "download", urlPatterns = {"/download"})
public class download extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (OutputStream out = response.getOutputStream()) {
          
            String file = request.getParameter("file");
            
            String folderPath = "c:\\arquivos";
            File folder = new File(folderPath);
            if(!folder.exists()){
                folder.mkdir();
            }
            String path = folderPath + "\\"+file;
            File fileOnDik = new File(path);
            if(!fileOnDik.exists()) throw new IOException("File not found");
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            String mimeType = fileNameMap.getContentTypeFor(path);
            
            String fileName = file;
            String fileType = mimeType;
            // Find this file id in database to get file name, and file type

            // You must tell the browser the file type you are going to send
            // for example application/pdf, text/plain, text/html, image/jpg
            response.setContentType(fileType);

            // Make sure to show the download dialog
            response.setHeader("Content-disposition","attachment; filename="+file);

            // Assume file name is retrieved from database
            // For example D:\\file\\test.pdf

            File my_file =fileOnDik; //new File(fileName);
            try ( // This should send the file to browser
                    FileInputStream in = new FileInputStream(my_file)) {
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0){
                    out.write(buffer, 0, length);
                }
            }
            out.flush();
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
