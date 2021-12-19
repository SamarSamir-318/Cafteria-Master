<%-- 
    Document   : clientFooter
    Created on : Jun 7, 2017, 2:24:16 PM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!-- //footer -->
<!-- copyright -->
<div class="copyright">
    <div class="container">
        <div class="w3agile-list">
            <ul>
                <li><a href="${context}/allItemsAndOffers">Home</a></li>
				<li><a href="${context}/showCustomerOffer">Offers</a></li>
				<li><a href="${context}/showFavItems">Favorites</a></li>
				<!--<li><a href="${context}/selectFavItems">Items</a></li>-->	
				<li><a href="${context}/showCustomerOrders">Order History</a></li>
            </ul>
        </div>
        <div class="agileinfo">
            <p>© 2017 ITI Cafeteria System. All Rights Reserved.</p>
        </div>
    </div>
</div>
<!-- //copyright -->