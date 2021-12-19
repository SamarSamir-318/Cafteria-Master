<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<section class='body' xmlns="http://www.w3.org/1999/xhtml">
    <div>
        <center>
            <img src="${contextPath}/resources/dist/img/500.png" alt="Remove Item" 
                 width="600" height="350" style="border-radius: 20px;"/>
            <p style="color: #009;font-size: 17px;">
                <br/>
                We are sorry for this but the service is currently unavailable, 
                <br/>please try again later or contact us on 
                <c:set var="date" value="<%=new java.util.Date()%>"/>
                <b><a id="mailId" href="mailto:jetsinfo@mcit.gov.eg?subject=Service Access Error (${date})&body=I have found an error on (${date}). %0AAt service (${contextPath}">
                        jetsinfo@mcit.gov.eg</a></b>
                <script>
                    var oldLink = $('#mailId').attr("href");
                    var pathName = window.location.pathname + ")";
                    $('#mailId').attr("href", oldLink + pathName);
                </script>
            </p>
        </center>
    </div>
</section>