<%-- 
    Document   : uploadform
    Created on : Jun 3, 2017, 4:08:14 PM
    Author     : Nesma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html>  
    <head>  
        <title>Image File Upload</title>  
    </head>  
    <body>  
        <h1>File Upload </h1>  

        <h3 style="color:red">${filesuccess}</h3>  
        <form:form method="post" action="savefile" enctype="multipart/form-data">  
            <p><label for="image">Choose Image</label></p>  
            <p><input name="file" id="fileToUpload" type="file" /></p>  
            <p><input type="submit" value="Upload"></p>  
            </form:form>  
    </body>  
</html> 