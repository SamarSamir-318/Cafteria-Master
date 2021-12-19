<%-- 
    Document   : clinetHeader
    Created on : Jun 7, 2017, 2:18:30 PM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:directive.include file="/WEB-INF/layouts/includes/includes.jsp"/>
<div class="bg-img">
    <!-- banner -->
    <div class="banner code-banner">
        <div class="container">
            <div class="header">
                <div class="logo">
                    <h1><a href="${contextPath}/allItemsAndOffers"> 
                            <img src="${contextPath}/resources/dist/img/logo.png"  style="max-height: 79px;" alt="User Image">
                        </a>
                    </h1>
                </div>
                <div class="top-nav">
                    <nav class="navbar navbar-default">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">Menu						
                        </button>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li><a href="${contextPath}/allItemsAndOffers">Home</a></li>
                                <li><a href="${contextPath}/showCustomerOffer">Offers</a></li>
                                <li><a href="${contextPath}/showFavItems">Favorites</a></li>
                                <!--<li><a href="${contextPath}/selectFavItems">Items</a></li>-->	
                                <li><a href="${contextPath}/showCustomerOrders">Order History</a></li>
                                    <security:authorize access="!isAuthenticated()">
                                    <li><a href="${contextPath}/login">Login</a></li>
                                    </security:authorize>                                        
                                    <security:authorize access="isAuthenticated()">
                                    <li><a href="${contextPath}/adminHome.htm">Admin</a></li>
                                    </security:authorize>
                                <div class="clearfix"> </div>
                            </ul>	
                        </div>	
                    </nav>		
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
    </div>
    <!-- //banner -->
</div>
