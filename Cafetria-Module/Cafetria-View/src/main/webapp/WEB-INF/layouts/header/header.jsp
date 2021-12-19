<%-- 
    Document   : header
    Created on : May 24, 2017, 6:25:33 PM
    Author     : JEDiver
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<header class="main-header">
    <!-- Logo -->
    <a href="${contextPath}" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>C</b> ITI</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b> 
                <img src="${contextPath}/resources/dist/img/logo.png"  style="max-height: 60px;" alt="User Image">
                </span>
                </a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>

                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">




                            <li class="dropdown messages-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-language" aria-hidden="true"></i>
                                    <span class="label label-success"><spring:message code="language" text="default text" /></span>
                                </a>

                                <!--handle language--> 
                                <c:url var="arabic" value="">
                                    <c:forEach items="${param}" var="entry">
                                        <c:if test="${entry.key != 'lang'}">
                                            <c:param name="${entry.key}" value="${entry.value}" />
                                        </c:if>
                                    </c:forEach>
                                    <c:param name="lang" value="ar_EG" />
                                </c:url>
                                <c:url var="english" value="">
                                    <c:forEach items="${param}" var="entry">
                                        <c:if test="${entry.key != 'lang'}">
                                            <c:param name="${entry.key}" value="${entry.value}" />
                                        </c:if>
                                    </c:forEach>
                                    <c:param name="lang" value="en_US" />
                                </c:url>

                                <ul class="dropdown-menu">
                                    <li><a href="${english}"><spring:message code="language.English" text="default text" /></a></li>
                                    <li>
                                        <a href="${arabic}"><spring:message code="language.Arabic" text="default text" /></a>
                                    </li>

                                </ul>
                            </li>



                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="${contextPath}/resources/dist/img/user.png" class="user-image" alt="User Image">
                                    <span class="hidden-xs">${sessionScope.user.name}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="${contextPath}/resources/dist/img/user.png" class="img-circle" alt="User Image">

                                        <p>
                                            Admin
                                            <small>Member since Nov. 2012</small>
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
											<a href="${contextPath}/login?logout" class="btn btn-default btn-flat">Sign out</a>
										</div>
                                    </li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                </nav>
                </header>
